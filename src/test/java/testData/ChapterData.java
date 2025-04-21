package testData;

import builder.Chapter;

import java.util.List;

public class ChapterData {

    public static final Chapter FUNDAMENTALS;
    public static final Chapter BROWSER_AGNOSTIC;
    public static final Chapter BROWSER_SPECIFIC;
    public static final Chapter POM;
    public static final Chapter FRAMEWORK_SPECIFIC;
    public static final Chapter THIRD_PARTY;

    static {
        FUNDAMENTALS = Chapter.builder()
                .chapterName("Chapter 3. WebDriver Fundamentals")
                .links(List.of("Web form", "Navigation", "Dropdown menu", "Mouse over", "Drag and drop", "Draw in canvas",
                        "Loading images", "Slow calculator"))
                .build();

        BROWSER_AGNOSTIC = Chapter.builder()
                .chapterName("Chapter 4. Browser-Agnostic Features")
                .links(List.of("Long page", "Infinite scroll", "Shadow DOM", "Cookies", "Frames", "IFrames", "Dialog boxes",
                        "Web storage"))
                .build();

        BROWSER_SPECIFIC = Chapter.builder()
                .chapterName("Chapter 5. Browser-Specific Manipulation")
                .links(List.of("Geolocation", "Notifications", "Get user media", "Multilanguage", "Console logs"))
                .build();

        POM = Chapter.builder()
                .chapterName("Chapter 7. The Page Object Model (POM)")
                .links(List.of("Login form", "Slow login"))
                .build();

        FRAMEWORK_SPECIFIC = Chapter.builder()
                .chapterName("Chapter 8. Testing Framework Specifics")
                .links(List.of("Random calculator"))
                .build();

        THIRD_PARTY = Chapter.builder()
                .chapterName("Chapter 9. Third-Party Integrations")
                .links(List.of("Download files", "A/B Testing", "Data types"))
                .build();
    }

    public static List<Chapter> allChapters() {
        return List.of(
                FUNDAMENTALS,
                BROWSER_AGNOSTIC,
                BROWSER_SPECIFIC,
                POM,
                FRAMEWORK_SPECIFIC,
                THIRD_PARTY
        );
    }
}
