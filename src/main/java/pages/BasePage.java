package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BasePage {
    private static final long WAIT_SECONDS = 15;
    private static final long POLLING_MS = 500;
    private static final List<Class<? extends Throwable>> IGNORED = Arrays.asList(
            NoSuchElementException.class,
            StaleElementReferenceException.class);

    //local webdriver variable
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_SECONDS))
                .pollingEvery(Duration.ofMillis(POLLING_MS))
                .ignoreAll(IGNORED);

        PageFactory.initElements(driver, this);
    }
}
