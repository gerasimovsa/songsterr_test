package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.Cookie;

import java.util.Set;


//https://www.songsterr.com/auth/signin
// {"email":"gerasimovsa20@gmail.com", "password":"mypass123"}
//get songs from search
//get my songs
//

public class ApiUtils {

    SongsterrApp app = new SongsterrApp();

    public Cookie getAuthCookie() {
        open("https://www.songsterr.com");
        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        sleep(1000);
        refresh();
        sleep(1000);
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("SongsterrT");
        closeWebDriver();
        return cookie;

    }

    public void loginUser(Cookie cookie) {
        open("https://www.songsterr.com");
        sleep(5000);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        sleep(2000);
        refresh();
        sleep(10000);


    }



}
