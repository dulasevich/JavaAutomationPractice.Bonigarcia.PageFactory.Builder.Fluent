package allure;

import browser.BrowserFactory;
import driver.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AllureUtil {

    public void captureScreenshotOnTestFailure() {
        Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
