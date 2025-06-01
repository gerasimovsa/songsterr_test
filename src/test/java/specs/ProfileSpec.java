package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class ProfileSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/auth/profile";


    public static RequestSpecification profileRequest = with()
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static ResponseSpecification profileResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("name", not(empty()))
            .expectBody("email", not(empty()))
            .expectBody("profileId", not(empty()))
            .build();

}

