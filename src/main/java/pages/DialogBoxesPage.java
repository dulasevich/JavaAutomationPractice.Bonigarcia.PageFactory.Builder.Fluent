package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class DialogBoxesPage extends BasePage {
    @FindBy(xpath = "//div[@class='col-sm-2 py-2']/button")
    private List<WebElement> dialogBoxesButtons;

    @FindBy(xpath = "//h5[@class='modal-title']")
    private WebElement modalTitle;

    @FindBy(xpath = "//div[@class='modal-body']")
    private WebElement modalBody;

    @FindBy(xpath = "//button[contains(@class, 'secondary')]")
    private WebElement modalCloseButton;

    @FindBy(xpath = "//button[contains(@class, 'btn-primary')]")
    private WebElement modalSaveButton;

    @FindBy(xpath = "//button[@id='my-alert']")
    private WebElement launchAlertButton;

    @FindBy(xpath = "//button[@id='my-confirm']")
    private WebElement launchConfirmButton;

    @FindBy(xpath = "//button[@id='my-prompt']")
    private WebElement launchPromptButton;

    @FindBy(xpath = "//button[@id='my-modal']")
    private WebElement launchModalButton;

    private static final String ALERT_RESULTS_XPATH = "./following-sibling::p";

    public DialogBoxesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get all alerts buttons")
    public List<String> getAllDialogBoxesButton() {
        return dialogBoxesButtons.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Launch alert")
    public DialogBoxesPage clickLaunchAlert() {
        launchAlertButton.click();
        return this;
    }

    @Step("Get alert text")
    public DialogBoxesPage checkAlertText(String text) {
        Assertions.assertEquals(text, getAlert().getText(), "Incorrect alert text");
        return this;
    }

    @Step("Launch confirm alert")
    public DialogBoxesPage clickLaunchConfirmAlert() {
        launchConfirmButton.click();
        return this;
    }

    @Step("Launch prompt alert")
    public DialogBoxesPage clickLaunchPromptAlert() {
        launchPromptButton.click();
        return this;
    }

    @Step("Accept alert")
    public DialogBoxesPage acceptAlert() {
        getAlert().accept();
        return this;
    }

    @Step("Enter text to alert")
    public DialogBoxesPage sendKeysToAlert(String text) {
        getAlert().sendKeys(text);
        return this;
    }

    @Step("Skip alert")
    public DialogBoxesPage dismissAlert() {
        getAlert().dismiss();
        return this;
    }

    @Step("Verify confirm alert message")
    public DialogBoxesPage checkAlertConfirmMessage(String message) {
        Assertions.assertEquals(message, launchConfirmButton.findElement(By.xpath(ALERT_RESULTS_XPATH)).getText(),
                "Alert confirm message is wrong");
        return this;
    }

    @Step("Verify prompt alert message")
    public DialogBoxesPage checkAlertPromptMessage(String message) {
        Assertions.assertEquals(message, launchPromptButton.findElement(By.xpath(ALERT_RESULTS_XPATH)).getText(),
                "Alert prompt message is wrong");
        return this;
    }

    @Step("Launch modal")
    public DialogBoxesPage clickModalAlert() {
        launchModalButton.click();
        return this;
    }

    @Step("Verify modal message")
    public DialogBoxesPage checkModalMessage(String message) {
        Assertions.assertEquals(message, launchModalButton.findElement(By.xpath(ALERT_RESULTS_XPATH)).getText(),
                "Modal selection was wrong");
        return this;
    }

    @Step("Verify modal title")
    public DialogBoxesPage checkModalTitle(String modalTitleText) {
        Assertions.assertEquals(modalTitleText, wait.until(ExpectedConditions.visibilityOf(modalTitle)).getText(),
                "Incorrect modal title");
        return this;
    }

    @Step("Verify modal body")
    public DialogBoxesPage checkModalBody(String modalBodyText) {
        Assertions.assertEquals(modalBodyText, wait.until(ExpectedConditions.visibilityOf(modalBody)).getText(),
                "Incorrect modal body");
        return this;
    }

    @Step("Close modal")
    public DialogBoxesPage closeModal() {
        wait.until(ExpectedConditions.visibilityOf(modalCloseButton)).click();
        return this;
    }

    @Step("Save modal")
    public DialogBoxesPage saveModal() {
        wait.until(ExpectedConditions.visibilityOf(modalSaveButton)).click();
        return this;
    }
}
