package api;


import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SearchApi {

    String searchEndpoint = "/api/search";

    public void getSearchResultsByArtist(Cookie cookie, String query, int artistID, String resultsNumber) {
        given()
            .cookie(String.valueOf(cookie))
            .log().all()
            .queryParam("pattern", query)
            .queryParam("size", resultsNumber)
            .when()
            .get("https://www.songsterr.com" + searchEndpoint)
            .then()
            .log().all()
            .statusCode(200)
            .body("records.artist", hasItem(query))
            .body("records.artistId", everyItem(equalTo(artistID)));
    }

    public void getNoSearchResults(Cookie cookie, String query) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .queryParam("pattern", query)
                .when()
                .get("https://www.songsterr.com" + searchEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .body("records", is(empty()));
    }

}
