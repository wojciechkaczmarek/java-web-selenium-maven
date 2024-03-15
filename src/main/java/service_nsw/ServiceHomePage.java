package service_nsw;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utilities.BaseTest;

import java.util.NoSuchElementException;

public class ServiceHomePage extends ServicePage {

    //final variables
    public static final String PAGE_TITLE = "Home | Service NSW";
    public static final String PAGE_URL = "https://www.service.nsw.gov.au/";

    By linkApplyNumberPlate = By.xpath("//a[@href='/transaction/apply-number-plate']");


    //Page Class Constructor
    public ServiceHomePage(BaseTest caller) {
        super(caller);
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

}
