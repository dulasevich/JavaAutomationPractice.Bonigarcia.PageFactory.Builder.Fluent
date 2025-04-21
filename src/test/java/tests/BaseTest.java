package tests;

import browser.BrowserFactory;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class BaseTest {
    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    protected WebDriver driver;
    protected HomePage homePage;
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    public void setUp() {
        driver = BrowserFactory.getDriver(configProperties.getBrowser());
        homePage = new HomePage(driver);
        homePage.open(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
