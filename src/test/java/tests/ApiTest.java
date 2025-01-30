package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ApiUtils;

import java.util.Set;

public class ApiTest {

    ApiUtils utils = new ApiUtils();

    @Test
    void login(){
        Cookie cookie = utils.getAuthCookie();
        System.out.println(cookie);
        utils.loginUser(cookie);
    }
}
