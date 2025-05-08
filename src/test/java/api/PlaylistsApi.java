package api;


import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import models.PlaylistModel;
import org.openqa.selenium.Cookie;


import static io.restassured.RestAssured.given;


public class PlaylistsApi {

    String playlistsEndpoint = "/api/setlist";

    public PlaylistModel postNewPlaylist(Cookie cookie, PlaylistModel playlist) {
        return given()
                .cookie(String.valueOf(cookie))
                .contentType(ContentType.JSON)
                .body(playlist)
                .log().all()
                .when()
                .post("https://www.songsterr.com" + playlistsEndpoint)
                .then()
                .log().all()
                .statusCode(201)
                .body("id", not(empty()))
                .extract().as(PlaylistModel.class);
    }

    public PlaylistModel getPlaylistByID(Cookie cookie, Integer playlistID) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .get("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(PlaylistModel.class);

    }

    public void deletePlaylistByID(Cookie cookie, Integer playlistID) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(201);
    }

    public String getPlaylistNotFound(Cookie cookie, Integer playlistID) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(404)
                .extract().body().asString();
    }

}
