package api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class ProfileApi {

    String profileEndpoint = "/auth/profile";

    public void getLoggedInProfile(Cookie cookie, String profileJSON) throws JsonProcessingException {
        String name = new ObjectMapper().readTree(profileJSON).get("name").asText();
        String email = new ObjectMapper().readTree(profileJSON).get("email").asText();

        given()
            .cookie(String.valueOf(cookie))
            .log().all()
            .when()
            .get("https://www.songsterr.com" + profileEndpoint)
            .then()
            .log().all()
            .statusCode(200)
            .body("name", is(name))
            .body("email", is(email));
    }

}
