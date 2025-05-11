package browser;

import configs.TestPropertiesConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserFactory {
    private static final TestPropertiesConfig CONFIG_PROPERTIES = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    public static WebDriver initDriver() {
        WebDriver driver;
        String remoteURL = System.getenv("SELENIUM_REMOTE_URL");
        if (remoteURL != null && !remoteURL.isEmpty()) {
            driver = setRemoteChromeDriver(remoteURL);
        } else {
            driver = setLocalDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver setRemoteChromeDriver(String remoteURL) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        try {
            return new RemoteWebDriver(new URL(remoteURL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static WebDriver setLocalDriver() {
        return switch (Browser.fromString(CONFIG_PROPERTIES.getBrowser())) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
        };
    }
}
