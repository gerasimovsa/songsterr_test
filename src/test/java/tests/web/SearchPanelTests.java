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
@Feature("Searchbar")
@Owner("gerasimovsa")
public class SearchPanelTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @DisplayName("Search song by title")
    @Severity(CRITICAL)
    void searchByTitleTest() {

        SongModel newSong = SongModel.builder()
                .title("Wonderwall")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());

    }

    @Test
    @DisplayName("Search song not found")
    @Severity(CRITICAL)
    void searchNoTabsFoundTest() {

        SongModel newSong = SongModel.builder()
                .title("zxcvbnmqwertyui")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getTitle());
        app.searchPanel.verifyNoSearchResults();

    }

    @Test
    @DisplayName("Search song by artist")
    @Severity(CRITICAL)
    void searchByArtistTest() {

        SongModel newSong = SongModel.builder()
                .artist("Radiohead")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.verifySearchResultArtistsHaveText(newSong.getArtist());

    }

    @Test
    @DisplayName("Clear search bar")
    @Severity(CRITICAL)
    void clearSearchFieldTest() {

        SongModel newSong = SongModel.builder()
                .artist("FortySecondsToMars")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.clearSearchBar();

        app.searchPanel.verifySearchResultsHaveNoText(newSong.getArtist());

    }

    @Test
    @DisplayName("Filter search results by difficulty")
    @Severity(NORMAL)
    void searchFilterSortByDifficultyTest() {

        SongModel newSong = SongModel.builder()
                .artist("Strokes")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.filterSearchResultsByDifficulty("Advanced");

        app.searchPanel.verifySearchResultsCount(4);
        app.searchPanel.verifySearchResultsHaveDifficulty("Advanced");
    }

    @Test
    @DisplayName("Filter search results by custom tuning")
    @Severity(NORMAL)
    void searchFilterSortByCustomTuningTest() {

        SongModel newSong = SongModel.builder()
                .artist("Michael Jackson")
                .title("Smooth Criminal")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.createCustomTuningFilter(3);

        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());
        app.searchPanel.verifySearchResultArtistsHaveText(newSong.getArtist());

    }

    @Test
    @DisplayName("Open song from search bar")
    @Severity(CRITICAL)
    void openSongFromSearchTest() {

        SongModel newSong = SongModel.builder()
                .artist("Bondage Fairies")
                .title("Gay Wedding")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist() + " - " + newSong.getTitle());
        app.searchPanel.openSearchResultByText(newSong.getTitle());

        app.searchPanel.verifySearchPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle(newSong.getTitle(),newSong.getArtist());

    }

    @Test
    @DisplayName("Open search panel")
    @Severity(CRITICAL)
    void reopenSearchPanelTest() {

        SongModel newSong = SongModel.builder()
                .artist("Frank Sinatra")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.closeSearchPanelByClickingSidebar();
        app.toolbar.openSearchPanel();

        app.searchPanel.verifySearchPanelIsOpened();
        app.searchPanel.verifySearchResultArtistsHaveText(newSong.getArtist());

    }

}


