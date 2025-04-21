package tests.components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class FooterTests extends BaseTest {
    private static final String FOOTER_URL = "https://bonigarcia.dev/";
    private static final String FOOTER_TEXT = "Copyright \u00A9 2021-2025 Boni Garc\u00EDa";

    @Test
    void homePageFooterViewTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(homePage.isFooterDisplayed(), "Footer is missing"),
                () -> Assertions.assertEquals(FOOTER_TEXT, homePage.getFooterText(), "Footer text is wrong")
        );
    }

    @Test
    void webFormHeaderLogoTest() {
        homePage.clickWebFormLink().clickFooterLink();

        Assertions.assertEquals(FOOTER_URL, driver.getCurrentUrl(), "Footer link is wrong");
    }

    @Test
    void dialogBoxesHeaderDisplayTest() {
        Assertions.assertTrue(homePage.clickDialogBoxesLink().isFooterDisplayed(), "Footer is missing");
    }
}
