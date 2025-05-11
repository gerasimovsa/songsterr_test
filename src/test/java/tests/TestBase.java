package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Cookie;
import utils.AuthUtils;

import static com.codeborne.selenide.Selenide.*;
import static utils.AuthUtils.getAuthCookie;

public class TestBase {

    AuthUtils api = new AuthUtils();

    public static Cookie cookie;

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
            open("/");
            api.authorizeUser(cookie);
        }
    }

    @AfterEach
    void afterEach() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
