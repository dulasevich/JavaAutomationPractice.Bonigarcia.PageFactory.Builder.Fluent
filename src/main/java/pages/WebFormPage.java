package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WebFormPage extends BasePage {
    private static final By TEXT_INPUT_FIELD = By.xpath("//input[@id='my-text-id']");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@name='my-password']");
    private static final By TEXT_AREA_INPUT_FIELD = By.xpath("//textarea");
    private static final By DISABLED_INPUT_FIELD = By.xpath("//input[@name='my-disabled']");
    private static final By READ_ONLY_FIELD = By.xpath("//input[@name='my-readonly']");
    private static final By DROPDOWN_SELECT = By.xpath("//select[@name='my-select']");
    private static final By DATA_LIST_INPUT = By.xpath("//input[@name='my-datalist']");
    private static final By DATA_LIST_OPTIONS = By.xpath("//datalist/option");
    private static final By FILE_INPUT= By.xpath("//input[@name='my-file']");
    private static final By COLOR_PICKER = By.xpath("//input[@name='my-colors']");
    private static final By SUBMIT_BUTTON = By.xpath("//button");
    private static final By RANGE_PICKER = By.xpath("//input[@name='my-range']");
    private static final By DATE_PICKER = By.xpath("//input[@name='my-date']");
    private static final String CHECKBOX_XPATH = "//input[@id='my-check-%d']";
    private static final String RADIOBUTTON_XPATH = "//input[@id='my-radio-%d']";
    private static final String DATE_TO_SELECT_XPATH= "//td[@class='%s' and text()=%d]";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String PLACEHOLDER_ATTRIBUTE = "placeholder";
    private static final String CHECKED_NAME = "Checked";
    private final Select dropdownSelect;

    public WebFormPage(WebDriver driver) {
        super(driver);
        dropdownSelect = new Select(driver.findElement(DROPDOWN_SELECT));
    }

    @Step("Enter text input")
    public void enterTextInputField(String textInput) {
        driver.findElement(TEXT_INPUT_FIELD).sendKeys(textInput);
    }

    @Step("Enter password input")
    public void enterPasswordInputField(String passwordInput) {
        driver.findElement(PASSWORD_INPUT_FIELD).sendKeys(passwordInput);
    }

    @Step("Enter text area")
    public void enterTextAreaInputField(String textAreaInput) {
        driver.findElement(TEXT_AREA_INPUT_FIELD).sendKeys(textAreaInput);
    }

    @Step("Get text entered")
    public String getTextEntered() {
        return driver.findElement(TEXT_INPUT_FIELD).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get password entered")
    public String getPasswordEntered() {
        return driver.findElement(PASSWORD_INPUT_FIELD).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get text area entered")
    public String getTextAreaEntered() {
        return driver.findElement(TEXT_AREA_INPUT_FIELD).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Delete text from input")
    public void clearTextInputField() {
        driver.findElement(TEXT_INPUT_FIELD).clear();
    }

    @Step("Delete text from area")
    public void clearTextAreaField() {
        driver.findElement(TEXT_AREA_INPUT_FIELD).clear();
    }

    @Step("Delete text from password")
    public void clearPasswordInputField() {
        driver.findElement(PASSWORD_INPUT_FIELD).clear();
    }

    @Step("Check if disabled input is enabled")
    public boolean isDisabledInputEnabled() {
        return driver.findElement(DISABLED_INPUT_FIELD).isEnabled();
    }

    @Step("Get disabled input placeholder text")
    public String getDisabledInputPlaceholder() {
        return driver.findElement(DISABLED_INPUT_FIELD).getDomProperty(PLACEHOLDER_ATTRIBUTE);
    }

    @Step("Get readonly input placeholder text")
    public String getReadOnlyInputPlaceholder() {
        return driver.findElement(READ_ONLY_FIELD).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Enter disabled field with text")
    public void enterDisabledInputFiled(String disabledInput) {
        driver.findElement(DISABLED_INPUT_FIELD).sendKeys(disabledInput);
    }

    @Step("Enter readonly field with text")
    public void enterReadOnlyInputFiled(String readOnlyInput) {
        driver.findElement(READ_ONLY_FIELD).sendKeys(readOnlyInput);
    }

    @Step("Get readonly input text")
    public String getReadOnlyValueEntered() {
        return driver.findElement(READ_ONLY_FIELD).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Get dropdown select default option")
    public String getDefaultDropdownSelectOption() {
        return dropdownSelect.getFirstSelectedOption().getText();
    }

    @Step("Get dropdown select options")
    public List<String> getAllDropdownSelectOptions() {
        return dropdownSelect.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Select dropdown option by index")
    public void selectDropdownOption(int index) {
        dropdownSelect.selectByIndex(index);
    }

    @Step("Select dropdown option by text")
    public void selectDropdownOption(String value) {
        dropdownSelect.selectByVisibleText(value);
    }

    @Step("Get dropdown selected option")
    public String getDropdownSelectedOption() {
        return dropdownSelect.getFirstSelectedOption().getText();
    }

    @Step("Get data list placeholder text")
    public String getDataListPlaceholder() {
        return driver.findElement(DATA_LIST_INPUT).getDomAttribute(PLACEHOLDER_ATTRIBUTE);
    }

    @Step("Get data list dropdown options")
    public List<String> getDataListOptions() {
        return driver.findElements(DATA_LIST_OPTIONS).stream().map(element -> element.getDomAttribute(VALUE_ATTRIBUTE))
                .collect(Collectors.toList());
    }

    @Step("Select data list option")
    public void enterDataList(String dataListInput) {
        driver.findElement(DATA_LIST_INPUT).sendKeys(dataListInput);
    }

    @Step("Get data list selected option")
    public String getDataListOptionSelected() {
        return driver.findElement(DATA_LIST_INPUT).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Attach file to the form")
    public void attachFile(String absolutePath) {
        driver.findElement(FILE_INPUT).sendKeys(absolutePath);
    }

    @Step("Get filed attached name")
    public String getFileAttachedName() {
        return driver.findElement(FILE_INPUT).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Select checkBox")
    public void selectCheckBox(String checkBox) {
        int checkBoxToSelect = checkBox.contains(CHECKED_NAME) ? 1 : 2;
        driver.findElement(By.xpath(String.format(CHECKBOX_XPATH, checkBoxToSelect))).click();
    }

    @Step("Check if checkbox is selected")
    public Boolean isCheckBoxSelected(String checkBox) {
        int checkBoxToSelect = checkBox.contains(CHECKED_NAME) ? 1 : 2;
        return driver.findElement(By.xpath(String.format(CHECKBOX_XPATH, checkBoxToSelect))).isSelected();
    }

    @Step("Select radio button")
    public void selectRadioButton(String radioButton) {
        int radioButtonToSelect = radioButton.contains(CHECKED_NAME) ? 1 : 2;
        driver.findElement(By.xpath(String.format(RADIOBUTTON_XPATH, radioButtonToSelect))).click();
    }

    @Step("Check if radio button is selected")
    public Boolean isRadioButtonSelected(String radioButton) {
        int radioButtonToSelect = radioButton.contains(CHECKED_NAME) ? 1 : 2;
        return driver.findElement(By.xpath(String.format(RADIOBUTTON_XPATH, radioButtonToSelect))).isSelected();
    }

    @Step("Get selected color from the picker")
    public String getColorFromPicker() {
        return driver.findElement(COLOR_PICKER).getDomAttribute(VALUE_ATTRIBUTE);
    }

    @Step("Select color from the picker")
    public void changeColorTo(Color color) {
        String script = String.format("arguments[0].setAttribute('value', '%s');", color.asHex());
        pageUtil.executeScript(script, driver.findElement(COLOR_PICKER));
    }

    @Step("Get selected date from the picker")
    public String getDateSelected() {
        return driver.findElement(DATE_PICKER).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Click date picker")
    public void clickDateSelection() {
        driver.findElement(DATE_PICKER).click();
    }

    @Step("Select date from the picker")
    public void clickSpecificDate(String xpathClassName, LocalDate day) {
        driver.findElement(By.xpath(String.format(DATE_TO_SELECT_XPATH, xpathClassName, day.getDayOfMonth()))).click();
    }

    @Step("Get range picker location")
    public String getRangePickerLocation() {
        return driver.findElement(RANGE_PICKER).getDomProperty(VALUE_ATTRIBUTE);
    }

    @Step("Move range picker by some pixels")
    public void moveRangePickerBy(int pixels) {
        pageUtil.moveElementByOffset(driver.findElement(RANGE_PICKER), pixels, 0);
    }

    @Step("Submit form")
    public SubmitFormPage submitForm() {
        driver.findElement(SUBMIT_BUTTON).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(getPageUrl())));
        return new SubmitFormPage(driver);
    }
}
