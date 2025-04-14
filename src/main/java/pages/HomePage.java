package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static constants.LinksConstants.DIALOG_BOXES;
import static constants.LinksConstants.WEB_FORM;

public class HomePage extends BasePage {
    private static final String CHAPTER_LINKS_XPATH = "//h5[text()='%s']/following-sibling::a";
    private static final String LINK_XPATH = "//a[text()='%s']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click specific page link")
    public void clickSpecificLink(String linkName) {
        driver.findElement(By.xpath(String.format(LINK_XPATH, linkName))).click();
    }

    @Step("Click Web form link")
    public WebFormPage clickWebFormLink() {
        driver.findElement(By.xpath(String.format(LINK_XPATH, WEB_FORM))).click();
        return new WebFormPage(driver);
    }

    @Step("Click Dialog boxes link")
    public DialogBoxesPage clickDialogBoxesLink() {
        driver.findElement(By.xpath(String.format(LINK_XPATH, DIALOG_BOXES))).click();
        return new DialogBoxesPage(driver);
    }

    @Step("Get Homepage chapters links")
    public List<String> getChapterLinks(String chapterName) {
        return driver.findElements(By.xpath(String.format(CHAPTER_LINKS_XPATH, chapterName)))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
