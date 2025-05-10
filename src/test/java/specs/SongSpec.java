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

public class SongSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/api/song";


    public static RequestSpecification songRequest = with()
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static ResponseSpecification songResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("artist", not(empty()))
            .expectBody("title", not(empty()))
            .expectBody("songId", not(empty()))
            .build();

    public static ResponseSpecification songNotFoundResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .expectBody("error", not(empty()))
            .expectBody("code", not(empty()))
            .build();


}

