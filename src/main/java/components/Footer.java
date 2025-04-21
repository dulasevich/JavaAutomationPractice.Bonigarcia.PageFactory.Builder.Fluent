package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Footer {
    @FindBy(xpath = "//footer")
    private WebElement footerSection;

    @FindBy(xpath = "//footer//span")
    private WebElement footerText;

    @FindBy(xpath = "//footer//a")
    private WebElement boniGarciaLink;

    public Footer(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getFooterText() {
        return footerText.getText();
    }

    public void clickBoniGarciaLink(Actions actions) {
        actions.click(boniGarciaLink).perform();
    }

    public Boolean isFooterDisplayed() {
        return footerSection.isDisplayed();
    }
}
