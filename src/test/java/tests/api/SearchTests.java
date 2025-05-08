package tests.api;

import api.SearchApi;
import models.RecordsModel;
import models.SongModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchTests extends TestBase {

    SearchApi searchApi = new SearchApi();

    @Test
    @Tag("AuthRequired")
    void searchSongSuccessful() {

        SongModel expectedSong = SongModel.builder()
                .songId(12)
                .artist("Red Hot Chili Peppers")
                .title("Can't Stop")
                .artistId(12)
                .build();

        RecordsModel responseRecords = searchApi.getSearchResultsByArtist(cookie, expectedSong, 3);

        assertThat(responseRecords.getRecords())
                .extracting("artistId")
                .containsOnly(expectedSong.getArtistId());
    }

//    @Test
//    @Tag("AuthRequired")
//    void searchSongNotFound() {
//
//        String artist = "TakoiPesniNet";
//
//        searchApi.getNoSearchResults(cookie, artist);
//
//    }

}






