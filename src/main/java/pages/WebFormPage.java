package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WebFormPage extends BasePage {
    @FindBy(xpath = "//input[@id='my-text-id']")
    private WebElement textInputField;

    @FindBy(xpath = "//input[@name='my-password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//textarea")
    private WebElement textAreaInputField;

    @FindBy(xpath = "//input[@name='my-disabled']")
    private WebElement disabledInputField;

    @FindBy(xpath = "//input[@name='my-readonly']")
    private WebElement readOnlyField;

    @FindBy(xpath = "//select[@name='my-select']")
    private WebElement dropdownSelect;

    @FindBy(xpath = "//input[@name='my-datalist']")
    private WebElement dataListInput;

    @FindBy(xpath = "//datalist/option")
    private List<WebElement> dataListOptions;

    @FindBy(xpath = "//input[@name='my-file']")
    private WebElement fileInput;

    @FindBy(xpath = "//input[@name='my-colors']")
    private WebElement colorPicker;

    @FindBy(xpath = "//button")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name='my-range']")
    private WebElement rangePicker;

    @FindBy(xpath = "//input[@name='my-date']")
    private WebElement datePicker;

    @FindBy(xpath = "//input[@id='my-check-1']")
    private WebElement checkedCheckbox;

    @FindBy(xpath = "//input[@id='my-check-2']")
    private WebElement defaultCheckbox;

    @FindBy(xpath = "//input[@id='my-radio-1']")
    private WebElement checkedRadioButton;

    @FindBy(xpath = "//input[@id='my-radio-2']")
    private WebElement defaultRadioButton;

    private static final String VALUE_ATTRIBUTE = "value";
    private static final String PLACEHOLDER_ATTRIBUTE = "placeholder";
    private final Select select;

    public WebFormPage(WebDriver driver) {
        super(driver);
        select = new Select(dropdownSelect);
    }

    @Step("Enter text input")
    public void enterTextInputField(String textInput) {
        textInputField.sendKeys(textInput);
    }

    @Step("Enter password input")
    public void enterPasswordInputField(String passwordInput) {
        passwordInputField.sendKeys(passwordInput);
    }

    @Step("Enter text area")
    public void enterTextAreaInputField(String textAreaInput) {
        textAreaInputField.sendKeys(textAreaInput);
    }

    @Step("Get text entered")
    public String getTextEntered() {
        return textInputField.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get password entered")
    public String getPasswordEntered() {
        return passwordInputField.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get text area entered")
    public String getTextAreaEntered() {
        return textAreaInputField.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Delete text from input")
    public void clearTextInputField() {
        textInputField.clear();
    }

    @Step("Delete text from area")
    public void clearTextAreaField() {
        textAreaInputField.clear();
    }

    @Step("Delete text from password")
    public void clearPasswordInputField() {
        passwordInputField.clear();
    }

    @Step("Check if disabled input is enabled")
    public boolean isDisabledInputEnabled() {
        return disabledInputField.isEnabled();
    }

    @Step("Get disabled input placeholder text")
    public String getDisabledInputPlaceholder() {
        return disabledInputField.getDomProperty(PLACEHOLDER_ATTRIBUTE);
    }

    @Step("Get readonly input placeholder text")
    public String getReadOnlyInputPlaceholder() {
        return readOnlyField.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Enter disabled field with text")
    public void enterDisabledInputFiled(String disabledInput) {
        disabledInputField.sendKeys(disabledInput);
    }

    @Step("Enter readonly field with text")
    public void enterReadOnlyInputFiled(String readOnlyInput) {
        readOnlyField.sendKeys(readOnlyInput);
    }

    @Step("Get readonly input text")
    public String getReadOnlyValueEntered() {
        return readOnlyField.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get dropdown select default option")
    public String getDefaultDropdownSelectOption() {
        return select.getFirstSelectedOption().getText();
    }

    @Step("Get dropdown select options")
    public List<String> getAllDropdownSelectOptions() {
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Select dropdown option by index")
    public void selectDropdownOption(int index) {
        select.selectByIndex(index);
    }

    @Step("Select dropdown option by text")
    public void selectDropdownOption(String value) {
        select.selectByVisibleText(value);
    }

    @Step("Get dropdown selected option")
    public String getDropdownSelectedOption() {
        return select.getFirstSelectedOption().getText();
    }

    @Step("Get data list placeholder text")
    public String getDataListPlaceholder() {
        return dataListInput.getDomAttribute(PLACEHOLDER_ATTRIBUTE);
    }

    @Step("Get data list dropdown options")
    public List<String> getDataListOptions() {
        return dataListOptions.stream().map(element -> element.getDomAttribute(VALUE_ATTRIBUTE))
                .collect(Collectors.toList());
    }

    @Step("Select data list option")
    public void enterDataList(String dataListText) {
        dataListInput.sendKeys(dataListText);
    }

    @Step("Get data list selected option")
    public String getDataListOptionSelected() {
        return dataListInput.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Attach file to the form")
    public void attachFile(String absolutePath) {
        if (driver instanceof RemoteWebDriver) {
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        }
        fileInput.sendKeys(absolutePath);
    }

    @Step("Get filed attached name")
    public String getFileAttachedName() {
        return fileInput.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Select checked checkBox")
    public void selectCheckedCheckBox() {
        checkedCheckbox.click();
    }

    @Step("Select default checkBox")
    public void selectDefaultCheckBox() {
        defaultCheckbox.click();
    }

    @Step("Check if checkbox is selected")
    public Boolean isCheckedCheckBoxSelected() {
        return checkedCheckbox.isSelected();
    }

    @Step("Check if checkbox is selected")
    public Boolean isDefaultCheckBoxSelected() {
        return defaultCheckbox.isSelected();
    }

    @Step("Select checked radio button")
    public void selectCheckedRadioButton() {
        checkedRadioButton.click();
    }

    @Step("Select default radio button")
    public void selectDefaultRadioButton() {
        defaultRadioButton.click();
    }

    @Step("Verify if checked radio button is selected")
    public Boolean isCheckedRadioButtonSelected() {
        return checkedRadioButton.isSelected();
    }

    @Step("Verify if default radio button is selected")
    public Boolean isDefaultRadioButtonSelected() {
        return defaultRadioButton.isSelected();
    }

    @Step("Get selected color from the picker")
    public String getColorFromPicker() {
        return colorPicker.getDomAttribute(VALUE_ATTRIBUTE);
    }

    @Step("Select color from the picker")
    public void changeColorTo(Color color) {
        String script = String.format("arguments[0].setAttribute('value', '%s');", color.asHex());
        js.executeScript(script, colorPicker);
    }

    @Step("Get selected date from the picker")
    public String getDateSelected() {
        return datePicker.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Click date picker")
    public void clickDateSelection() {
        datePicker.click();
    }

    @Step("Select date from the picker")
    public void clickSpecificDate(String xpathClassName, LocalDate day) {
        String dateToSelectXpath= "//td[@class='%s' and text()=%d]";
        driver.findElement(By.xpath(String.format(dateToSelectXpath, xpathClassName, day.getDayOfMonth()))).click();
    }

    @Step("Get range picker location")
    public String getRangePickerLocation() {
        return rangePicker.getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Move range picker by some pixels")
    public void moveRangePickerBy(int pixels) {
        actions.clickAndHold(rangePicker).moveByOffset(pixels, 0).perform();
    }

    @Step("Submit form")
    public SubmitFormPage submitForm() {
        String webFormUrl = getPageUrl();
        submitButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(webFormUrl)));
        return new SubmitFormPage(driver);
    }
}
