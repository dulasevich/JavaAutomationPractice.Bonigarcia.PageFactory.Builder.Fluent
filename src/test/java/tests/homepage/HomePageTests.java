package tests.homepage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testData.ChapterData;
import tests.BaseTest;

public class HomePageTests extends BaseTest {

    private static final String HOMEPAGE_DESCRIPTION_TEXT = "This site contains a collection of sample web pages to be " +
            "tested with Selenium WebDriver. Check out the O'Reilly book and the source code on GitHub.";

    @Test
    void chaptersTest() {
        ChapterData.allChapters().forEach(chapter -> Assertions.assertAll(
                () -> Assertions.assertTrue(homePage.isChapterDisplayed(chapter.getChapterName()),
                        "Chapter not displayed: " + chapter.getChapterName()),
                () -> Assertions.assertEquals(chapter.getLinks(), homePage.getChapterLinks(chapter.getChapterName()),
                        "Incorrect links for chapter: " + chapter.getChapterName())
        ));
    }

    @Test
    void descriptionText() {
        Assertions.assertEquals(HOMEPAGE_DESCRIPTION_TEXT, homePage.getDescription(), "Description is wrong");
    }
}

