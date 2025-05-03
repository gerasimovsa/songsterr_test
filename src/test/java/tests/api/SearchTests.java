package tests.api;

import api.SearchApi;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class SearchTests extends TestBase {

    SearchApi searchApiApi = new SearchApi();

    @Test
    @Tag("AuthRequired")
    void searchSongSuccessful() {

        String artist = "Red Hot Chili Peppers";
        int artistID = 12;

        searchApiApi.getSearchResultsByArtist(cookie, artist, artistID, "3");

    }

    @Test
    @Tag("AuthRequired")
    void searchSongNotFound() {

        String artist = "TakoiPesniNet";

        searchApiApi.getNoSearchResults(cookie, artist);

    }

}






