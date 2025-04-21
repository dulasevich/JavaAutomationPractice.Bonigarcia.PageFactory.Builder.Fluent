package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubmitFormPage extends BasePage {
    @FindBy(xpath = "//p")
    private WebElement successSubmitMessage;

    public SubmitFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get submit confirmation message")
    public String getConfirmationMessage() {
        return successSubmitMessage.getText();
    }
}
