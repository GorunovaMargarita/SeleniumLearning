package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class StickerCheck extends TestBase {
    @Test
    public void stickerCheckTest(){
        app.wd.get(app.propertyValue("web.baseUrl"));
        List<WebElement> images = app.wd.findElements(By.cssSelector("div.image-wrapper"));
        for(WebElement img : images){
            List<WebElement> stickers= img.findElements(By.cssSelector("div.sticker"));
            Assert.assertTrue(stickers.size()==1);
            Assert.assertFalse(stickers.get(0).getText().trim().isEmpty());
        }
    }
}
