package api;


import models.ProfileModel;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static specs.ProfileSpec.profileRequest;
import static specs.ProfileSpec.profileResponse;


public class ProfileApi {

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
