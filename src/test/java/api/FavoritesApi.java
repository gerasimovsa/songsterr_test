package api;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.List;


public class FavoritesApi {

    String favoritesEndpoint = "/api/favorites";

    public void clearFavorites(Cookie cookie) {
        Response response = given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint);
        response.then()
                .statusCode(200);
        List<Integer> ids = response.jsonPath().getList("songId");
        if (!ids.isEmpty()) {
            for (Integer songId : ids) {
                given()
                        .cookie(String.valueOf(cookie))
                        .when()
                        .delete("https://www.songsterr.com" + favoritesEndpoint + "/" + songId)
                        .then()
                        .statusCode(201);
            }
        }
    }

    public void putSongToFavorites(Cookie cookie, String id) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .put("https://www.songsterr.com" + favoritesEndpoint + "/" + id)
                .then()
                .log().status()
                .statusCode(201);

    }

    public void deleteSongFromFavorites(Cookie cookie, String id) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .delete("https://www.songsterr.com" + favoritesEndpoint + "/" + id)
                .then()
                .statusCode(201);

    }

    public void verifyFavoritesHasSong(Cookie cookie, String artist, String title) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().body()
                .statusCode(200)
                .body("artist", hasItem(artist))
                .body("title", hasItem(title));
    }

    public void verifyFavoritesIsEmpty(Cookie cookie) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().body()
                .statusCode(200)
                .body(empty());
    }
}
