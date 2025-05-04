package api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class AuthApi {

    String authChangeEndpoint = "/auth/change";

    public void postChangesToProfile(Cookie cookie, String payload) throws JsonProcessingException {
        String name = new ObjectMapper().readTree(payload).get("name").asText();
        String email = new ObjectMapper().readTree(payload).get("email").asText();

        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("https://www.songsterr.com" + authChangeEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is(name))
                .body("email", is(email));
    }

    public void postChangesWithInvalidEmail(Cookie cookie, String payload) {
        given()
                .cookie(String.valueOf(cookie))
                .log().all()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("https://www.songsterr.com" + authChangeEndpoint)
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Please fix the errors and try again"));
    }
}
