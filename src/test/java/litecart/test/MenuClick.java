package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenuClick extends TestBase {

    @Test
    public void MenuClickTest() {
        app.loginAdmin();
        List<WebElement> ppMenu = app.wd.findElements(By.cssSelector("span.name"));
        for (int i = 0; i < ppMenu.size(); i++) {
            String ppMainMenuName = ppMenu.get(i).getText();
            ppMenu.get(i).click();
            List<WebElement> pppMenu = app.wd.findElements(By.cssSelector("ul.docs>li"));
            for (int j = 0; j < pppMenu.size(); j++) {
                String pppMenuName = pppMenu.get(j).getText();
                pppMenu.get(j).click();
                Assert.assertTrue(app.wd.findElement(By.cssSelector("div#body h1")).isDisplayed());
                //Assert.assertEquals(pppMenuName,app.wd.findElement(By.cssSelector("div#body h1")).getText());
                System.out.println(ppMainMenuName + " -> " + pppMenuName + " OK!");
                pppMenu = app.wd.findElements(By.cssSelector("ul.docs>li"));
            }
            app.wd.findElement(By.cssSelector("img[title='My Store']")).click();
            ppMenu = app.wd.findElements(By.cssSelector("span.name"));
        }
    }

}
