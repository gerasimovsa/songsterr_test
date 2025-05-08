package api;


import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SongApi {

    String songEndpoint = "/api/song";

    public void getSongById(Cookie cookie, int id, String artist, String title) {
        String songID = String.valueOf(id);
        given()
            .cookie(String.valueOf(cookie))
            .log().all()
            .when()
            .get("https://www.songsterr.com" + songEndpoint + "/" + songID)
            .then()
            .log().all()
            .statusCode(200)
            .body("songId", is(id))
            .body("artist", is(artist))
            .body("title", is(title));
    }

    public void getSongByIdNotFound(Cookie cookie, int id) {
        String songID = String.valueOf(id);
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .get("https://www.songsterr.com" + songEndpoint + "/" + songID)
                .then()
                .log().all()
                .statusCode(404)
                .body("error", is("Not Found"));
    }

    public void deleteSongById(Cookie cookie, int id) {
        String songID = String.valueOf(id);
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + songEndpoint + "/" + songID)
                .then()
                .log().all()
                .statusCode(204);
    }

}
