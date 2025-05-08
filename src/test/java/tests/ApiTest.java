package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ApiUtils;
import pages.SongsterrApp;

import static com.codeborne.selenide.Selenide.open;

public class ApiTest extends TestBase {

    SongsterrApp app = new SongsterrApp();
    ApiUtils api = new ApiUtils();


    @Test
    @Tag("AuthRequired")
    void addSongToFavoritesViaAPI() {
        Cookie authCookie = TestBase.getCookie();
        api.clearFavorites(authCookie);
        api.putSongToFavoritesByID(authCookie, "12");
        api.verifyFavoritesHasSong(authCookie, "Red Hot Chili Peppers", "Can't Stop");
    }

    @Test
    @Tag("AuthRequired")
    void searchSongByTitleViaAPI() {
        Cookie authCookie = TestBase.getCookie();
        api.getSongsList(authCookie, "Does It Offend You Yeah", 50, 0);
    }


}
