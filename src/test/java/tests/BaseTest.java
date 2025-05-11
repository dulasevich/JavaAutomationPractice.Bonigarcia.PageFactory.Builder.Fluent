package tests;

import allure.AllureExtension;
import driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

@ExtendWith(AllureExtension.class)
public class BaseTest {
    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = Driver.getDriver();
        homePage = new HomePage(driver);
        homePage.open(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        Driver.turnDriverDown();
    }
}
