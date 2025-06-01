package tests.api;

import api.FavoritesApi;
import io.qameta.allure.*;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@Epic("Songsterr - API")
@Feature("Favorites")
@Owner("gerasimovsa")
public class FavoritesTests extends TestBase {

    FavoritesApi favoritesApi = new FavoritesApi();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Adding song to favorites")
    @Severity(CRITICAL)
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
                .comparingOnlyFields("songId", "artist", "title")
                .isEqualTo(expectedSong);
    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Removing song from favorites")
    @Severity(CRITICAL)
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




