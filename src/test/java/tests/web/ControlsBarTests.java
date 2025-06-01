package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Tag("Web")
@Epic("Songsterr - Web")
@Feature("Controls bar")
@Owner("gerasimovsa")
public class ControlsBarTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Edit song from menu")
    @Severity(CRITICAL)
    void editSongFromNoteMenuTest() {

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
    @DisplayName("Reset song edits")
    @Severity(CRITICAL)
    void resetSongEditsTest() {

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
    @DisplayName("Select instrument")
    @Severity(NORMAL)
    void selectTabInstrumentTest() {

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
    @DisplayName("Play song")
    @Severity(CRITICAL)
    void playSongTest() {

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
