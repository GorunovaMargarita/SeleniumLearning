package litecart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class BasketPage extends Page{
  public BasketPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver,this);
  }
  @FindBy(css="span.quantity")
  public WebElement quantityInto;

  @FindBy(css="a.image-wrapper.shadow")
  public List<WebElement> quantityUnicProductsInto;

  @FindBy(css="button[value='Remove']")
  public WebElement removeButton;

  @FindBy(css="table.dataTable.rounded-corners")
  public WebElement productsTable;

  @FindBy(css="p em")
  public WebElement emptyBasketMessage;
}
