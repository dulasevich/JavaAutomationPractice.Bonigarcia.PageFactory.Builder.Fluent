package tests.components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class HeaderTests extends BaseTest {
    private static final String HEADER_LOGO_URL = "https://github.com/bonigarcia/selenium-webdriver-java";
    private static final String HEADER_TITLE = "Hands-On Selenium WebDriver with Java";
    private static final String HEADER_SUB_TITLE = "Practice site";

    @Test
    void homePageHeaderViewTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(homePage.isHeaderDisplayed(), "Header is missing"),
                () -> Assertions.assertEquals(HEADER_TITLE, homePage.getHeaderTitle(), "Header title is wrong"),
                () -> Assertions.assertEquals(HEADER_SUB_TITLE, homePage.getHeaderSubtitle(), "Header sub title is wrong")
        );
    }

    @Test
    void webFormHeaderLogoTest() {
        homePage.clickWebFormLink().clickHeaderLogo();

        Assertions.assertEquals(HEADER_LOGO_URL, driver.getCurrentUrl(), "Header logo link is wrong");
    }

    @Test
    void dialogBoxesHeaderDisplayTest() {
        Assertions.assertTrue(homePage.clickDialogBoxesLink().isHeaderDisplayed(), "Header is missing");
    }
}
