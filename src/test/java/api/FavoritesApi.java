package api;


import static io.restassured.RestAssured.given;
import static specs.FavoritesSpec.*;


import io.qameta.allure.Step;
import models.SongModel;
import org.openqa.selenium.Cookie;

import java.util.List;


public class FavoritesApi {

    @Step("GET all favorite songs IDs | DELETE each song by ID")
    public void clearFavorites(Cookie cookie) {
        List<Integer> ids =
                given()
                        .spec(FavoritesRequest)
                        .cookie(String.valueOf(cookie))
                        .when()
                        .get()
                        .then()
                        .spec(getFavoritesResponseSuccess)
                        .extract().jsonPath().getList("songId");

        if (!ids.isEmpty()) {
            for (Integer songId : ids) {
                given()
                        .spec(FavoritesRequest)
                        .cookie(String.valueOf(cookie))
                        .when()
                        .delete("/" + songId)
                        .then()
                        .spec(manageFavoritesResponse);
            }
        }
    }

    @Step("PUT song to favorites")
    public void putSongToFavorites(Cookie cookie, Integer id) {
        given()
                .spec(FavoritesRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .put("/" + id)
                .then()
                .spec(manageFavoritesResponse);

    }

    @Step("DELETE song from favorites")
    public void deleteSongFromFavorites(Cookie cookie, Integer id) {
        given()
                .spec(FavoritesRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .delete("/" + id)
                .then()
                .spec(manageFavoritesResponse);

    }

    @Step("GET favorites song")
    public SongModel getFavoriteSongs(Cookie cookie) {
        return given()
                .spec(FavoritesRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .get()
                .then()
                .spec(getFavoritesResponseSuccess)
                .extract().jsonPath().getList(".", SongModel.class).get(0);
    }

    @Step("GET empty favorites")
    public String getEmptyFavorites(Cookie cookie) {
        return given()
                .cookie(String.valueOf(cookie))
                .spec(FavoritesRequest)
                .when()
                .get()
                .then()
                .spec(getFavoritesResponseSuccess)
                .extract().body().asString();
    }
}
