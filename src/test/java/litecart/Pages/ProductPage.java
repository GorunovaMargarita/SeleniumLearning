package litecart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
  public ProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver,this);
  }
  @FindBy(css="select")
  public WebElement size;
  @FindBy(css="button[name=add_cart_product")
  public WebElement addProductIntoBasketButton;

  public void selectAnySize() {
    Select select = new Select(size);
    select.selectByIndex(1);
  }


}
