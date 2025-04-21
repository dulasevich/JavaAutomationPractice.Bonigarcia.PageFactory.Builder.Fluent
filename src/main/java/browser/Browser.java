package browser;

import java.util.Arrays;

public enum Browser {
    CHROME, FIREFOX, EDGE;

    public static Browser fromString(String name) {
        return Arrays.stream(Browser.values())
                .filter(value -> value.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid browser: " + name));
    }
}