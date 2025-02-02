package pages;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.List;


public class ApiUtils {

    String favoritesEndpoint = "/api/favorites";
    String songsEndpoint = "/api/songs";

    public static Cookie getAuthCookie() {
        Configuration.headless = true;
        open("/");
        $("#menu-signin").shouldBe(visible).click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("mypass123");
        Selenide.sleep(2000);
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regularS"));
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("SongsterrT");
        closeWebDriver();
        if (cookie != null) {
            return cookie;
        } else {
            throw new RuntimeException("SongsterrT auth cookie is not extracted");
        }
    }

    public void authorizeUser(Cookie cookie) {
        open("/");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    public void putSongToFavoritesByID(Cookie cookie, String songID) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .put("https://www.songsterr.com" + favoritesEndpoint + "/" + songID)
                .then()
                .log().status()
                .statusCode(201);

    }

    public void clearFavorites(Cookie cookie) {
        Response response = given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint);
        response.then()
                .statusCode(200);
        List<Integer> ids = response.jsonPath().getList("songId");
        if (!ids.isEmpty()) {
            for (Integer songId : ids) {
                given()
                        .cookie(String.valueOf(cookie))
                        .when()
                        .delete("https://www.songsterr.com" + favoritesEndpoint + "/" + songId)
                        .then()
                        .statusCode(201);
            }
        }
    }

    public void getSongsList(Cookie cookie, String query, int size, int from) {
        given()
                .cookie(String.valueOf(cookie))
                .param("pattern", query)
                .param("size", size)
                .param("from", from)
                .when()
                .get("https://www.songsterr.com" + songsEndpoint)
                .then()
                .statusCode(200)
                .log().body();
    }

    public void verifyFavoritesHasSong(Cookie cookie, String artist, String title) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .body("artist", hasItem(artist))
                .body("title", hasItem(title));
    }

    public void verify(int index, int index) {
        given()
                .cookie(String.valueOf(cookie))
                .when()
                .get("https://www.songsterr.com" + favoritesEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .body("artist", hasItem(artist))
                .body("title", hasItem(title));
    }

}
