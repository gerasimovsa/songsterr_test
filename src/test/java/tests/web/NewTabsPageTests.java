package tests.web;

import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class NewTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void createNewBlankSong() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewBlankTab("My Test Song", "Test Band");

        app.newTabPage.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle("My Test Song", "Test Band");

    }

    @Test
    void createNewSongFromGPFile() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewTabFromGuitarProTab
                ("Song from GP File", "Test GP Band", "src/test/resources/my_gp_file.gp3");

        app.newTabPage.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle("Song from GP File", "Test GP Band");

    }

    @Test
    void cannotCreateSongWithBlankTitle() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");
        app.toolbar.openNewTabPanel();

        app.newTabPage.createNewBlankTab("", "Test Band");

        app.newTabPage.verifyBlankTitleFieldHasWarning();

    }

}

