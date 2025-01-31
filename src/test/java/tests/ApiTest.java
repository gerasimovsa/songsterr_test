package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ApiUtils;
import pages.SongsterrApp;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static pages.SongsterrApp.toolbar;

public class ApiTest extends TestBase{

    @Test
    @Tag("AuthRequired")
    void login(){
        open("/");
        toolbar.openMyTabsPanel();
        sleep(5000);
    }
}
