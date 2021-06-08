package litecart.appmanager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    Select select = new Select(wd.findElement(By.cssSelector("select.select2-hidden-accessible")));
    select.selectByVisibleText(country);
    countryField.click();
    wd.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
    WebElement phoneElement = wd.findElement(By.cssSelector("input[name=phone]"));
    phoneElement.sendKeys(Keys.HOME + phoneElement.getAttribute("placeholder") + phone);
    wd.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
    wd.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
    wd.findElement(By.cssSelector("button[type=submit]")).click();
  }

  public void logout(){
    wd.findElement(By.cssSelector("#box-account a[href$='logout']")).click();
  }

  public void addNewProduct(String name, String code, String category, String defaultCategory, String productGroupsGender, File photo,
                            String quantity, String quantityUnit, String deliveryStatus){
    wd.findElement(By.cssSelector("a[href$='catalog']")).click();
    wd.findElement(By.cssSelector("a.button[href$=product]")).click();
    selectCheckBox(By.cssSelector("input[name='status'][value='1']"));
    wd.findElement(By.cssSelector("input[name^='name']")).sendKeys(name);
    wd.findElement(By.cssSelector("input[name='code']")).sendKeys(code);
    selectCheckBox(By.cssSelector((String.format("input[data-name='%s']", category))));
    selectValueFromList(By.cssSelector("select[name='default_category_id']"),defaultCategory);
    selectCheckBox(By.xpath(String.format("//td[contains(text(),'%s')]/../td[1]",productGroupsGender)));
    wd.findElement(By.cssSelector("input[type='file']")).sendKeys(photo.getAbsolutePath());
    wd.findElement(By.cssSelector("input[name='quantity']")).sendKeys(quantity);
    selectValueFromList(By.cssSelector("select[name='quantity_unit_id']"),quantityUnit);
    selectValueFromList(By.cssSelector("select[name='delivery_status_id']"),deliveryStatus);
  }

  public void selectCheckBox(By locator) {
    List<WebElement> elements =wd.findElements(locator);
    for (WebElement element : elements){
      Boolean checked = Boolean.valueOf(element.getAttribute("checked"));
      if(!checked){
        element.click();
      }
    }
  }

  public void selectValueFromList (By locator, String value) {
    Select select = new Select(wd.findElement(locator));
    if(!select.getFirstSelectedOption().getText().equals(value)){
      select.selectByVisibleText(value);
    }
  }

  public void stop() {
    wd.quit();
  }
  public String propertyValue(String propertyName) {
    return properties.getProperty(propertyName);
  }
}
