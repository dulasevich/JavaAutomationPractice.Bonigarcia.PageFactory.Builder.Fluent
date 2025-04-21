package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[text()='Web form']")
    private WebElement webFormButton;

    @FindBy(xpath = "//a[text()='Dialog boxes']")
    private WebElement dialogBoxesButton;

    @FindBy(xpath = "//p")
    private WebElement description;

    private static final String CHAPTER_XPATH = "//div[@class='card-body']//h5[text()='%s']";
    private static final String CHAPTER_LINKS_XPATH = "//h5[text()='%s']/following-sibling::a";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open home page")
    public void open(String Url) {
        driver.get(Url);
    }

    @Step("Click Web form link")
    public WebFormPage clickWebFormLink() {
        webFormButton.click();
        return new WebFormPage(driver);
    }

    @Step("Click Dialog boxes link")
    public DialogBoxesPage clickDialogBoxesLink() {
        dialogBoxesButton.click();
        return new DialogBoxesPage(driver);
    }

    @Step("Get home page description text")
    public String getDescription() {
        return description.getText();
    }

    @Step("Check if specific chapter displays")
    public Boolean isChapterDisplayed(String chapterName) {
        return driver.findElement(By.xpath(String.format(CHAPTER_XPATH, chapterName))).isDisplayed();
    }

    @Step("Get Homepage links by chapter")
    public List<String> getChapterLinks(String chapterName) {
        return driver.findElements(By.xpath(String.format(CHAPTER_LINKS_XPATH, chapterName)))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
