package browser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver = switch (Browser.fromString(browser)) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
        };
        driver.manage().window().maximize();
        return driver;
    }
}
