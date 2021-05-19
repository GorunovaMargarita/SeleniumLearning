package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

  public WebDriver driver;
  public WebDriverWait wait;

  @Before
  public void start(){
    //driver = new ChromeDriver();
    driver = new FirefoxDriver();
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
