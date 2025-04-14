package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class DialogBoxesPage extends BasePage{
    private static final By DIALOG_BOXES_BUTTONS = By.xpath("//div[@class='col-sm-2 py-2']/button");
    private static final By MODAL_TITLE = By.xpath("//h5[@class='modal-title']");
    private static final By MODAL_BODY = By.xpath("//div[@class='modal-body']");
    private static final By MODAL_CLOSE_BUTTON =By.xpath("//button[contains(@class, 'secondary')]");
    private static final By MODAL_SAVE_BUTTON = By.xpath("//button[contains(@class, 'btn-primary')]");
    private static final String DIALOG_BOX_XPATH = "//button[@id='my-%s']";
    private static final String ALERT_RESULTS_XPATH = DIALOG_BOX_XPATH + "/following-sibling::p";
    private static final String ALERT_TYPE_CONFIRM= "confirm";
    private static final String ALERT_TYPE_PROMPT= "prompt";
    private static final String MODAL_TYPE_PROMPT= "modal";

    public DialogBoxesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get all alerts buttons")
    public List<String> getAllDialogBoxesButton() {
        return driver.findElements(DIALOG_BOXES_BUTTONS).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Launch alert")
    public void clickLaunchAlert() {
        driver.findElement(By.xpath(String.format(DIALOG_BOX_XPATH, "alert"))).click();
    }

    @Step("Get alert text")
    public String getAlertText() {
        return pageUtil.getAlert().getText();
    }

    @Step("Launch confirm alert")
    public void clickLaunchConfirmAlert() {
        driver.findElement(By.xpath(String.format(DIALOG_BOX_XPATH, ALERT_TYPE_CONFIRM))).click();
    }

    @Step("Launch prompt alert")
    public void clickLaunchPromptAlert() {
        driver.findElement(By.xpath(String.format(DIALOG_BOX_XPATH, ALERT_TYPE_PROMPT))).click();
    }

    @Step("Accept alert")
    public void acceptAlert() {
        pageUtil.getAlert().accept();
    }

    @Step("Enter text to alert")
    public void sendKeysToAlert(String text) {
        pageUtil.getAlert().sendKeys(text);
    }

    @Step("Dismiss alert")
    public void dismissAlert() {
        pageUtil.getAlert().dismiss();
    }

    @Step("Get confirm alert message")
    public String getAlertConfirmMessage() {
        return driver.findElement(By.xpath(String.format(ALERT_RESULTS_XPATH, ALERT_TYPE_CONFIRM))).getText();
    }

    @Step("Get prompt alert message")
    public String getAlertPromptMessage() {
        return driver.findElement(By.xpath(String.format(ALERT_RESULTS_XPATH, ALERT_TYPE_PROMPT))).getText();
    }

    @Step("Launch modal")
    public void clickModalAlert() {
        driver.findElement(By.xpath(String.format(DIALOG_BOX_XPATH, MODAL_TYPE_PROMPT))).click();
    }

    @Step("Get modal message")
    public String getModalMessage() {
        return driver.findElement(By.xpath(String.format(ALERT_RESULTS_XPATH, MODAL_TYPE_PROMPT))).getText();
    }

    @Step("Get modal title")
    public String getModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_TITLE)).getText();
    }

    @Step("Get modal body")
    public String getModalBody() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_BODY)).getText();
    }

    @Step("Close modal")
    public void closeModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_CLOSE_BUTTON)).click();
    }

    @Step("Save modal")
    public void saveModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_SAVE_BUTTON)).click();
    }
}
