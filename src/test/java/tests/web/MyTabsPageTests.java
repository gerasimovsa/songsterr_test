package tests.web;


import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;


import static com.codeborne.selenide.Selenide.open;

public class MyTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void addSongToFavorites() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");
        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.verifyFavoritesTabHasSong("Happy Ending", "The Strokes");

    }

    @Test
    void removeSongFromFavorites() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();
        app.myTabsPage.removeFirstSongFromFavorites();

        app.myTabsPage.verifyFavoritesTabIsEmpty();

    }

    @Test
    void removeSongFromContributions() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");


        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewBlankTab("My Test Song", "Test Band");
        app.newTabPage.verifyNewTabPanelIsClosed();

        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openContributionsTab();
        app.searchPanel.openSearchResultByText("My Test Song");
        app.songPage.deleteContributedSong("My Test Song", "test band");

        app.songPage.verifySongIsDeleted();


    }

    @Test
    void addSongToPlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist("MyTestPlaylist");

        app.myTabsPage.openFavoritesTab();
        app.myTabsPage.addSongFromFavoritesToPlaylist("MyTestPlaylist");

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.verifyPlaylistHasSong("The Strokes", "Happy Ending");

    }

    @Test
    void removeSongFromPlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist("MyTestPlaylist");

        app.myTabsPage.openFavoritesTab();
        app.myTabsPage.addSongFromFavoritesToPlaylist("MyTestPlaylist");

        app.myTabsPage.openPlaylistsTab();

        app.myTabsPage.removeSongFromPlaylist();

        app.myTabsPage.verifyPlaylistIsEmpty();

    }

    @Test
    void deletePlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPage.openPlaylistsTab();
        app.myTabsPage.createPlaylist("MyTestPlaylist");

        app.myTabsPage.deletePlaylist("MyTestPlaylist");


        app.myTabsPage.verifyPlaylistsTabIsEmpty();

    }

}
