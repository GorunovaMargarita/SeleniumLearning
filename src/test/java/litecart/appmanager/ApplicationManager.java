package litecart.appmanager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private final String browser;
  public WebDriver wd;
  public WebDriverWait wait;
  public int implicitlyWaitTimeOut = 30;
  public int waitTimeOut = 30;


  public ApplicationManager(String browser)  {
    this.browser=browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target","remote");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

    if("".equals(properties.getProperty("selenium.server"))){
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
    }else{
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform","windows")));
      wd=new RemoteWebDriver(new URL(properties.getProperty("selenium.server")),capabilities);
    }
    //properties.load(new FileReader(new File("src/test/resources/local.properties")));
    wd.manage().timeouts().implicitlyWait(implicitlyWaitTimeOut, TimeUnit.SECONDS);
    wait = new WebDriverWait(wd,waitTimeOut);
  }

  public void login(String email, String password) {
    wd.get(properties.getProperty("web.baseUrl"));
    wd.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    wd.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    wd.findElement(By.xpath("//button[@name='login']")).click();
    wd.findElement(By.cssSelector("#box-account a[href$='logout']"));
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
    wd.findElement(By.cssSelector("span.select2-selection__arrow")).click();
    wd.findElement(By.xpath(String.format("//li[contains(text(),'%s')]",country))).click();
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
                            String quantity, String quantityUnit, String deliveryStatus, String soldOutStatus, String dateValidFrom,
                            String dateValidTo, String manufacturer,
                            String supplier, String keywords, String shortDesc, String desc, String headTitle, String metaDesc,
                            String price, String currency, String taxClass, String priceWithTaxUSD, String priceWithTaxEUR){
    wd.findElement(By.cssSelector("a[href$='catalog']")).click();

    wd.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();

    selectCheckBox(By.cssSelector("input[name='status'][value='1']"));
    wd.findElement(By.cssSelector("input[name^='name']")).sendKeys(name);
    wd.findElement(By.cssSelector("input[name='code']")).sendKeys(code);
    unselectCheckBox(By.cssSelector("input[name^='categories']"));
    selectCheckBox(By.cssSelector((String.format("input[data-name='%s']", category))));
    selectValueFromList(By.cssSelector("select[name='default_category_id']"),defaultCategory);
    selectCheckBox(By.xpath(String.format("//td[contains(text(),'%s')]/../td[1]",productGroupsGender)));
    findElementAndTypeQuantity(wd.findElement(By.cssSelector("input[name='quantity']")), quantity);
    selectValueFromList(By.cssSelector("select[name='quantity_unit_id']"),quantityUnit);
    selectValueFromList(By.cssSelector("select[name='delivery_status_id']"),deliveryStatus);
    selectValueFromList(By.cssSelector("select[name='sold_out_status_id']"),soldOutStatus);
    wd.findElement(By.cssSelector("input[type='file']")).sendKeys(photo.getAbsolutePath());
    wd.findElement(By.cssSelector("input[name='date_valid_from']")).click();
    typeDateValue(dateValidFrom);
    wd.findElement(By.cssSelector("input[name='date_valid_to']")).click();
    typeDateValue(dateValidTo);

    wd.findElement(By.cssSelector("a[href$='information']")).click();

    selectValueFromList(By.cssSelector("select[name='manufacturer_id']"), manufacturer);
    selectValueFromList(By.cssSelector("select[name='supplier_id']"), supplier);
    wd.findElement(By.cssSelector("input[name='keywords']")).sendKeys(keywords);
    wd.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys(shortDesc);
    wd.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(desc);
    wd.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys(headTitle);
    wd.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys(metaDesc);

    wd.findElement(By.cssSelector("a[href$='prices']")).click();

    findElementAndTypeQuantity(wd.findElement(By.cssSelector("input[name='purchase_price']")), price);
    selectValueFromList(By.cssSelector("select[name = 'purchase_price_currency_code']"),currency);
    selectValueFromList(By.cssSelector("select[name = 'tax_class_id']"), taxClass);
    findElementAndTypeQuantity(wd.findElement(By.cssSelector("input[name='gross_prices[USD]']")), priceWithTaxUSD);
    findElementAndTypeQuantity(wd.findElement(By.cssSelector("input[name='gross_prices[EUR]']")), priceWithTaxEUR);

    wd.findElement(By.cssSelector("button[name='save']")).click();
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
  public void unselectCheckBox (By locator) {
    List<WebElement> elements =wd.findElements(locator);
    for (WebElement element : elements){
      Boolean checked = Boolean.valueOf(element.getAttribute("checked"));
      if(checked){
        element.click();
      }
    }
  }

  public void selectValueFromList (By locator, String value) {
    if(value!=null){
      Select select = new Select(wd.findElement(locator));
      if(!select.getFirstSelectedOption().getText().equals(value)){
        select.selectByVisibleText(value);
      }
    }
  }

  public void findElementAndTypeQuantity(WebElement element, String value) {
    new Actions(wd)
            .moveToElement(element)
            .click()
            .sendKeys(Keys.ARROW_RIGHT)
            .sendKeys(Keys.BACK_SPACE)
            .sendKeys(value)
            .perform();
  }

  public void typeDateValue (String DateString){
    new Actions(wd)
            .sendKeys(Keys.ARROW_LEFT)
            .sendKeys(Keys.ARROW_LEFT)
            .sendKeys(DateString)
            .sendKeys(Keys.TAB)
            .perform();
  }

  public void unhide(WebElement element) {
    String script = "arguments[0].style.opacity=1;"
            + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
            + "return true;";
    ((JavascriptExecutor) wd).executeScript(script, element);
  }

  public boolean isProductExist(String nameOfProduct) {
    wd.findElement(By.cssSelector("a[href$='catalog']")).click();
    wd.findElement(By.cssSelector("input[type='search']")).sendKeys(nameOfProduct + Keys.ENTER);
    List<WebElement> elements = wd.findElements(By.xpath(String.format("//a[contains(text(),'%s')]",nameOfProduct)));
    return elements.size()>0;
  }

  public boolean isElementNotPresent(By locator) {
    try {
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      return wd.findElements(locator).size()==0;
    } finally {
      wd.manage().timeouts().implicitlyWait(implicitlyWaitTimeOut, TimeUnit.SECONDS);
    }
  }

  public ExpectedCondition<String> anyWindowOtherThen(Set<String> oldWindowHandles){
    return new ExpectedCondition<String>() {
      @NullableDecl
      @Override
      public String apply(WebDriver webDriver) {
        Set<String> handles = webDriver.getWindowHandles();
        handles.removeAll(oldWindowHandles);
        return handles.size() > 0 ? handles.iterator().next():null;
      }
    };
  }

  public void stop() {
    wd.quit();
  }
  public String propertyValue(String propertyName) {
    return properties.getProperty(propertyName);
  }
}
