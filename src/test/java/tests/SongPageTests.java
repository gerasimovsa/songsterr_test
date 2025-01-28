package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import pages.SongsterrApp;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SongPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void openArtistSearch() {

        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.openArtistOfCurrentSong();

        app.searchPanel.verifySearchResultArtistsHaveText("Aphex Twin");

    }

    @Test
    void openRevisionTab() {

        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.openRevisionsOfCurrentSong();

        app.songPage.verifyRevisionsOpened();

    }

    @Test
    void addSongToFavoritesWhenNotLoggedIn() {

        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.addCurrentSongToFavorites();

        app.songPage.verifySignUpWarningIsDisplayed();

    }

    @Test
    void moveCursorToSongBar() {

        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.selectSongBarAtRow(2, 600);

        app.songPage.verifyCursorHasTransform(1337, -22, 0);

    }

    @Test
    void markSongBarAsLearned() {

        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.selectSongBarAtRow(2, 600);
        app.songPage.markCurrentBarAsLearned();

        app.songPage.verifyBarMarkedAsLearned(15);

    }

    @Test
    void editSongBarFromTablist() {


        open("/");

        app.searchPanel.enterSearchQuery("aphex twin - rhubarb");
        app.searchPanel.openSearchResultByText("Rhubarb");
        app.songPage.selectSongBarAtRow(2, 600);
        app.songPage.enableEditModeForCurrentSong();

        app.songPage.verifyEditModeIsaEnabled();
    }

}
