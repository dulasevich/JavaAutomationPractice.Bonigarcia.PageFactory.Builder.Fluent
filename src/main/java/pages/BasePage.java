package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageUtil;

import java.time.Duration;

public class BasePage {
    protected static final By TITLE = By.xpath("//h1[contains(@class, 'display-6')]");
    private static final By FRAME_HEADER_XPATH = By.xpath("//frame[@name='frame-header']");
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PageUtil pageUtil;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        pageUtil = new PageUtil(driver);
    }

    @Step("Get page title")
    public String getTitle() {
        try {
            return driver.findElement(TITLE).getText();
        } catch (NoSuchElementException e) {
            driver.switchTo().frame(driver.findElement(FRAME_HEADER_XPATH));
            return driver.findElement(TITLE).getText();
        }
    }

    @Step("Get page URL")
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
