package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.*;

public class PlaylistsSpec {

    final static String baseUrl = "https://www.songsterr.com";
    final static String basePath = "/api/setlist";


    public static RequestSpecification playlistsRequest = with()
            .contentType(ContentType.JSON)
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();

    public static RequestSpecification playlistsRequestNoBody = with()
            .baseUri(baseUrl)
            .basePath(basePath)
            .log().all();


    public static ResponseSpecification managePlaylistsResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody("id", not(empty()))
            .expectBody("name", not(empty()))
            .build();

    public static ResponseSpecification getPlaylistResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(not(empty()))
            .build();

    public static ResponseSpecification getPlaylistNotFoundResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .expectBody(not(empty()))
            .build();

}

