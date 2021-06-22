package litecart.Pages;

import litecart.appmanager.ApplicationManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String mainPageURL) {
        driver.get(mainPageURL);
    }

    @FindBy(css = "#box-most-popular a.link")
    public List<WebElement> productLinks;

    @FindBy(css = "a.link[href$='checkout']")
    public WebElement basketPageLink;

    @FindBy(css = "i.fa.fa-home")
    public WebElement homeLink;

}
