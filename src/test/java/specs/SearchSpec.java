package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.*;

public class SearchSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/api/search";


    public static RequestSpecification searchRequest = with()
            .contentType(ContentType.JSON)
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static ResponseSpecification searchResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(not(empty()))
            .build();


}

