package api;


import io.restassured.http.ContentType;
import models.ProfileModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class AuthApi {

    String authChangeEndpoint = "/auth/change";

    public void postChangesToProfile(Cookie cookie, ProfileModel profile) {

        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .contentType(ContentType.JSON)
                .body(profile)
                .when()
                .post("https://www.songsterr.com" + authChangeEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is(profile.getName()))
                .body("email", is(profile.getEmail()));
    }

    public String postChangesWithInvalidEmail(Cookie cookie, ProfileModel profile) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .contentType(ContentType.JSON)
                .body(profile)
                .when()
                .post("https://www.songsterr.com" + authChangeEndpoint)
                .then()
                .log().all()
                .statusCode(400)
                .extract().body().asString();
    }
}
