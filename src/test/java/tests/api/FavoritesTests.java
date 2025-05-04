package tests.api;

import api.FavoritesApi;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class FavoritesTests extends TestBase {

    FavoritesApi favoritesApi = new FavoritesApi();

    @Test
    @Tag("AuthRequired")
    void addSongToFavoritesApiTest() {

        String artist = "Red Hot Chili Peppers";
        String title = "Can't Stop";
        String songID = "12";

        favoritesApi.clearFavorites(cookie);
        favoritesApi.putSongToFavorites(cookie, songID);

        favoritesApi.verifyFavoritesHasSong(cookie, artist, title);
    }

    @Test
    @Tag("AuthRequired")
    void removeSongFromFavoritesApiTest() {
        String songID = "12";

        favoritesApi.clearFavorites(cookie);
        favoritesApi.putSongToFavorites(cookie, songID);
        favoritesApi.deleteSongFromFavorites(cookie, songID);

        favoritesApi.verifyFavoritesIsEmpty(cookie);
    }

}




