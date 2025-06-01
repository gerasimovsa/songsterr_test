package tests.web;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.PlaylistModel;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;


import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Tag("Web")
@Epic("Songsterr - Web")
@Feature("My Tabs page")
@Owner("gerasimovsa")
public class MyTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Adding song to favorites")
    @Severity(CRITICAL)
    void addSongToFavoritesTest() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.verifyFavoritesTabHasSong(newSong.getTitle(), newSong.getArtist());

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Remove song from favorites")
    @Severity(CRITICAL)
    void removeSongFromFavoritesTest() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();
        app.myTabsPage.removeFirstSongFromFavorites();

        app.myTabsPage.verifyFavoritesTabIsEmpty();

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Remove song from contributions")
    @Severity(CRITICAL)
    void removeSongFromContributionsTest() {

        SongModel newSong = SongModel.builder()
                .artist("Test Band")
                .title("My Test Song")
                .build();

        open("/");

        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewBlankTab(newSong.getTitle(), newSong.getArtist());
        app.newTabPage.verifyNewTabPanelIsClosed();

        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openContributionsTab();
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.deleteContributedSong(newSong.getTitle(), newSong.getArtist());

        app.songPage.verifySongIsDeleted();


    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Add song to playlist")
    @Severity(CRITICAL)
    void addSongToPlaylistTest() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        PlaylistModel newPlaylist = PlaylistModel.builder()
                .name("MyTestPlaylist")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist(newPlaylist.getName());

        app.myTabsPage.openFavoritesTab();
        app.myTabsPage.addSongFromFavoritesToPlaylist(newPlaylist.getName());

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.verifyPlaylistHasSong(newSong.getTitle(), newSong.getArtist());

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Remove song from playlist")
    @Severity(CRITICAL)
    void removeSongFromPlaylistTest() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        PlaylistModel newPlaylist = PlaylistModel.builder()
                .name("MyTestPlaylist")
                .build();


        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist(newPlaylist.getName());

        app.myTabsPage.openFavoritesTab();
        app.myTabsPage.addSongFromFavoritesToPlaylist(newPlaylist.getName());

        app.myTabsPage.openPlaylistsTab();

        app.myTabsPage.removeSongFromPlaylist();

        app.myTabsPage.verifyPlaylistIsEmpty();

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Delete playlist")
    @Severity(CRITICAL)
    void deletePlaylistTest() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        PlaylistModel newPlaylist = PlaylistModel.builder()
                .name("MyTestPlaylist")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist(newPlaylist.getName());

        app.myTabsPage.deletePlaylist(newPlaylist.getName());


        app.myTabsPage.verifyPlaylistsTabIsEmpty();

    }

}
