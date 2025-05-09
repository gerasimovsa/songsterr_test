package api;


import models.SongModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;


public class SongApi {

    String songEndpoint = "/api/song";

    public SongModel getSongById(Cookie cookie, SongModel song) {
        return given()
            .cookie(String.valueOf(cookie))
            .log().all()
            .when()
            .get("https://www.songsterr.com" + songEndpoint + "/" + song.getSongId())
            .then()
            .log().all()
            .statusCode(200)
            .extract().as(SongModel.class);
    }

    public String getSongByIdNotFound(Cookie cookie, SongModel song) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .get("https://www.songsterr.com" + songEndpoint + "/" + song.getSongId())
                .then()
                .log().all()
                .statusCode(404)
                .extract().body().asString();
    }

    public void deleteSongById(Cookie cookie, SongModel song) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + songEndpoint + "/" + song.getSongId())
                .then()
                .log().all()
                .statusCode(204);
    }

}
