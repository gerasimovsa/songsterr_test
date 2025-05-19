package api;


import io.qameta.allure.Step;
import models.SongModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.SongSpec.*;


public class SongApi {

    @Step("GET song by ID")
    public SongModel getSongById(Cookie cookie, SongModel song) {
        return given()
                .spec(songRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .get("/" + song.getSongId())
                .then()
                .spec(songResponse)
                .extract().as(SongModel.class);
    }

    @Step("GET non-existing song by ID")
    public String getSongByIdNotFound(Cookie cookie, SongModel song) {
        return given()
                .spec(songRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .get("/" + song.getSongId())
                .then()
                .spec(songNotFoundResponse)
                .extract().body().asString();
    }

    @Step("DELETE song by ID")
    public void deleteSongById(Cookie cookie, SongModel song) {
        given()
                .spec(songRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .delete("/" + song.getSongId())
                .then()
                .statusCode(204);
    }

}
