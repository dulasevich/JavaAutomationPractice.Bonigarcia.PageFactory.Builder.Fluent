package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageUtil {
    private final WebDriver driver;
    private final Actions actions;
    private final JavascriptExecutor js;

    public PageUtil(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public Alert getAlert() {
        return driver.switchTo().alert();
    }

    public void executeScript(String script, WebElement element) {
        js.executeScript(script, element);
    }

    public void moveElementByOffset(WebElement element, int xOffset, int yOffset) {
        actions.clickAndHold(element).moveByOffset(xOffset, yOffset).perform();
    }
}
