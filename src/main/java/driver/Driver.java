package driver;

import browser.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class Driver {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(BrowserFactory.initDriver());
        }
        return driver.get();
    }

    public static void turnDriverDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
