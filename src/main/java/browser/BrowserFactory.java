package browser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;
        String remoteURL = System.getenv("SELENIUM_REMOTE_URL");
        if (remoteURL != null && !remoteURL.isEmpty()) {
            switch (Browser.fromString(browser)) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    addHeadlessArguments(chromeOptions);
                    try {
                        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    addHeadlessArguments(firefoxOptions);
                    try {
                        driver = new RemoteWebDriver(new URL(remoteURL), firefoxOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    addHeadlessArguments(edgeOptions);
                    try {
                        driver = new RemoteWebDriver(new URL(remoteURL), edgeOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        } else {
            switch (Browser.fromString(browser)) {
                case CHROME -> driver = new ChromeDriver();
                case FIREFOX -> driver = new FirefoxDriver();
                case EDGE -> driver = new EdgeDriver();
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static void addHeadlessArguments(ChromeOptions options) {
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    }

    private static void addHeadlessArguments(FirefoxOptions options) {
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    }

    private static void addHeadlessArguments(EdgeOptions options) {
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    }

}
