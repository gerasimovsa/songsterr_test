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
@Feature("Song page")
@Owner("gerasimovsa")
public class SongPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @DisplayName("Open current song's artist")
    @Severity(CRITICAL)
    void openArtistOfCurrentSongTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.openArtistOfCurrentSong();

        app.searchPanel.verifyArtistSearchHaveText(newSong.getArtist());

    }

    @Test
    @DisplayName("Open revision tab")
    @Severity(NORMAL)
    void openRevisionTabTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.openRevisionsOfCurrentSong();

        app.songPage.verifyRevisionsOpened();

    }

    @Test
    @DisplayName("Add song to favorites not logged in")
    @Severity(CRITICAL)
    void addSongToFavoritesWhenNotLoggedInTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.addCurrentSongToFavorites();

        app.songPage.verifySignUpWarningIsDisplayed();

    }

    @Test
    @DisplayName("Move cursor for song")
    @Severity(CRITICAL)
    void moveCursorToSongBarTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.selectSongBarAtRow(2, 600);

        app.songPage.verifyCursorHasTransform(1337, -22, 0);

    }

    @Test
    @DisplayName("Marks song bar as learned")
    @Severity(NORMAL)
    void markSongBarAsLearnedTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.selectSongBarAtRow(2, 600);
        app.songPage.markCurrentBarAsLearned();

        app.songPage.verifyBarMarkedAsLearned(15);

    }

    @Test
    @DisplayName("Edit song bar from tab list")
    @Severity(NORMAL)
    void editSongBarFromTablistTest() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.selectSongBarAtRow(2, 600);
        app.songPage.enableEditModeFromBarMenu();

        app.songPage.verifyBarEditModeIsEnabled();

    }

}
