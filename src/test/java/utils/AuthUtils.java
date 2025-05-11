package utils;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


import org.openqa.selenium.Cookie;


public class AuthUtils {

    public static Cookie getAuthCookie() {
        open("/");
        $("#menu-signin").shouldBe(visible).click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("mypass123");
        Selenide.sleep(2000);
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regularS"));
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("SongsterrT");
        if (cookie != null) {
            return cookie;
        } else {
            throw new RuntimeException("SongsterrT auth cookie is not extracted");
        }
    }

    public void authorizeUser(Cookie cookie) {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();    }
}
