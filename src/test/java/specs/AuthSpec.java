package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class AuthSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/auth/change";


    public static RequestSpecification authRequest = with()
            .contentType(ContentType.JSON)
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static ResponseSpecification authResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("name", not(empty()))
            .expectBody("email", not(empty()))
            .build();

    public static ResponseSpecification authInvalidResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .expectBody("error", not(empty()))
            .expectBody("code", not(empty()))
            .build();

}

