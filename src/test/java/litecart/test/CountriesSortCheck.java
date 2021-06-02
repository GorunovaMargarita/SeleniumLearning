package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CountriesSortCheck extends TestBase {
  @Test
  public void CountriesSortCheckTest(){
    app.loginAdmin();
    app.wd.findElement(By.xpath("//span[contains(.,'Countries')]")).click();
    List<WebElement> countries = app.wd.findElements(By.cssSelector("tr.row td>a:not([title=Edit])"));
    ArrayList<String> names = new ArrayList<>();
    for (WebElement element : countries) {
      names.add(element.getText());
    }
    ArrayList<String> notSortedNames = new ArrayList<>();
    notSortedNames.addAll(names);
    Collections.sort(names);
    Assert.assertEquals(notSortedNames, names);
  }
}
