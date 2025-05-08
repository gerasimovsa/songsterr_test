package tests.api;

import api.PlaylistsApi;
import models.PlaylistModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static org.assertj.core.api.Assertions.assertThat;


public class PlaylistsTests extends TestBase {

    PlaylistsApi playlistsApi = new PlaylistsApi();

    @Test
    @Tag("AuthRequired")
    void createNewPlaylistTest() {

        PlaylistModel expectedPlaylist = PlaylistModel.builder()
                .name("TestPlaylist")
                .build();

        PlaylistModel createdPlaylist = playlistsApi.postNewPlaylist(cookie, expectedPlaylist);
        PlaylistModel responsePlaylist = playlistsApi.getPlaylistByID(cookie, createdPlaylist.getPlaylistId());

        assertThat(createdPlaylist).isEqualTo(responsePlaylist);
    }

    @Test
    @Tag("AuthRequired")
    void removePlaylistTest() {

        PlaylistModel expectedPlaylist = PlaylistModel.builder()
                .name("TestPlaylist")
                .build();

        PlaylistModel createdPlaylist = playlistsApi.postNewPlaylist(cookie, expectedPlaylist);
        playlistsApi.deletePlaylistByID(cookie, createdPlaylist.getPlaylistId());
        String responsePlaylist = playlistsApi.getPlaylistNotFound(cookie, createdPlaylist.getPlaylistId());

        assertThat(responsePlaylist).contains("Not Found");
    }

}






