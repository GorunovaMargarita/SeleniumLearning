package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    }
  }

  @Before
  public void start(){
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
   // driver = new InternetExplorerDriver(caps);
   // driver = new FirefoxDriver(caps);
    driver = new EventFiringWebDriver(new ChromeDriver(caps));
    driver.register(new MyListener());
    System.out.println(((HasCapabilities) driver).getCapabilities());
    wait = new WebDriverWait(driver,10);
  }

  @Test
  public void myFirstTest() {
    driver.get("https://ya.ru");
    driver.findElement(By.xpath("//input[@name='text']")).sendKeys("webdriver");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    wait.until(titleContains("webdriver — Яндекс"));
  }
  @After
  public void stop() {
    driver.quit();
    driver=null;
  }


}
