package tests.web;

import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class ControlsBarTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void editSongFromNoteMenu() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.verifySearchResultTitlesHaveText("Happy Ending");
        app.controlsBar.toggleEditMode();
        app.songPage.selectOptionInNoteEditingMenu("Set 1/2 duration");
        app.controlsBar.toggleEditMode();

        app.controlsBar.verifySongHasEditedStatus();
    }

    @Test
    void resetSongEdits() {

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("gerasimovsa20@gmail.com", "mypass123");
        app.signInPage.submitSignIn();
        app.toolbar.verifyAccountNameIsDisplayed("regular_s");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.verifySearchResultTitlesHaveText("Happy Ending");
        app.controlsBar.toggleEditMode();
        app.songPage.selectOptionInNoteEditingMenu("Set 1/2 duration");
        app.controlsBar.toggleEditMode();
        app.controlsBar.resetEdits();

        app.controlsBar.verifySongIsNotEdited();

    }

    @Test
    void selectTabInstrument() {

        open("/");

        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.verifySearchResultTitlesHaveText("Happy Ending");
        app.controlsBar.selectInstrumentFromMixer("Drums");

        app.controlsBar.verifyCurrentTabInstrument("Drum");


    }

    @Test
    void playSong() {

        open("/");

        app.searchPanel.enterSearchQuery("happy ending the strokes");
        app.searchPanel.verifySearchResultTitlesHaveText("Happy Ending");
        app.controlsBar.playSong();

        app.songPage.verifyCursorIsNotOnDefaultPosition(93, -22, 0);

    }
}
