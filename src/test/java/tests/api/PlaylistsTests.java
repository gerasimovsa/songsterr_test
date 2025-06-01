package tests.api;

import api.PlaylistsApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.PlaylistModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@Epic("Songsterr - API")
@Feature("Playlists")
@Owner("gerasimovsa")
public class PlaylistsTests extends TestBase {

    PlaylistsApi playlistsApi = new PlaylistsApi();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Creating new playlist")
    @Severity(CRITICAL)
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
    @DisplayName("Removing playlist")
    @Severity(CRITICAL)
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






