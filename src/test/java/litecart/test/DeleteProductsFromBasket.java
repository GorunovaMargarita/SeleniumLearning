package litecart.test;

import org.junit.Test;



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
