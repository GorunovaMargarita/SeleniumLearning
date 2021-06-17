package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class CheckConsoleMessageAtProductPages extends TestBase{
  @Test
  public void checkConsoleMessageAtProductPagesTest (){
    app.loginAdmin();
    app.wd.get(app.propertyValue("web.productUrl"));
    List<WebElement> products = app.wd.findElements(By.cssSelector("tr.row a:not([title=Edit])[href*='product']"));
    for (int i = 0; i < products.size(); i++){
      products.get(i).click();
      for (LogEntry l : app.wd.manage().logs().get("browser").getAll()) {
        System.out.println(l);
        Assert.assertNull(l);
      }
      app.wd.navigate().back();
      products = app.wd.findElements(By.cssSelector("tr.row a:not([title=Edit])[href*='product']"));
    }
    app.wd.quit();
  }
}
