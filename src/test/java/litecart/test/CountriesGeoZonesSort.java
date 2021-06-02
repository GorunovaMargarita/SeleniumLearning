package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CountriesGeoZonesSort extends TestBase {
  @Test
  public void CountriesGeoZonesSortCheckTest() {
    app.loginAdmin();
    app.wd.findElement(By.xpath("//span[contains(.,'Geo Zones')]")).click();
    List<WebElement> geoZonesCountry = app.wd.findElements(By.cssSelector("tr.row a:not([title=Edit]"));
    for (int j = 0; j < geoZonesCountry.size(); j++) {
      geoZonesCountry.get(j).click();
      List<WebElement> geoZones = app.wd.findElements(By.cssSelector("select:not(.select2-hidden-accessible) option[selected=selected]"));
      ArrayList<String> geoZoneName = new ArrayList<>();
      for (WebElement element : geoZones) {
        geoZoneName.add(element.getText());
      }
      ArrayList<String> notSortedGeoZoneNames = new ArrayList<>();
      notSortedGeoZoneNames.addAll(geoZoneName);
      Collections.sort(geoZoneName);
      Assert.assertEquals(notSortedGeoZoneNames, geoZoneName);
      app.wd.navigate().back();
      geoZones.clear();
      geoZoneName.clear();
      notSortedGeoZoneNames.clear();

      geoZonesCountry = app.wd.findElements(By.cssSelector("tr.row a:not([title=Edit]"));
    }
  }
}
