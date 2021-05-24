package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

  public WebDriver driver;
  public WebDriverWait wait;

  @Before
  public void start(){
    //driver = new ChromeDriver();
   // driver = new FirefoxDriver();
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
   // driver = new InternetExplorerDriver(caps);
    driver = new ChromeDriver(caps);
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
