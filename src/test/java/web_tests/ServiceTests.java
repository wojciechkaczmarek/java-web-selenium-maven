package web_tests;

import utilities.*;
import service_nsw.ServiceHomePage;
import service_nsw.ServiceApplyNumberPlatePage;
import service_nsw.ServiceLocateUsPage;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ServiceTests extends BaseTest {




    Gson gson = new Gson();
    String pathOfData = System.getProperty("user.dir")+"/src/main/resource/DataFiles/Suburb.json";

    //    Case 1
//    Scenario:  Validate that search SHOULD display desired result and navigate to page
//    Given      I have loaded Service NSW website
//    When       I will search for "Apply for a number plate"
//    Then       I will find that search on Service NSW website SHOULD display desired result
//    And        I will find that result link SHOULD navigate to page

    @Test
    public void Case1_VerifySearchResultDisplayed() {
        ServiceHomePage serviceHomePage;
        ServiceApplyNumberPlatePage serviceApplyNumberPlatePage;

        driver.get(ServiceHomePage.PAGE_URL);
        serviceHomePage = new ServiceHomePage(this);
        assertTrue(driver.getTitle().contains(ServiceHomePage.PAGE_TITLE));

        serviceHomePage.startYourSearch("Apply for a number plate");

        assertTrue(driver.getTitle().contains("Search Results | Service NSW"));
        assertTrue(serviceHomePage.isLinkApplyNumberPlateDisplayed());
        System.out.println(">>>> Successfully Displayed Apply for a number plate link in Results ");

        serviceHomePage.selectLinkApplyNumberPlate();
        serviceApplyNumberPlatePage = new ServiceApplyNumberPlatePage(this);
        assertTrue(serviceApplyNumberPlatePage.isApplyNumberPlatePageDisplayed());
        assertTrue(driver.getTitle().contains(ServiceApplyNumberPlatePage.PAGE_TITLE));
        assertTrue(driver.getCurrentUrl().contains(ServiceApplyNumberPlatePage.PAGE_URL));
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
    public void Case2_VerifySuburbServiceCentreDisplayed() {
        ServiceApplyNumberPlatePage serviceApplyNumberPlatePage;
        ServiceLocateUsPage serviceLocateUsPage;

        driver.get(ServiceApplyNumberPlatePage.PAGE_URL);
        serviceApplyNumberPlatePage = new ServiceApplyNumberPlatePage(this);
        assertTrue(driver.getTitle().contains(ServiceApplyNumberPlatePage.PAGE_TITLE));

        serviceApplyNumberPlatePage.selectLocateUs();
        serviceLocateUsPage = new ServiceLocateUsPage(this);
        assertTrue(driver.getTitle().contains(ServiceLocateUsPage.PAGE_TITLE));
        assertTrue(driver.getCurrentUrl().contains(ServiceLocateUsPage.PAGE_URL));

        try (Reader reader = new FileReader(pathOfData)) {

            //  Convert JSON to Java Object
            SuburbGson suburbGson = gson.fromJson(reader, SuburbGson.class);

            if (suburbGson != null) {

                for (Suburb sub : suburbGson.getSuburb()) {

                    System.out.println("Finding Service Center in "+sub.getSuburbName());

                    driver.navigate().refresh();

                    serviceLocateUsPage.startYourSearch(sub.getSuburbName());

                    assertTrue(serviceLocateUsPage.isServiceCenterDisplayed(sub.getServiceCenter()));

                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        driver.close();
    }
}
