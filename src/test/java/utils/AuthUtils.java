package utils;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;


public class AuthUtils {

    private static final AuthConfig config = ConfigFactory.create(AuthConfig.class);


    public static Cookie getAuthCookie() {
        open("/");
        $("#menu-signin").shouldBe(visible).click();
        $("[name='email']").shouldBe(empty).setValue(config.authEmail());
        $("[name='password']").shouldBe(empty).setValue(config.authPassword());
        Selenide.sleep(2000);
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text(config.authUsername()));
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("SongsterrT");
        closeWebDriver();
        if (cookie != null) {
            return cookie;
        } else {
            throw new RuntimeException("SongsterrT auth cookie is not extracted");
        }
    }

    public void authorizeUser(Cookie cookie) {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
    }
}
