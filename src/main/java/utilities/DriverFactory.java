package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver getDriver(BrowserType type) {
        return switch (type) {
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                yield new FirefoxDriver(options);
            }
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                yield new ChromeDriver(options);
            }
        };
    }
}
