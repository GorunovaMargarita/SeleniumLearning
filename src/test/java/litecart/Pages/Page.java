package litecart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

  protected WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
  }
}