package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFirstTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start(){
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver,10);
  }

  @Test
  public void myFirstTest() {
    driver.get("https://ya.ru");
    driver.findElement(By.tagName("input")).sendKeys("webdriver");
    driver.findElement(By.xpath("//input[@type='submit']")).click();

  }
  @After
  public void stop() {
    driver.quit();
    driver=null;
  }


}
