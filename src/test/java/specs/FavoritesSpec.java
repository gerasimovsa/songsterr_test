package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.*;

public class FavoritesSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/api/favorites";


    public static RequestSpecification FavoritesRequest = with()
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static ResponseSpecification getFavoritesResponseSuccess = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(not(empty()))
            .build();

    public static ResponseSpecification manageFavoritesResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

}

