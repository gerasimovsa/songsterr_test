package api;

import models.PlaylistModel;
import org.openqa.selenium.Cookie;


import static io.restassured.RestAssured.given;
import static specs.PlaylistsSpec.*;


public class PlaylistsApi {

    public PlaylistModel postNewPlaylist(Cookie cookie, PlaylistModel playlist) {
        return given()
                .spec(playlistsRequest)
                .cookie(String.valueOf(cookie))
                .body(playlist)
                .when()
                .post()
                .then()
                .spec(managePlaylistsResponse)
                .extract().as(PlaylistModel.class);
    }

    public PlaylistModel getPlaylistByID(Cookie cookie, Integer playlistID) {
        return given()
                .spec(playlistsRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .get("/" + playlistID)
                .then()
                .spec(getPlaylistResponse)
                .extract().as(PlaylistModel.class);

    }

    public void deletePlaylistByID(Cookie cookie, Integer playlistID) {
        given()
                .spec(playlistsRequestNoBody)
                .cookie(String.valueOf(cookie))
                .when()
                .delete("/" + playlistID)
                .then()
                .spec(managePlaylistsResponse);
    }

    public String getPlaylistNotFound(Cookie cookie, Integer playlistID) {
        return given()
                .spec(playlistsRequestNoBody)
                .cookie(String.valueOf(cookie))
                .when()
                .delete("/" + playlistID)
                .then()
                .spec(getPlaylistNotFoundResponse)
                .extract().body().asString();
    }

}
