package litecart.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCheck extends TestBase {
  @Test
  public void ProductCheckTest(){
    app.wd.get(app.propertyValue("web.baseUrl"));
    WebElement firstDuck = app.wd.findElements(By.cssSelector("#box-campaigns a.link")).get(0);
    String name = firstDuck.findElement(By.cssSelector("div.name")).getText();
    String price = firstDuck.findElement(By.cssSelector("s.regular-price")).getText();
    String priceCampaign = firstDuck.findElement(By.cssSelector("strong.campaign-price")).getText();
    String colorPrice = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
    String colorPriceCampaign = firstDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
    String stylePrice = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");
    String stylePriceCampaign = firstDuck.findElement(By.cssSelector("div.price-wrapper")).getCssValue("font-weight");
    String fontSizePrice = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
    String fontSizeCampaign = firstDuck.findElement(By.cssSelector("div.price-wrapper")).getCssValue("font-size");
    System.out.println("Имя: " + name + "; Цена: " + price + "; Акционная цена " + priceCampaign + "\n" +
                      "Цвет цены: " + colorPrice + "; текст стиль: " + stylePrice + ", размер шрифта: " + fontSizePrice +"\n" +
                      "Цвет акционной цены: " + colorPriceCampaign +"; текст жирность: " + stylePriceCampaign+ ", размер шрифта: " + fontSizeCampaign);
    firstDuck.click();
  }
}
