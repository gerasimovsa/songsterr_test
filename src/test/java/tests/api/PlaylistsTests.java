package tests.api;

import api.ApiUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class PlaylistsTests extends TestBase {

    ApiUtils api = new ApiUtils();

    @Test
    @Tag("AuthRequired")
    void addSongToFavoritesApiTest() {

        String artist = "Red Hot Chili Peppers";
        String title = "Can't Stop";
        String songID = "12";

        api.clearFavorites(cookie);
        api.putSongToFavorites(cookie, songID);

        api.verifyFavoritesHasSong(cookie, artist, title);
    }

    @Test
    @Tag("AuthRequired")
    void removeSongFromFavoritesApiTest() {
        String songID = "12";

        api.clearFavorites(cookie);
        api.putSongToFavorites(cookie, songID);
        api.deleteSongFromFavorites(cookie, songID);

        api.verifyFavoritesIsEmpty(cookie);
    }

}






