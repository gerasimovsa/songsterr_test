package tests;

import org.junit.jupiter.api.Test;
import pages.SongsterrApp;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class NewTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void createNewBlankSong() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();
        app.newTabPanel.createNewBlankTab("My Test Song", "Test Band");

        app.newTabPanel.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle("My Test Song", "Test Band");

    }

    @Test
    void createNewSongFromGPFile() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();
        app.newTabPanel.createNewTabFromGuitarProTab
                ("Song from GP File", "Test GP Band", "src/test/resources/my_gp_file.gp3");

        app.newTabPanel.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle("Song from GP File", "Test GP Band");

    }

    @Test
    void cannotCreateSongWithBlankTitle() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInMenu.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();

        app.newTabPanel.createNewBlankTab("", "Test Band");

        app.newTabPanel.verifyBlankTitleFieldHasWarning();

    }

}

