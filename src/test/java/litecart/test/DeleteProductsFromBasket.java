package litecart.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DeleteProductsFromBasket extends TestBase {
    @Test
    public void deleteProductsFromBasketTest() {
        int nedeedDuckCount = 3;
        app.wd.get(app.propertyValue("web.baseUrl"));
        int duckCountInBasket = 0;
        do {
            WebElement firstDuck = app.wd.findElements(By.cssSelector("#box-most-popular a.link")).get(0);
            firstDuck.click();
            WebElement quantity = app.wd.findElement(By.cssSelector("span.quantity"));

            duckCountInBasket = Integer.parseInt(quantity.getText());
            app.wd.findElement(By.cssSelector("button[name=add_cart_product")).click();
            app.wait.until(ExpectedConditions.attributeToBe(quantity,"textContent",String.valueOf(duckCountInBasket+1)));
            duckCountInBasket++;
            app.wd.findElement(By.cssSelector("i.fa.fa-home")).click();
        } while (duckCountInBasket < nedeedDuckCount);
        app.wd.findElement(By.cssSelector("a.link[href$='checkout']")).click();
        for (int i = 0; i < nedeedDuckCount; i++)
        {
            WebElement table = app.wd.findElement(By.cssSelector("table.dataTable.rounded-corners"));
            app.wd.findElement(By.cssSelector("button[value='Remove']")).click();
            app.wait.until(ExpectedConditions.stalenessOf(table));
        }
    }
}
