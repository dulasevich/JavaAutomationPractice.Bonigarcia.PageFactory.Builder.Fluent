package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(xpath = "//div[@class='row py-2']")
    private WebElement headerBlock;

    @FindBy(xpath = "//h1")
    private WebElement headerTitle;

    @FindBy(xpath = "//h5")
    private WebElement headerSubTitle;

    @FindBy(xpath = "//img")
    private WebElement seleniumLogo;

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return headerTitle.getText();
    }

    public String getSubTitle() {
        return headerSubTitle.getText();
    }

    public void clickLogo() {
        seleniumLogo.click();
    }

    public Boolean isHeaderDisplayed() {
        return headerBlock.isDisplayed();
    }
}
