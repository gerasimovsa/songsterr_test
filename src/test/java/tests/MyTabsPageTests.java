package tests;


import org.junit.jupiter.api.Test;
import pages.SongsterrApp;


import static com.codeborne.selenide.Selenide.open;

public class MyTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void addSongToFavorites() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");
        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPanel.verifyFavoritesTabHasSong("Happy Ending", "The Strokes");

    }

    @Test
    void removeSongFromFavorites() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();
        app.myTabsPanel.removeFirstSongFromFavorites();

        app.myTabsPanel.verifyFavoritesTabIsEmpty();

    }

    @Test
    void removeSongFromContributions() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");


        app.toolbar.openNewTabPanel();
        app.newTabPanel.createNewBlankTab("My Test Song", "Test Band");
        app.newTabPanel.verifyNewTabPanelIsClosed();

        app.toolbar.openMyTabsPanel();

        app.myTabsPanel.openContributionsTab();
        app.searchPanel.openSearchResultByText("My Test Song");
        app.songPage.deleteContributedSong("My Test Song", "test band");

        app.songPage.verifySongIsDeleted();


    }

    @Test
    void addSongToPlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPanel.openPlaylistsTab();
        app.myTabsPanel.createPlaylist("MyTestPlaylist");

        app.myTabsPanel.openFavoritesTab();
        app.myTabsPanel.addSongFromFavoritesToPlaylist("MyTestPlaylist");

        app.myTabsPanel.openPlaylistsTab();
        app.myTabsPanel.verifyPlaylistHasSong("The Strokes", "Happy Ending");

    }

    @Test
    void removeSongFromPlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPanel.openPlaylistsTab();
        app.myTabsPanel.createPlaylist("MyTestPlaylist");

        app.myTabsPanel.openFavoritesTab();
        app.myTabsPanel.addSongFromFavoritesToPlaylist("MyTestPlaylist");

        app.myTabsPanel.openPlaylistsTab();

        app.myTabsPanel.removeSongFromPlaylist();

        app.myTabsPanel.verifyPlaylistIsEmpty();

    }

    @Test
    void deletePlaylist() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.openSearchResultByText("Happy Ending");

        app.songPage.addCurrentSongToFavorites();
        app.toolbar.openMyTabsPanel();

        app.myTabsPanel.openPlaylistsTab();
        app.myTabsPanel.createPlaylist("MyTestPlaylist");

        app.myTabsPanel.deletePlaylist("MyTestPlaylist");


        app.myTabsPanel.verifyPlaylistsTabIsEmpty();

    }

}
