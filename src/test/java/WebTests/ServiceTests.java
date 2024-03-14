package WebTests;

import utilities.*;
import service_nsw.ServiceHomePage;
import service_nsw.ServiceApplyNumberPlatePage;
import service_nsw.ServiceLocateUsPage;
import utilities.DriverFactory;
import utilities.BrowserType;
import com.google.gson.Gson;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ServiceTests {

    WebDriver driver;
    BrowserType type = BrowserType.CHROME;
    ServiceHomePage serviceHomePageObj;
    ServiceApplyNumberPlatePage serviceApplyNumberPlatePageObj;
    ServiceLocateUsPage serviceLocateUsPageObj;
    Gson gson = new Gson();
    String pathOfData = System.getProperty("user.dir")+"/src/main/resource/DataFiles/Suburb.json";

    @BeforeMethod
    public void Setup(){

        driver = DriverFactory.getDriver(type);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        serviceHomePageObj = new ServiceHomePage(driver);

        serviceApplyNumberPlatePageObj = new ServiceApplyNumberPlatePage(driver);

        serviceLocateUsPageObj = new ServiceLocateUsPage(driver);
    }

    @AfterMethod
    public void TearDown(){

        driver.quit();

    }

//    Case 1
//    Scenario:  Validate that search SHOULD display desired result and navigate to page
//    Given      I have loaded Service NSW website
//    When       I will search for "Apply for a number plate"
//    Then       I will find that search on Service NSW website SHOULD display desired result
//    And        I will find that result link SHOULD navigate to page

    @Test
    public void Case1_VerifySearchResultDisplayed(){

        driver.get(serviceHomePageObj.PAGE_URL);

        assertTrue(driver.getTitle().contains(serviceHomePageObj.PAGE_TITLE));

        serviceHomePageObj.startYourSearch("Apply for a number plate");

        assertTrue(driver.getTitle().contains("Search Results | Service NSW"));

        assertTrue(serviceHomePageObj.isLinkApplyNumberPlateDisplayed());

        System.out.println(">>>> Successfully Displayed Apply for a number plate link in Results ");

        serviceHomePageObj.selectLinkApplyNumberPlate();

        assertTrue(serviceApplyNumberPlatePageObj.isApplyNumberPlatePageDisplayed());

        assertTrue(driver.getTitle().contains(serviceApplyNumberPlatePageObj.PAGE_TITLE));

        assertTrue(driver.getCurrentUrl().contains(serviceApplyNumberPlatePageObj.PAGE_URL));

        System.out.println(">>>> Successfully Displayed Apply for a number plate Page ");

        driver.close();

    }

//    Case 2
//    Scenario:  Validate that Service Center page SHOULD display location of service centre for given Suburb
//    Given      I have loaded Service Center - Locate US page
//    When       I will search service center for a Suburb
//    Then       I will find that Service Center page SHOULD display location of service centre for given Suburb
//
    @Test
    public void Case2_VerifySuburbServiceCentreDisplayed(){

        driver.get(serviceApplyNumberPlatePageObj.PAGE_URL);

        assertTrue(driver.getTitle().contains(serviceApplyNumberPlatePageObj.PAGE_TITLE));

        serviceApplyNumberPlatePageObj.selectLocateUs();

        assertTrue(driver.getTitle().contains(serviceLocateUsPageObj.PAGE_TITLE));

        assertTrue(driver.getCurrentUrl().contains(serviceLocateUsPageObj.PAGE_URL));


        try (Reader reader = new FileReader(pathOfData)) {

            //  Convert JSON to Java Object
            SuburbGson suburbGson = gson.fromJson(reader, SuburbGson.class);

            if (suburbGson != null) {

                for (Suburb sub : suburbGson.getSuburb()) {

                    System.out.println("Finding Service Center in "+sub.getSuburbName());

                    driver.navigate().refresh();

                    serviceLocateUsPageObj.startYourSearch(sub.getSuburbName());

                    assertTrue(serviceLocateUsPageObj.isServiceCenterDisplayed(sub.getServiceCenter()));

                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        driver.close();
    }
}
