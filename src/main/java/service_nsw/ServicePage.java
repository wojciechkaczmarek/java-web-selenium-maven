package service_nsw;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utilities.BaseTest;

public class ServicePage extends BasePage {
    By buttonLocateUsBy = By.xpath("//header//a[contains(@href,'/service-centre')]");

    @FindBy(xpath="//*[contains(@class,'GlobalHeader')]//form//input[@placeholder='Search']")
    WebElement globalTextFieldSearchBox;

    @FindBy(xpath="//*[contains(@class,'GlobalHeader')]//form//button")
    WebElement globalButtonSearch;

    public ServicePage(BaseTest caller) {
        super(caller);
    }

    //Action Methods
    protected void insertGlobalSearchText(String searchText) {

        globalTextFieldSearchBox.clear();

        globalTextFieldSearchBox.sendKeys(searchText);
    }

    public void startGlobalSearch(String searchText) {

        insertGlobalSearchText(searchText);

        pressGlobalSearchButton();
    }

    protected void selectSearchText() {

        globalTextFieldSearchBox.sendKeys(Keys.ARROW_DOWN);

        globalTextFieldSearchBox.sendKeys(Keys.ENTER);
    }

    protected void pressGlobalSearchButton() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(globalButtonSearch));

            globalButtonSearch.click();

        } catch (Exception ex) {

            System.out.println(ex);
        }
    }

    public void selectLocateUs() {

        driver.findElement(buttonLocateUsBy).click();
    }
}
