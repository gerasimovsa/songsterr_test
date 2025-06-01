package api;

import io.qameta.allure.Step;
import models.PlaylistModel;
import org.openqa.selenium.Cookie;


import static io.restassured.RestAssured.given;
import static specs.PlaylistsSpec.*;


public class PlaylistsApi {

    @Step("POST new playlist")
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

    @Step("GET playlist by ID")
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

    @Step("DELETE playlist by ID")
    public void deletePlaylistByID(Cookie cookie, Integer playlistID) {
        given()
                .spec(playlistsRequestNoBody)
                .cookie(String.valueOf(cookie))
                .when()
                .delete("/" + playlistID)
                .then()
                .spec(managePlaylistsResponse);
    }

    @Step("GET non-existing playlist")
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
