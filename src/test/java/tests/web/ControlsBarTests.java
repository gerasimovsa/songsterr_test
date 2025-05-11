package tests.web;

import models.SongModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class ControlsBarTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @Tag("AuthRequired")
    void editSongFromNoteMenu() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());
        app.controlsBar.toggleEditMode();
        app.songPage.selectOptionInNoteEditingMenu("Set 1/2 duration");
        app.controlsBar.toggleEditMode();

        app.controlsBar.verifySongHasEditedStatus();
    }

    @Test
    @Tag("AuthRequired")
    void resetSongEdits() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.toolbar.openSearchPanel();
        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());
        app.controlsBar.toggleEditMode();
        app.songPage.selectOptionInNoteEditingMenu("Set 1/2 duration");
        app.controlsBar.toggleEditMode();
        app.controlsBar.resetEdits();

        app.controlsBar.verifySongIsNotEdited();

    }

    @Test
    void selectTabInstrument() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());
        app.controlsBar.selectInstrumentFromMixer("Drums");

        app.controlsBar.verifyCurrentTabInstrument("Drum");


    }

    @Test
    void playSong() {

        SongModel newSong = SongModel.builder()
                .artist("The Strokes")
                .title("Happy Ending")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());
        app.controlsBar.playSong();

        app.songPage.verifyCursorIsNotOnDefaultPosition(93, -22, 0);

    }
}
