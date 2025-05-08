package tests.api;

import api.FavoritesApi;
import models.SongModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


import static org.assertj.core.api.Assertions.assertThat;


public class FavoritesTests extends TestBase {

    FavoritesApi favoritesApi = new FavoritesApi();

    @Test
    @Tag("AuthRequired")
    void addSongToFavoritesApiTest() {

        SongModel expectedSong = SongModel.builder()
                .songId(12)
                .artist("Red Hot Chili Peppers")
                .title("Can't Stop")
                .build();

        favoritesApi.clearFavorites(cookie);
        favoritesApi.putSongToFavorites(cookie, expectedSong.getSongId());

        SongModel responseSongs = favoritesApi.getFavoriteSongs(cookie);

        assertThat(responseSongs)
                .usingRecursiveComparison()
                .comparingOnlyFields("songId", "artist", "title");
    }

    @Test
    @Tag("AuthRequired")
    void removeSongFromFavoritesApiTest() {

        SongModel expectedSong = SongModel.builder()
                .songId(12)
                .build();

        favoritesApi.clearFavorites(cookie);
        favoritesApi.putSongToFavorites(cookie, expectedSong.getSongId());
        favoritesApi.deleteSongFromFavorites(cookie, expectedSong.getSongId());

        String responseSongs = favoritesApi.getEmptyFavorites(cookie);

        assertThat(responseSongs).isEqualTo("[]");
    }

}




