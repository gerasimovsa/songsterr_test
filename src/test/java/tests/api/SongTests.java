package tests.api;

import api.SongApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@Epic("Songsterr - API")
@Feature("Song")
@Owner("gerasimovsa")
public class SongTests extends TestBase {

    SongApi songApi = new SongApi();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Get song information")
    @Severity(CRITICAL)
    void getSongSuccessful() {

        SongModel expectedSong = SongModel.builder()
                .songId(12)
                .artist("Red Hot Chili Peppers")
                .title("Can't Stop")
                .build();

        SongModel responseSong = songApi.getSongById(cookie, expectedSong);

        assertThat(responseSong)
                .usingRecursiveComparison()
                .comparingOnlyFields("songId", "artist", "title")
                .isEqualTo(expectedSong);

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Get song not found")
    @Severity(CRITICAL)
    void getSongNotFound() {

        SongModel expectedSong = SongModel.builder()
                .songId(1212121212)
                .build();

        String errorResponse = songApi.getSongByIdNotFound(cookie, expectedSong);

        assertThat(errorResponse).contains("Not Found");

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Remove contributed song")
    @Severity(CRITICAL)
    void removeSong() {

        SongModel expectedSong = SongModel.builder()
                .songId(1212121212)
                .build();

        songApi.deleteSongById(cookie, expectedSong);
        String errorResponse = songApi.getSongByIdNotFound(cookie, expectedSong);

        assertThat(errorResponse).contains("Not Found");
    }

}






