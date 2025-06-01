package api;


import io.qameta.allure.Step;
import models.ProfileModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.AuthSpec.*;


public class AuthApi {

    @Step("POST changes to profile")
    public void postChangesToProfile(Cookie cookie, ProfileModel profile) {

        given()
                .spec(authRequest)
                .cookie(String.valueOf(cookie))
                .body(profile)
                .when()
                .post()
                .then()
                .spec(authResponse);
    }

    @Step("POST changes to profile with invalid email")
    public String postChangesWithInvalidEmail(Cookie cookie, ProfileModel profile) {
        return given()
                .spec(authRequest)
                .cookie(String.valueOf(cookie))
                .body(profile)
                .when()
                .post()
                .then()
                .spec(authInvalidResponse)
                .extract().body().asString();
    }
}
