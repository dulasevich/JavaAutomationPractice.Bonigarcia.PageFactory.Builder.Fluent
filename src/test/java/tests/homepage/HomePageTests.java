package tests.homepage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.BaseTest;

import java.util.List;

import static constants.LinksConstants.*;

public class HomePageTests extends BaseTest {

    @Test
    void contentTest() {
        List<String> fundamentalsLinks = homePage.getChapterLinks("Chapter 3. WebDriver Fundamentals");
        List<String> browserAgnosticLinks = homePage.getChapterLinks("Chapter 4. Browser-Agnostic Features");
        List<String> browserSpecificLinks = homePage.getChapterLinks("Chapter 5. Browser-Specific Manipulation");
        List<String> pomLinks = homePage.getChapterLinks("Chapter 7. The Page Object Model (POM)");
        List<String> testingFrameworksLinks = homePage.getChapterLinks("Chapter 8. Testing Framework Specifics");
        List<String> thirdPartyLinks = homePage.getChapterLinks("Chapter 9. Third-Party Integrations");

        Assertions.assertAll(
                () -> Assertions.assertEquals(List.of(WEB_FORM, NAVIGATION, DROPDOWN_MENU, MOUSE_OVER, DRAG_DROP, DRAW_IN_CANVAS,
                                LOADING_IMAGES, SLOW_CALCULATOR), fundamentalsLinks,
                        "Chapter 3. WebDriver Fundamentals has incorrect links"),
                () -> Assertions.assertEquals(List.of(LONG_PAGE, INFINITE_SCROLL, SHADOW_DOM, COOKIES, FRAMES, IFRAMES, DIALOG_BOXES,
                                WEB_STORAGE), browserAgnosticLinks,
                        "Chapter 4. Browser-Agnostic Features has incorrect links"),
                () -> Assertions.assertEquals(List.of(GEOLOCATION, NOTIFICATIONS, GET_USER_MEDIA, MULTILANGUAGE, CONSOLE_LOGS),
                        browserSpecificLinks, "Chapter 5. Browser-Specific Manipulation has incorrect links"),
                () -> Assertions.assertEquals(List.of(LOGIN_FORM, SLOW_LOGIN), pomLinks,
                        "Chapter 7. The Page Object Model (POM) has incorrect links"),
                () -> Assertions.assertEquals(List.of(RANDOM_CALCULATOR), testingFrameworksLinks,
                        "Chapter 8. Testing Framework Specifics has incorrect links"),
                () -> Assertions.assertEquals(List.of(DOWNLOAD_FILES, AB_TESTING, DATA_TYPES), thirdPartyLinks,
                        "Chapter 9. Third-Party Integrations has incorrect links")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters.TestData#linksProvider")
    void linksTest(String linkName, String endpoint, String title) {
        homePage.clickSpecificLink(linkName);

        Assertions.assertEquals(BaseTest.BASE_URL + endpoint, homePage.getPageUrl(), "Incorrect page opens");
        Assertions.assertEquals(title, homePage.getTitle(), "Incorrect title");
    }
}

