package tests.api;

import api.SongApi;
import models.SongModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static org.assertj.core.api.Assertions.assertThat;


public class SongTests extends TestBase {

    SongApi songApi = new SongApi();

    @Test
    @Tag("AuthRequired")
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
    void getSongNotFound() {

        SongModel expectedSong = SongModel.builder()
                .songId(1212121212)
                .build();

        String errorResponse = songApi.getSongByIdNotFound(cookie, expectedSong);

        assertThat(errorResponse).contains("Not Found");

    }

    @Test
    @Tag("AuthRequired")
    void removeSong() {

        SongModel expectedSong = SongModel.builder()
                .songId(1212121212)
                .build();

        songApi.deleteSongById(cookie, expectedSong);
        String errorResponse = songApi.getSongByIdNotFound(cookie, expectedSong);

        assertThat(errorResponse).contains("Not Found");
    }

}






