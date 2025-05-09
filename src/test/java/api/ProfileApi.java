package api;


import models.ProfileModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;


public class ProfileApi {

    String profileEndpoint = "/auth/profile";

    public ProfileModel getLoggedInProfile(Cookie cookie) {
        return given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .when()
                .get("https://www.songsterr.com" + profileEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ProfileModel.class);
    }

}
