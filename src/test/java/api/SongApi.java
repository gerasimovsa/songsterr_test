package api;


import models.SongModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.SongSpec.*;


public class SongApi {

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
