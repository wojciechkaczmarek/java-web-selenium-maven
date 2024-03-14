package service_nsw;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class ServiceLocateUsPage extends BasePage {

    //final variables
    public static final String PAGE_TITLE = "Find a Service NSW location | Service NSW";
    public static final String PAGE_URL = "https://www.service.nsw.gov.au/service-centre";

    @FindBy(xpath="/html/body/div[1]/header/div/h1")
    WebElement headingTextLocateUs;

    @FindBy(xpath="//*[@id=\"locatorTextSearch\"]")
    WebElement textFieldSearchBox;

    @FindBy(xpath="//*[@id='locator']/div/div/form/div/div[2]/button")
    WebElement buttonSearch;

    By buttonLocateUs = By.linkText("Locate us");

    By locationListView = By.xpath("//*[@id='locatorListView']");

    By listOfLocationResult = By.xpath("//*[@class='locator__results-list']/div");

    By suggestionLocation = By.xpath("//*[@id=\"locatorAutocomplete\"]");

    //Page Class Constructor
    public ServiceLocateUsPage(WebDriver driver) {
        super(driver);
    }

    //Action Methods
    private void insertSearchText(String searchText) {

        textFieldSearchBox.clear();

        textFieldSearchBox.sendKeys(searchText);
    }

    private void pressSearchButton() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(buttonSearch));

            buttonSearch.click();

        } catch (Exception ex) {

            System.out.println(ex);
        }
    }

    private void selectSearchText() {

        textFieldSearchBox.sendKeys(Keys.ARROW_DOWN);

        textFieldSearchBox.sendKeys(Keys.ENTER);
    }

    public void startYourSearch(String searchText) {

        insertSearchText(searchText);

        pressSearchButton();
    }

    public void selectLocateUs() {

        driver.findElement(buttonLocateUs).click();
    }

    public boolean isServiceCenterDisplayed(String serviceCentre) {

        boolean result = false;

        try {
            List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfLocationResult));

            System.out.println("> No of Location Found = " + results.size());

            for (WebElement elementLocation : results) {

                if (elementLocation.findElement(By.xpath(" . //a")).getText().contains(serviceCentre)) {

                    System.out.println(">>> Found " + elementLocation.findElement(By.xpath(" . //a")).getText());

                    result = true;

                }
            }
        } catch (Exception ex) {

            System.out.println(ex);
        }
        return result;
    }
}
