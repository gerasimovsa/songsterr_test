package api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.Cookie;


import static io.restassured.RestAssured.given;


public class PlaylistsApi {

    String playlistsEndpoint = "/api/setlist";

    public String postNewPlaylistAndGetId(Cookie cookie, String playlistJSON) throws JsonProcessingException {
        String name = new ObjectMapper().readTree(playlistJSON).get("name").asText();

        return given()
                .cookie(String.valueOf(cookie))
                .contentType(ContentType.JSON)
                .body(playlistJSON)
                .log().all()
                .when()
                .post("https://www.songsterr.com" + playlistsEndpoint)
                .then()
                .log().all()
                .statusCode(201)
                .body("id", not(empty()))
                .body("name", is(name))
                .extract().path("id").toString();
    }

    public void getPlaylistByID(Cookie cookie, Integer playlistID, String playlistJSON) throws JsonProcessingException {
        String name = new ObjectMapper().readTree(playlistJSON).get("name").asText();

        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .get("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(playlistID))
                .body("name", is(name));

    }

    public void deletePlaylistByID(Cookie cookie, int playlistID) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(201);
    }

    public void getPlaylistNotFound(Cookie cookie, int playlistID) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .delete("https://www.songsterr.com" + playlistsEndpoint + "/" + playlistID)
                .then()
                .log().all()
                .statusCode(404);
    }

}
