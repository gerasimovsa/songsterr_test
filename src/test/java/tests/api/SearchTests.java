package tests.api;

import api.SearchApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.RecordsModel;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@Epic("Songsterr - API")
@Feature("Searchbar")
@Owner("gerasimovsa")
public class SearchTests extends TestBase {

    SearchApi searchApi = new SearchApi();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Search results found")
    @Severity(CRITICAL)
    void searchSongSuccessful() {

        SongModel expectedSong = SongModel.builder()
                .artist("Red Hot Chili Peppers")
                .artistId(12)
                .build();

        RecordsModel responseRecords = searchApi.getSearchResultsByArtist(cookie, expectedSong, 3);

        assertThat(responseRecords.getRecords())
                .extracting("artistId")
                .containsOnly(expectedSong.getArtistId());
    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Search results not found")
    @Severity(CRITICAL)
    void searchSongNotFound() {

        SongModel expectedSong = SongModel.builder()
                .artist("ThereIsNoSuchArtist")
                .artistId(12)
                .build();

        RecordsModel responseRecords = searchApi.getSearchResultsByArtist(cookie, expectedSong, 3);

        assertThat(responseRecords.getRecords()).isEmpty();
    }

}






