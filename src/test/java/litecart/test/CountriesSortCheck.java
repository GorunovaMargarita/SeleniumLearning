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
    //а) проверить, что страны расположены в алфавитном порядке
    List<WebElement> countries = app.wd.findElements(By.cssSelector("tr.row td>a:not([title=Edit])"));
    ArrayList<String> names = new ArrayList<>();
    for (WebElement element : countries) {
      names.add(element.getText());
    }
    ArrayList<String> notSortedNames = new ArrayList<>();
    notSortedNames.addAll(names);
    Collections.sort(names);
    Assert.assertEquals(notSortedNames, names);
    //б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
    List<WebElement> countryRow = app.wd.findElements(By.cssSelector("tr.row"));
    for (int i=0; i < countryRow.size(); i++){
      if(!countryRow.get(i).findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0")) {
        countryRow.get(i).findElement(By.cssSelector("td > a")).click();
        List<WebElement> zones = app.wd.findElements(By.cssSelector("td:nth-child(3)>input[name^='zones']"));
        ArrayList<String> namesZones = new ArrayList<>();
        for (WebElement element : zones) {
          namesZones.add(element.getAttribute("value"));
        }
        ArrayList<String> notSortedZoneNames = new ArrayList<>();
        notSortedZoneNames.addAll(namesZones);
        Collections.sort(namesZones);

        Assert.assertEquals(notSortedZoneNames, namesZones);

        zones.clear();
        namesZones.clear();
        notSortedZoneNames.clear();
        app.wd.navigate().back();
        countryRow = app.wd.findElements(By.cssSelector("tr.row"));
      }
    }
  }
}
