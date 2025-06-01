package config;

import lombok.Getter;

@Getter
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("msedge"),
    SAFARI("safari");

    private final String selenideName;

    Browser(String selenideName) {
        this.selenideName = selenideName;
    }

}
