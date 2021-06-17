package ru.stqa.training.selenium;


import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

  public EventFiringWebDriver driver;
  public WebDriverWait wait;

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by +  " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      //Делаем скриншот, если не нашли элемент
      File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen-" + System.currentTimeMillis() + ".png");
      try {
        Files.copy(tmp,screen);
      }catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen);
    }
  }

  @Before
  public void start(){
    //DesiredCapabilities caps = new DesiredCapabilities();
   // caps.setCapability("unexpectedAlertBehaviour", "dismiss");
   // driver = new InternetExplorerDriver(caps);
   // driver = new FirefoxDriver(caps);

    ChromeOptions opt = new ChromeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    opt.setExperimentalOption("w3c", false);
    driver = new EventFiringWebDriver(new ChromeDriver(opt));
    driver.register(new MyListener());
    System.out.println(((HasCapabilities) driver).getCapabilities());
    wait = new WebDriverWait(driver,10);
  }

  @Test
  public void myFirstTest() {
    driver.get("https://ya.ru");
    driver.findElement(By.xpath("//input[@name='text']")).sendKeys("webdriver");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
      System.out.println(l);
    }
    wait.until(titleContains("webdriver — Яндекс"));
  }

  @Test
  public void getBrowserLogs() {
    driver.get("http://selenium2.ru");
    //System.out.println(driver.manage().logs().getAvailableLogTypes());
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
    driver.manage().logs().get("performance").forEach(l-> System.out.println(l));
    driver.quit();
  }
  @After
  public void stop() {
    driver.quit();
    driver=null;
  }


}
