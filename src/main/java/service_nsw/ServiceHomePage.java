package service_nsw;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.NoSuchElementException;

public class ServiceHomePage extends BasePage {

    //final variables
    public static final String PAGE_TITLE = "Home | Service NSW";
    public static final String PAGE_URL = "https://www.service.nsw.gov.au/";

    @FindBy(xpath="//*[@id=\"edit-contains\"]")
    WebElement textFieldSearchBox;

    @FindBy(xpath="//a[@href='/service-centre']")
    WebElement buttonLocateUs;

    @FindBy(xpath="//*[@id=\"edit-submit-site-search\"]")
    WebElement buttonSearch;

    By linkApplyNumberPlate = By.xpath("//a[@href='/transaction/apply-number-plate']");


    //Page Class Constructor
    public ServiceHomePage(WebDriver driver) {
        super(driver);
    }

    //Action Methods
    private void insertSearchText(String searchText) {

        textFieldSearchBox.clear();

        textFieldSearchBox.sendKeys(searchText);
    }

    private void selectSearchText() {

        textFieldSearchBox.sendKeys(Keys.ARROW_DOWN);

        textFieldSearchBox.sendKeys(Keys.ENTER);
    }

    private void pressSearchButton() {

        buttonSearch.click();
    }

    public void startYourSearch(String searchText) {

        insertSearchText(searchText);

        pressSearchButton();
    }

    public boolean isLinkApplyNumberPlateDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(linkApplyNumberPlate));
            return true;
        } catch (NoSuchElementException e ) {

            System.out.println("ERROR > Apply for Number Plate Not Displayed in Search Results ");

            return false;

        } catch (Exception ex) {

            return false;
        }
    }

    public void selectLinkApplyNumberPlate() {

        driver.findElement(linkApplyNumberPlate).click();

    }
    public void selectLocateUs() {

        buttonLocateUs.click();

    }

}
