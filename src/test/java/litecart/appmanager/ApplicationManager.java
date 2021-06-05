package litecart.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
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

  public void login(String email, String password) {
    wd.get(properties.getProperty("web.baseUrl"));
    wd.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    wd.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    wd.findElement(By.xpath("//button[@name='login']")).click();
    wd.findElement(By.cssSelector("a[href='http://localhost/litecart/public_html/en/logout']"));
  }
  public void loginAdmin() {
    wd.get(properties.getProperty("web.adminUrl"));
    wd.findElement(By.xpath("//input[@name='username']")).sendKeys(properties.getProperty("web.adminLogin"));
    wd.findElement(By.xpath("//input[@name='password']")).sendKeys(properties.getProperty("web.adminPassword"));
    wd.findElement(By.xpath("//button[@name='login']")).click();
  }

  public void userRegistration(String firstName, String lastName, String address1, String postcode, String city,
                               String country, String email, String phone,String password){
    wd.get(properties.getProperty("web.baseUrl"));
    wd.findElement(By.cssSelector("#box-account-login a[href$='create_account']")).click();
    wd.findElement(By.cssSelector("input[name=firstname]")).sendKeys(firstName);
    wd.findElement(By.cssSelector("input[name=lastname]")).sendKeys(lastName);
    wd.findElement(By.cssSelector("input[name=address1]")).sendKeys(address1);
    wd.findElement(By.cssSelector("input[name=postcode]")).sendKeys(postcode);
    wd.findElement(By.cssSelector("input[name=city]")).sendKeys(city);
    WebElement countryField = wd.findElement(By.cssSelector("span.select2-selection__arrow"));
    countryField.click();
    wd.findElement(By.cssSelector("input[type=search]")).sendKeys(country);
    Select select = new Select(wd.findElement(By.cssSelector("select[name=country_code]")));
    select.selectByVisibleText(country);
    //select.selectByValue(country);
    wd.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
    WebElement phoneElement = wd.findElement(By.cssSelector("input[name=phone]"));
    phoneElement.sendKeys(Keys.HOME + phoneElement.getAttribute("placeholder") + phone);
    wd.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
    wd.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
    wd.findElement(By.cssSelector("button[type=submit]")).click();
  }

  public void stop() {
    wd.quit();
  }
  public String propertyValue(String propertyName) {
    return properties.getProperty(propertyName);
  }
}
