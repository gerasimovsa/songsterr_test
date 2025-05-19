package api;


import io.qameta.allure.Step;
import models.ProfileModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.ProfileSpec.profileRequest;
import static specs.ProfileSpec.profileResponse;


public class ProfileApi {

    @Step("GET logged int profile")
    public ProfileModel getLoggedInProfile(Cookie cookie) {
        return given()
                .spec(profileRequest)
                .cookie(String.valueOf(cookie))
                .when()
                .get()
                .then()
                .spec(profileResponse)
                .extract().as(ProfileModel.class);
    }

}
