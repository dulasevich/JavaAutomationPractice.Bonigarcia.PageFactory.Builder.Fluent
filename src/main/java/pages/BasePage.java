package pages;

import components.Footer;
import components.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected Header header;
    protected Footer footer;

    @FindBy(xpath = "//h1[contains(@class, 'display-6')]")
    private WebElement title;

    @FindBy(xpath = "//frame[@name='frame-header']")
    private WebElement frame;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        header = new Header(driver);
        footer = new Footer(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Get page title")
    public String getTitle() {
        try {
            return title.getText();
        } catch (NoSuchElementException e) {
            driver.switchTo().frame(frame);
            return title.getText();
        }
    }

    @Step("Get page URL")
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Switch to Alert")
    public Alert getAlert() {
        return driver.switchTo().alert();
    }

    @Step("Click header logo")
    public void clickHeaderLogo() {
        header.clickLogo();
    }

    @Step("Get header title")
    public String getHeaderTitle() {
        return header.getTitle();
    }

    @Step("Get header sub title")
    public String getHeaderSubtitle() {
        return header.getSubTitle();
    }

    @Step("Verify if header section displays")
    public Boolean isHeaderDisplayed() {
        return header.isHeaderDisplayed();
    }

    @Step("Get footer copy right notice")
    public String getFooterText() {
        return footer.getFooterText();
    }

    @Step("Click footer link")
    public void clickFooterLink() {
        footer.clickBoniGarciaLink(actions);
    }

    @Step("Verify if footer section displays")
    public Boolean isFooterDisplayed() {
        return footer.isFooterDisplayed();
    }
}
