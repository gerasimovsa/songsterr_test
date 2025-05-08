package tests.api;

import api.SongApi;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class SongTests extends TestBase {

    SongApi songApi = new SongApi();

    @Test
    @Tag("AuthRequired")
    void getSongSuccessful() {

        String artist = "Red Hot Chili Peppers";
        String title = "Can't Stop";
        int songID = 12;

        songApi.getSongById(cookie, songID, artist, title);

    }

    @Test
    @Tag("AuthRequired")
    void getSongNotFound() {

        int songID = 12121212;

        songApi.getSongByIdNotFound(cookie, songID);

    }

    @Test
    @Tag("AuthRequired")
    void removeSong() {

        int songID = 1440607;

        songApi.deleteSongById(cookie, songID);
        songApi.getSongByIdNotFound(cookie, songID);

    }

}






