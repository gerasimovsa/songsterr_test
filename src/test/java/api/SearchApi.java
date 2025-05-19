package api;


import io.qameta.allure.Step;
import models.RecordsModel;
import models.SongModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.SearchSpec.searchRequest;
import static specs.SearchSpec.searchResponse;


public class SearchApi {

    @Step("GET search result by artist")
    public RecordsModel getSearchResultsByArtist(Cookie cookie, SongModel song, Integer numberOfResults) {
        return given()
                .spec(searchRequest)
                .cookie(String.valueOf(cookie))
                .queryParam("pattern", song.getArtist())
                .queryParam("size", String.valueOf(numberOfResults))
                .when()
                .get()
                .then()
                .spec(searchResponse)
                .extract().as(RecordsModel.class);
    }

}
