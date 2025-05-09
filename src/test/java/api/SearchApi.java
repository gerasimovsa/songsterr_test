package api;


import models.RecordsModel;
import models.SongModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;


public class SearchApi {

    String searchEndpoint = "/api/search";

    public RecordsModel getSearchResultsByArtist(Cookie cookie, SongModel song, Integer numberOfResults) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .queryParam("pattern", song.getArtist())
                .queryParam("size", String.valueOf(numberOfResults))
                .when()
                .get("https://www.songsterr.com" + searchEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(RecordsModel.class);
    }

}
