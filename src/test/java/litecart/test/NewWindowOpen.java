package litecart.test;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class NewWindowOpen extends TestBase{
  @Test
  public void newWindowOpenTest(){
    app.loginAdmin();
    app.wait.until(ExpectedConditions.invisibilityOf(app.wd.findElement(By.cssSelector("div.notice.warnings"))));
    app.wd.findElement(By.cssSelector("a[href$='countries']")).click();
    app.wd.findElement(By.cssSelector("a.button")).click();
    String originalWindowHandle = app.wd.getWindowHandle();
    Set<String> oldWindowHandles = app.wd.getWindowHandles();
    List<WebElement> externalLinks = app.wd.findElements(By.cssSelector("i.fa.fa-external-link"));
    for(int i = 0; i < externalLinks.size(); i++) {
      externalLinks.get(i).click();
      String newWindowHandle = app.wait.until(app.anyWindowOtherThen(oldWindowHandles));
      app.wd.switchTo().window(newWindowHandle);
      app.wd.close();
      app.wd.switchTo().window(originalWindowHandle);
    }

  }
}
