import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import pages.SubmitFormPage;
import pages.WebFormPage;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WebFormTests extends BaseTest{
    private static final String TEXT_TO_ENTER = "text";
    private static final String TEXT_AREA_TO_ENTER = "This is line one.\nThis is line two.\nAnd this is line three.";
    private WebFormPage webFormPage;

    @BeforeEach
    void navigateToWebFormPage() {
        webFormPage = homePage.clickWebFormLink();
    }

    @Test
    void inputFieldsTest() {
        webFormPage.enterTextInputField(TEXT_TO_ENTER);
        webFormPage.enterPasswordInputField(TEXT_TO_ENTER);
        Assertions.assertEquals(TEXT_TO_ENTER, webFormPage.getTextEntered(), "Text entered is wrong");
        Assertions.assertEquals(TEXT_TO_ENTER, webFormPage.getPasswordEntered(), "Text entered is wrong");

        webFormPage.clearTextInputField();
        webFormPage.clearPasswordInputField();
        Assertions.assertTrue(webFormPage.getTextEntered().isEmpty(), "Field is NOT blank");
        Assertions.assertTrue(webFormPage.getPasswordEntered().isEmpty(), "Field is NOT blank");
    }

    @Test
    void textAreaTest() {
        webFormPage.enterTextAreaInputField(TEXT_AREA_TO_ENTER);
        Assertions.assertEquals(TEXT_AREA_TO_ENTER, webFormPage.getTextAreaEntered(), "Text entered is wrong");

        webFormPage.clearTextAreaField();
        Assertions.assertTrue(webFormPage.getTextAreaEntered().isEmpty(), "Field is NOT blank");
    }

    @Test
    void disabledInputTest() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(webFormPage.isDisabledInputEnabled(), "Disabled input is enabled"),
                () -> Assertions.assertEquals("Disabled input", webFormPage.getDisabledInputPlaceholder(),
                        "Disabled input text is wrong"),
                () -> Assertions.assertThrows(ElementNotInteractableException.class, () -> webFormPage.enterDisabledInputFiled(TEXT_TO_ENTER),
                        "It's possible to enter text to the disabled input")
        );
    }

    @Test
    void readOnlyInputTest() {
        String readOnlyInputText = "Readonly input";

        Assertions.assertEquals(readOnlyInputText, webFormPage.getReadOnlyInputPlaceholder(),
                "Read only text is wrong");

        webFormPage.enterReadOnlyInputFiled(TEXT_TO_ENTER);
        Assertions.assertEquals(readOnlyInputText, webFormPage.getReadOnlyValueEntered());
    }

    @Test
    void dropDownSelectOptionsTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Open this select menu", webFormPage.getDefaultDropdownSelectOption(),
                        "Incorrect default option selected"),
                () -> Assertions.assertEquals(List.of("Open this select menu", "One", "Two", "Three"),
                        webFormPage.getAllDropdownSelectOptions(), "List of options is incorrect")
        );
    }

    @Test
    void dropDownSelectTest() {
        webFormPage.selectDropdownOption(1);
        Assertions.assertEquals("One", webFormPage.getDropdownSelectedOption(), "Incorrect option selected");

        webFormPage.selectDropdownOption("Two");
        Assertions.assertEquals("Two", webFormPage.getDropdownSelectedOption(), "Incorrect option selected");
    }

    @Test
    void dropDownDataListOptionsTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Type to search...", webFormPage.getDataListPlaceholder(),
                        "Incorrect background text added"),
                () -> Assertions.assertEquals(List.of("San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"),
                        webFormPage.getDataListOptions(), "List of options is incorrect")
        );

        String dataListOption = "Seattle";
        webFormPage.enterDataList(dataListOption);
        Assertions.assertEquals(dataListOption, webFormPage.getDataListOptionSelected());
    }

    @Test
    void fileInputTest() {
        String fileName = "test.txt";
        URL fileUrl = WebFormTests.class.getClassLoader().getResource(fileName);
        Assertions.assertNotNull(fileUrl, "File not found in the resources");

        String absolutePath = new File(URLDecoder.decode(fileUrl.getPath(), StandardCharsets.UTF_8)).getAbsolutePath();
        webFormPage.attachFile(absolutePath);
        Assertions.assertTrue(webFormPage.getFileAttachedName().contains(fileName), "File is NOT attached ot incorrect added");
    }

    @Test
    void checkBoxTest() {
        String checkedCheckBox = "Checked checkbox";
        String defaultCheckBox = "Default checkbox";

        Assertions.assertAll(
                () -> Assertions.assertTrue(webFormPage.isCheckBoxSelected(checkedCheckBox), "Checked option is not selected"),
                () -> Assertions.assertFalse(webFormPage.isCheckBoxSelected(defaultCheckBox), "Default option is selected")
        );

        webFormPage.selectCheckBox(checkedCheckBox);
        webFormPage.selectCheckBox(defaultCheckBox);
        Assertions.assertAll(
                () -> Assertions.assertFalse(webFormPage.isCheckBoxSelected(checkedCheckBox), "Checked option is still selected"),
                () -> Assertions.assertTrue(webFormPage.isCheckBoxSelected(defaultCheckBox), "Default option is NOT selected")
        );
    }

    @Test
    void radioButtonTest() {
        String checkedRadioButton = "Checked radio";
        String defaultRadioButton = "Default radio";

        Assertions.assertAll(
                () -> Assertions.assertTrue(webFormPage.isRadioButtonSelected(checkedRadioButton), "Checked button is not selected"),
                () -> Assertions.assertFalse(webFormPage.isRadioButtonSelected(defaultRadioButton), "Default button is not selected")
        );

        webFormPage.selectRadioButton(defaultRadioButton);
        Assertions.assertAll(
                () -> Assertions.assertFalse(webFormPage.isRadioButtonSelected(checkedRadioButton), "Checked option is still selected"),
                () -> Assertions.assertTrue(webFormPage.isRadioButtonSelected(defaultRadioButton), "Default option is NOT selected")
        );
    }

    @Test
    void colorPickerTest() {
        String redColorHex = "#563d7c";
        String greenColorHex = "#3d7b52";
        Assertions.assertEquals(redColorHex, webFormPage.getColorFromPicker(), "Default color is wrong");

        Color green = new Color(61, 123, 82, 1);
        webFormPage.changeColorTo(green);

        Assertions.assertEquals(greenColorHex, webFormPage.getColorFromPicker(), "Incorrect color selected");
    }

    @Test
    void datePickerTest() {
        String dateFormat = "MM/dd/yyyy";
        Assertions.assertTrue(webFormPage.getDateSelected().isEmpty(), "Some date is selected");

        webFormPage.clickDateSelection();
        LocalDate date = LocalDate.now().plusDays(1);
        //handling the case when next day is in the next month, locator is different
        String xpathClassName = LocalDate.now().getMonth().maxLength() == LocalDate.now().getDayOfMonth() ? "new day" : "day";
        webFormPage.clickSpecificDate(xpathClassName, date);

        Assertions.assertEquals(date.format(DateTimeFormatter.ofPattern(dateFormat)), webFormPage.getDateSelected(),
                "Incorrect date selected");
    }

    @Test
    void rangeTest() {
        String defaultPickerPosition = "5";
        String expectedPickerPosition = "6";
        Assertions.assertEquals(defaultPickerPosition, webFormPage.getRangePickerLocation(),
                "Default position of picker is wrong");

        int pixelsToMove = 60;
        webFormPage.moveRangePickerBy(pixelsToMove);
        Assertions.assertEquals(expectedPickerPosition, webFormPage.getRangePickerLocation(), "Picker moved to incorrect position");
    }

    @Test
    void submitButtonTest() {
        SubmitFormPage submitFormPage = webFormPage.submitForm();

        Assertions.assertAll(
                () -> Assertions.assertEquals("Form submitted", submitFormPage.getTitle(), "Title is wrong"),
                () -> Assertions.assertEquals("Received!", submitFormPage.getConfirmationMessage(),
                        "Submission confirmation is either wrong or not displayed")
        );
    }
}
