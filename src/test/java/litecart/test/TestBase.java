package litecart.test;

import litecart.appmanager.ApplicationManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class TestBase {

  protected static final ApplicationManager app
          =new ApplicationManager(BrowserType.FIREFOX);

  @Before
  public void start() throws IOException {
    app.init();
  }

  @After
  public void stop() {
    app.stop();
  }
}
