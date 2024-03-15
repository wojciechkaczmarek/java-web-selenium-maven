package service_nsw;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utilities.BaseTest;

import java.util.List;

public class ServiceLocateUsPage extends ServicePage {

    //final variables
    public static final String PAGE_TITLE = "Find a Service NSW location | Service NSW";
    public static final String PAGE_URL = "https://www.service.nsw.gov.au/service-centre";

    @FindBy(xpath="/html/body/div[1]/header/div/h1")
    WebElement headingTextLocateUs;

    @FindBy(xpath="//*[@id='locatorTextSearch']")
    WebElement locationTextFieldSearchBox;

    @FindBy(xpath="//*[@id='locator']//*[contains(@class,'actions')]//button")
    WebElement locationButtonSearch;

    By locationListView = By.xpath("//*[@id='locatorListView']");

    By listOfLocationResult = By.xpath("//*[@class='locator__results-list']/div");

    By suggestionLocation = By.xpath("//*[@id='locatorAutocomplete']");

    //Page Class Constructor
    public ServiceLocateUsPage(BaseTest caller) {
        super(caller);
    }

    //Action Methods
    public void startLocationSearch(String suburbName) {

        insertLocationSearchText(suburbName);

        pressLocationSearchButton();
    }

    protected void insertLocationSearchText(String searchText) {

        locationTextFieldSearchBox.clear();

        locationTextFieldSearchBox.sendKeys(searchText);
    }

    protected void pressLocationSearchButton() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locationButtonSearch));

            locationButtonSearch.click();

        } catch (Exception ex) {

            System.out.println(ex);
        }
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
