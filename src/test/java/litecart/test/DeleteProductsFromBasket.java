package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class DeleteProductsFromBasket extends TestBase {
    @Test
    public void deleteProductsFromBasketTest() {
        int neededDuckCount = 3;
        int duckCountInBasket;
        app.goToBaseUrl();
        do {
            duckCountInBasket = app.getDuckQuantityInBasket();
            app.addDuckIntoBasket();
            duckCountInBasket++;
            app.goHome();
        } while (duckCountInBasket < neededDuckCount);
        app.goToBasket();
        app.clearBasket();
    }


}
