package service_nsw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utilities.BaseTest;

import java.util.NoSuchElementException;

public class ServiceApplyNumberPlatePage extends BasePage {

    //final variables
    public static final String PAGE_TITLE = "Apply for a number plate | Service NSW";
    public static final String PAGE_URL = "https://www.service.nsw.gov.au/transaction/apply-number-plate";

    @FindBy(xpath="//*[@id='block-system-main']/div/div/div/div[1]/div[3]/div/h1")
    WebElement headingTextApplyForNumberPlate;

    By buttonLocateUs = By.linkText("Find locations");

    By buttonOrderOnline = By.xpath("//a[@href='/node/69081']");

    //Page Class Constructor
    public ServiceApplyNumberPlatePage(BaseTest caller) {
        super(caller);
    }

    //Action Methods

    public boolean isApplyNumberPlatePageDisplayed() {
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(headingTextApplyForNumberPlate),
                    ExpectedConditions.visibilityOfElementLocated(buttonOrderOnline)));
            return true;
        } catch (NoSuchElementException e ) {

            System.out.println("ERROR > Apply for Number Plate Page is Not Displayed");

            return false;

        } catch (Exception ex) {

            return false;

        }
    }

    public void selectLocateUs() {

        driver.findElement(buttonLocateUs).click();

    }
}
