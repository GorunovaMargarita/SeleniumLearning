package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static java.util.Collections.*;


public class CountriesSortCheck extends TestBase {
  @Test
  public void CountriesSortCheckTest(){
    app.loginAdmin();
    app.wd.findElement(By.xpath("//a[contains(.,'Countries')]")).click();
    List<WebElement> countries = app.wd.findElements(By.cssSelector("tr.row td>a:not([title=Edit])"));
    //List<String> countryNames;
    ArrayList<String> names = new ArrayList<>();
    for (WebElement element : countries) {
      names.add(element.getText());
      System.out.println(names);
    }
    TreeSet<String> sortedNames = new TreeSet<>();
    sortedNames.addAll(names);

   /* TreeSet<String> countryNamesSorted = null;
    for (int i = 0; i < countries.size(); i++) {
      countryNamesSorted.add(countries.get(i).getText());
    }*/
    Assert.assertEquals(names, sortedNames);
  }
}
