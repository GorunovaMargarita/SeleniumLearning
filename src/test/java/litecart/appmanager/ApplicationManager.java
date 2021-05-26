package litecart.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  private final String browser;
  public WebDriver wd;
  public WebDriverWait wait;


  public ApplicationManager(String browser)  {
    this.browser=browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      /*
      //раскомментировать, если хотим запустить Firefox Nightly
      FirefoxOptions options = new FirefoxOptions();
      options.setBinary(new FirefoxBinary(new File("D:\\Tools\\Firefox Nightly\\firefox.exe")));
      wd = new FirefoxDriver(options);
       */
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
  }

  public void login(String email, String login) {
    wd.get(properties.getProperty("web.baseUrl"));
    wd.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    wd.findElement(By.xpath("//input[@name='password']")).sendKeys(login);
    wd.findElement(By.xpath("//button[@name='login']")).click();
    wd.findElement(By.cssSelector("a[href='http://localhost/litecart/public_html/en/logout']"));
  }

  public void stop() {
    wd.quit();
  }
  public String propertyValue(String propertyName) {
    return properties.getProperty(propertyName);
  }
}
