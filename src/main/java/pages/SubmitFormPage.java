package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubmitFormPage extends BasePage {
    private static final By SUCCESS_SUBMIT_MESSAGE = By.xpath("//p");

    public SubmitFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get submit confirmation message")
    public String getConfirmationMessage() {
        return driver.findElement(SUCCESS_SUBMIT_MESSAGE).getText();
    }
}
