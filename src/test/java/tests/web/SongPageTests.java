package tests.web;

import models.SongModel;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;


import static com.codeborne.selenide.Selenide.*;

public class SongPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void openArtistSearch() {

        SongModel newSong = SongModel.builder()
                .title("Rhubarb")
                .artist("Aphex Twin")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());
        app.songPage.openArtistOfCurrentSong();

        app.searchPanel.verifySearchResultArtistsHaveText(newSong.getArtist());

    }

    @Test
    void openRevisionTab() {

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
    void addSongToFavoritesWhenNotLoggedIn() {

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
    void moveCursorToSongBar() {

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
    void markSongBarAsLearned() {

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
    void editSongBarFromTablist() {

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
