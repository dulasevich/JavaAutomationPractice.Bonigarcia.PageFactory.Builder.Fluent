package parameters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static constants.LinksConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestData {
    public static Stream<Arguments> linksProvider() {
        return Stream.of(
                Arguments.of(WEB_FORM, "web-form.html", WEB_FORM),
                Arguments.of( NAVIGATION, "navigation1.html", NAVIGATION + " example"),
                Arguments.of(DROPDOWN_MENU, "dropdown-menu.html", DROPDOWN_MENU),
                Arguments.of(MOUSE_OVER, "mouse-over.html", MOUSE_OVER),
                Arguments.of(DRAG_DROP, "drag-and-drop.html", DRAG_DROP),
                Arguments.of(DRAW_IN_CANVAS, "draw-in-canvas.html", "Drawing in canvas"),
                Arguments.of(LOADING_IMAGES, "loading-images.html", LOADING_IMAGES),
                Arguments.of(SLOW_CALCULATOR, "slow-calculator.html", SLOW_CALCULATOR),
                Arguments.of(LONG_PAGE, "long-page.html", "This is a long page"),
                Arguments.of(INFINITE_SCROLL, "infinite-scroll.html", INFINITE_SCROLL),
                Arguments.of(SHADOW_DOM, "shadow-dom.html", SHADOW_DOM),
                Arguments.of(COOKIES, "cookies.html", COOKIES),
                Arguments.of(FRAMES, "frames.html", FRAMES),
                Arguments.of(IFRAMES, "iframes.html", "IFrame"),
                Arguments.of(DIALOG_BOXES, "dialog-boxes.html", DIALOG_BOXES),
                Arguments.of(WEB_STORAGE, "web-storage.html", WEB_STORAGE),
                Arguments.of(GEOLOCATION, "geolocation.html", GEOLOCATION),
                Arguments.of(NOTIFICATIONS, "notifications.html", NOTIFICATIONS),
                Arguments.of(GET_USER_MEDIA, "get-user-media.html", GET_USER_MEDIA),
                Arguments.of(MULTILANGUAGE, "multilanguage.html", MULTILANGUAGE + " page"),
                Arguments.of(CONSOLE_LOGS, "console-logs.html", CONSOLE_LOGS),
                Arguments.of(LOGIN_FORM, "login-form.html", LOGIN_FORM),
                Arguments.of(SLOW_LOGIN, "login-slow.html", SLOW_LOGIN + " form"),
                Arguments.of(RANDOM_CALCULATOR, "random-calculator.html", RANDOM_CALCULATOR),
                Arguments.of(DOWNLOAD_FILES, "download.html", DOWNLOAD_FILES),
                Arguments.of(AB_TESTING, "ab-testing.html", AB_TESTING),
                Arguments.of(DATA_TYPES, "data-types.html", DATA_TYPES)
        );
    }
}