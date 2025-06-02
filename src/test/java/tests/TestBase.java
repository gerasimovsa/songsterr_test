package tests;

import com.codeborne.selenide.Configuration;
import config.BrowserConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Cookie;
import utils.AllureAttachments;
import utils.AuthUtils;

import static com.codeborne.selenide.Selenide.*;
import static utils.AuthUtils.getAuthCookie;

public class TestBase {

    public static Cookie cookie;

    private static final AuthUtils api = new AuthUtils();

    private static final BrowserConfig config = ConfigFactory.create(BrowserConfig.class);

    @Step("Setting up browser configurations")
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = config.browser().getSelenideName();
        Configuration.baseUrl = config.baseUrl();
        Configuration.browserSize = config.browserSize();
        Configuration.timeout = config.timeout();
        Configuration.headless = config.headless();

        cookie = getAuthCookie();

    }

    @Step("Opening main page and adding auth cookie if needed")
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        open("/");
        if (testInfo.getTags().contains("AuthRequired")) {
            api.authorizeUser(cookie);
        }
    }

    @Step("Clearing up after test")
    @AfterEach
    void afterEach() {
        AllureAttachments.addAllureAttachments();
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
