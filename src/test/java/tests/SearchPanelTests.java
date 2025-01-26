package tests;

import org.junit.jupiter.api.Test;
import pages.SongsterrApp;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchPanelTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void searchByTitle() {

        open("/");

        app.searchPanel.enterSearchQuery("Wonderwall");
        app.searchPanel.verifySearchResultTitlesHaveText("Wonderwall");

    }

    @Test
    void searchNoTabsFound() {

        open("/");

        app.searchPanel.enterSearchQuery("zxcvbnmqwertyui");
        app.searchPanel.verifyNoSearchResults();

    }

    @Test
    void searchByArtist() {

        open("/");

        app.searchPanel.enterSearchQuery("Radiohead");
        app.searchPanel.verifySearchResultArtistsHaveText("Radiohead");

    }

    @Test
    void clearSearchField() {

        open("/");

        app.searchPanel.enterSearchQuery("FortySecondsToMars");
        app.searchPanel.clearSearchBar();

        app.searchPanel.verifySearchResultsHaveNoText("FortySecondsToMars");

    }

    @Test
    void searchFilterSortByDifficulty() {

        open("/");

        app.searchPanel.enterSearchQuery("Strokes");
        app.searchPanel.filterSearchResultsByDifficulty("Advanced");

        app.searchPanel.verifySearchResultsCount(4);
        app.searchPanel.verifySearchResultsHaveDifficulty("Advanced");
    }

    @Test
    void searchFilterSortByCustomTuning() {

        open("/");

        app.searchPanel.enterSearchQuery("Michael Jackson");
        app.searchPanel.createCustomTuningFilter(3);

        app.searchPanel.verifySearchResultTitlesHaveText("Smooth Criminal");
        app.searchPanel.verifySearchResultArtistsHaveText("Michael Jackson");

    }

    @Test
    void openSongFromSearch() {

        open("/");

        app.searchPanel.enterSearchQuery("bondage fairies - gay wedding");
        app.searchPanel.selectSearchResultByText("Gay Wedding");

        app.searchPanel.verifySearchPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle("Gay Wedding","Bondage Fairies");

    }

    @Test
    void reopenSearchPanel() {

        open("/");

        app.searchPanel.enterSearchQuery("Frank Sinatra");
        app.searchPanel.closeSearchPanelByClickingSidebar();
        app.toolbar.openSearchPanel();

        app.searchPanel.verifySearchResultArtistsHaveText("Frank Sinatra");

    }

}


