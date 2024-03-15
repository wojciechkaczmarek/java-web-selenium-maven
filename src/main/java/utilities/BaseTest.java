package utilities;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import service_nsw.ServiceApplyNumberPlatePage;
import service_nsw.ServiceHomePage;
import service_nsw.ServiceLocateUsPage;
import utilities.BrowserType;
import utilities.DriverFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BaseTest {
    private static final long WAIT_SECONDS = 15;
    private static final long POLLING_MS = 500;
    private static final List<Class<? extends Throwable>> IGNORED = Arrays.asList(
            NoSuchElementException.class,
            StaleElementReferenceException.class);

    @Getter
    protected WebDriver driver;
    protected BrowserType type;
    @Getter
    protected FluentWait<WebDriver> wait;

    @BeforeMethod
    @Parameters({"browserType"})
    public void setup(@Optional("chrome") String browserType) {
        type = BrowserType.valueOf(browserType);
        driver = DriverFactory.getDriver(type);
        driver.manage().window().maximize();

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_SECONDS))
                .pollingEvery(Duration.ofMillis(POLLING_MS))
                .ignoreAll(IGNORED);

        //  Implicit waits are not recommended in most scenarios;
        //  see https://www.selenium.dev/documentation/webdriver/waits/
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
