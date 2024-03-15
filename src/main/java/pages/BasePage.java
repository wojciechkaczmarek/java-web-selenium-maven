package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import utilities.BaseTest;

public class BasePage {

    //local webdriver variable
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;

    protected BasePage(BaseTest caller) {
        this.driver = caller.getDriver();
        this.wait = caller.getWait();

        PageFactory.initElements(driver, this);
    }
}
