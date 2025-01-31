package pages;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static pages.SongsterrApp.signInMenu;
import static pages.SongsterrApp.toolbar;


import org.openqa.selenium.Cookie;


public class ApiUtils {


    public static Cookie getAuthCookie() {
        Configuration.headless = true;
        open("/");
        toolbar.openSignInMenu();
        signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        signInMenu.submitSignIn();
        toolbar.verifyAccountNameIsDisplayed("regularS");
        refresh();
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("SongsterrT");
        closeWebDriver();
        return cookie;

    }

    public static void loginUser(Cookie cookie) {
        open("https://www.songsterr.com");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
    }


}
