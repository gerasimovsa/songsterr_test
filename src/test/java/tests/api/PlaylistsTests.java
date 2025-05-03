package tests.api;

import api.PlaylistsApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class PlaylistsTests extends TestBase {

    PlaylistsApi playlistsApi = new PlaylistsApi();

    @Test
    @Tag("AuthRequired")
    void createNewPlaylistTest() throws JsonProcessingException {

        String playlistJSON = "{\"name\":\"TestPlaylist\"}";

        String playlistID = playlistsApi.postNewPlaylistAndGetId(cookie, playlistJSON);
        int numberID = Integer.parseInt(playlistID);
        playlistsApi.getPlaylistByID(cookie, numberID, playlistJSON);
    }

    @Test
    @Tag("AuthRequired")
    void removePlaylistTest() throws JsonProcessingException {

        String playlistJSON = "{\"name\":\"TestPlaylist\"}";

        String playlistID = playlistsApi.postNewPlaylistAndGetId(cookie, playlistJSON);
        int numberID = Integer.parseInt(playlistID);
        playlistsApi.deletePlaylistByID(cookie, numberID);
        playlistsApi.getPlaylistNotFound(cookie, numberID);
    }

}






