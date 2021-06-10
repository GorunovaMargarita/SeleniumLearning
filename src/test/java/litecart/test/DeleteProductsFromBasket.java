package litecart.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DeleteProductsFromBasket extends TestBase {
    @Test
    public void deleteProductsFromBasketTest() {
        app.wd.get(app.propertyValue("web.baseUrl"));
        WebElement firstDuck = app.wd.findElements(By.cssSelector("#box-most-popular a.link")).get(0);
        firstDuck.click();
        WebElement cartImg = app.wd.findElement(By.cssSelector("#cart img"));
        WebElement quantity = app.wd.findElement(By.cssSelector("span.quantity"));
        String duckCountInBasket = quantity.getText();
        app.wd.findElement(By.cssSelector("button[name=add_cart_product")).click();
        app.wait.until(ExpectedConditions.stalenessOf(quantity));
       // app.wait.until(stalenessOf(cartImg));
        //app.wait.until(ExpectedConditions.stalenessOf(cartImg));
        //app.wait.until(stalenessOf(quantity));
        app.wd.findElement(By.cssSelector("i.fa.fa-home")).click();
    }
}
