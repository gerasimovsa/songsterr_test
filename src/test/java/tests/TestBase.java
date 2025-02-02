package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Cookie;
import pages.ApiUtils;

import static pages.ApiUtils.getAuthCookie;

public class TestBase {

    ApiUtils api = new ApiUtils();

    static Cookie cookie;

    public static Cookie getCookie() {
        return cookie;
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://songsterr.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        cookie = getAuthCookie();
        Configuration.headless = false;
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        if (testInfo.getTags().contains("AuthRequired")) {
            api.authorizeUser(cookie);
        }
    }
}
