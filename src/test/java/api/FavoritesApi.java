package api;


import static io.restassured.RestAssured.given;


import io.restassured.response.Response;
import models.SongModel;
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

    public void putSongToFavorites(Cookie cookie, Integer id) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .put("https://www.songsterr.com" + favoritesEndpoint + "/" + id)
                .then()
                .log().status()
                .statusCode(201);

    }

    public void deleteSongFromFavorites(Cookie cookie, Integer id) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .delete("https://www.songsterr.com" + favoritesEndpoint + "/" + id)
                .then()
                .statusCode(201);

    }

    public SongModel getFavoriteSongs(Cookie cookie) {
        return given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getList(".", SongModel.class).get(0);
    }

    public String getEmptyFavorites(Cookie cookie) {
        return given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().body()
                .statusCode(200)
                .extract().body().asString();
    }
}
