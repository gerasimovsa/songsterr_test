package tests.web;

import models.SongModel;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class SearchPanelTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void searchByTitle() {

        SongModel newSong = SongModel.builder()
                .title("Wonderwall")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getTitle());
        app.searchPanel.verifySearchResultTitlesHaveText(newSong.getTitle());

    }

    @Test
    void searchNoTabsFound() {

        SongModel newSong = SongModel.builder()
                .title("zxcvbnmqwertyui")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getTitle());
        app.searchPanel.verifyNoSearchResults();

    }

    @Test
    void searchByArtist() {

        SongModel newSong = SongModel.builder()
                .artist("Radiohead")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.verifySearchResultArtistsHaveText(newSong.getArtist());

    }

    @Test
    void clearSearchField() {

        SongModel newSong = SongModel.builder()
                .artist("FortySecondsToMars")
                .build();

        open("/");

        app.searchPanel.enterSearchQuery(newSong.getArtist());
        app.searchPanel.clearSearchBar();

        app.searchPanel.verifySearchResultsHaveNoText(newSong.getArtist());

    }

    @Test
    void searchFilterSortByDifficulty() {

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
    void searchFilterSortByCustomTuning() {

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
    void openSongFromSearch() {

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
    void reopenSearchPanel() {

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


