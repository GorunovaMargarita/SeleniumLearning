package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductCheck extends TestBase {
  @Test
  public void ProductCheckTest() {
    app.wd.get(app.propertyValue("web.baseUrl"));
    WebElement firstDuck = app.wd.findElements(By.cssSelector("#box-campaigns a.link")).get(0);
    String name = firstDuck.findElement(By.cssSelector("div.name")).getText();
    String price = firstDuck.findElement(By.cssSelector("s.regular-price")).getText();
    String priceCampaign = firstDuck.findElement(By.cssSelector("strong.campaign-price")).getText();
    String color1 = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
    Color colorPrice = parse(firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("color"));
    Color colorPriceCampaign = parse(firstDuck.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"));
    String stylePrice = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");
    String stylePriceCampaign = firstDuck.findElement(By.cssSelector("div.price-wrapper")).getCssValue("font-weight");
    String fontSizePrice = firstDuck.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
    String fontSizeCampaignPrice = firstDuck.findElement(By.cssSelector("div.price-wrapper")).getCssValue("font-size");
    System.out.println("Имя: " + name + "; Цена: " + price + "; Акционная цена " + priceCampaign + "\n" +
            "Цвет цены: " + colorPrice + "; текст стиль: " + stylePrice + ", размер шрифта: " + fontSizePrice + "\n" +
            "Цвет акционной цены: " + colorPriceCampaign + "; текст жирность: " + stylePriceCampaign + ", размер шрифта: " + fontSizeCampaignPrice);
    firstDuck.click();
    String nameOnProductForm = app.wd.findElement(By.cssSelector("h1")).getText();
    String priceOnProductForm = app.wd.findElement(By.cssSelector("s.regular-price")).getText();
    String priceCampaignOnProductForm = app.wd.findElement(By.cssSelector("strong.campaign-price")).getText();
    Color colorPriceOnProductForm = parse(app.wd.findElement(By.cssSelector("s.regular-price")).getCssValue("color"));
    Color colorPriceCampaignOnProductForm = parse(app.wd.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"));
    String stylePriceOnProductForm = app.wd.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");
    String stylePriceCampaignOnProductForm = app.wd.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
    String fontSizePriceOnProductForm = app.wd.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
    String fontSizePriceCampaignOnProductForm = app.wd.findElement(By.cssSelector("div.price-wrapper")).getCssValue("font-size");

    System.out.println("Данные на странице продукта." + "\n" +
            "Имя: " + nameOnProductForm + "; Цена: " + priceOnProductForm + "; Акционная цена " + priceCampaignOnProductForm + "\n" +
            "Цвет цены: " + colorPriceOnProductForm + "; текст стиль: " + stylePriceOnProductForm + ", размер шрифта: " + fontSizePriceOnProductForm + "\n" +
            "Цвет акционной цены: " + colorPriceCampaignOnProductForm + "; текст жирность: " + stylePriceCampaignOnProductForm + ", размер шрифта: " + fontSizePriceCampaignOnProductForm);
    //совпадает название товара
    Assert.assertEquals(name, nameOnProductForm);
    //совпадает цена без скидки
    Assert.assertEquals(price, priceOnProductForm);
    //совпадает цена со скидкой
    Assert.assertEquals(priceCampaign, priceCampaignOnProductForm);
    //цена без скидки зачёркнута. Если свойство не вернулось (как в IE, не проверяем)
    if (!stylePrice.equals("")) {
      Assert.assertEquals("line-through", stylePrice);
      Assert.assertEquals("line-through", stylePriceOnProductForm);
    } else {
      System.out.println("Свойство text-decoration-line не получено");
    }
    //цена со скидкой крупнее (больше размер шрифта), чем цена без скидки
    Assert.assertTrue(Double.parseDouble(fontSizeCampaignPrice.substring(0, fontSizeCampaignPrice.indexOf("p"))) > Double.parseDouble(fontSizePrice.substring(0, fontSizePrice.indexOf("p"))));
    Assert.assertTrue(Double.parseDouble(fontSizePriceCampaignOnProductForm.substring(0, fontSizePriceCampaignOnProductForm.indexOf("p"))) > Double.parseDouble(fontSizePriceOnProductForm.substring(0, fontSizePriceOnProductForm.indexOf("p"))));
    //цена со скидкой жирным
    Assert.assertEquals(700, Integer.parseInt(stylePriceCampaign));
    Assert.assertEquals(700, Integer.parseInt(stylePriceCampaignOnProductForm));
    //цена без скидки серая (r=g=b)
    Assert.assertTrue(colorPrice.getRed() == colorPrice.getBlue() && colorPrice.getBlue() == colorPrice.getGreen());
    Assert.assertTrue(colorPriceOnProductForm.getRed() == colorPriceOnProductForm.getBlue() && colorPriceOnProductForm.getBlue() == colorPriceOnProductForm.getGreen());
    //цена со скидкой красная (g=b=0)
    Assert.assertTrue(colorPriceCampaign.getBlue() == 0 && colorPriceCampaign.getGreen() == 0);
    Assert.assertTrue(colorPriceCampaignOnProductForm.getBlue() == 0 && colorPriceCampaignOnProductForm.getGreen() == 0);
  }

  public static Color parse(String input) {
    //для IE и CHROME
    Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
    //для Firefox
    Pattern c1 = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
    Matcher m = c.matcher(input);
    Matcher m1 = c1.matcher(input);

    if (m.matches()) {
      return new Color(Integer.valueOf(m.group(1)),  // r
              Integer.valueOf(m.group(2)),  // g
              Integer.valueOf(m.group(3))); // b
    }
    if (m1.matches()) {
      return new Color(Integer.valueOf(m1.group(1)),  // r
              Integer.valueOf(m1.group(2)),  // g
              Integer.valueOf(m1.group(3))); // b
    } else
      return null;
  }
}
