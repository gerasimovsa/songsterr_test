package tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyTabsPageTests {

    @Test
    void addSongToFavorites() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#favorite-toggle").shouldBe(visible).click();
        $("#menu-favorites").click();

        $("#fav-subpanel [data-field='name']").shouldHave(text("Happy Ending"));
        $("#fav-subpanel [data-field='artist']").shouldHave(text("The Strokes"));

    }

    @Test
    void removeSongFromFavorites() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#favorite-toggle").shouldBe(visible).click();
        $("#menu-favorites").click();
        $("#song-options-button").click();
        $("[data-feature='remove']").click();

        $("[data-stub='nofavorites']").shouldBe(visible);

    }

    @Test
    void removeSongFromContributions() {

        Configuration.timeout = 12000;

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));


        $("#menu-submit").click();
        $("[name='title']").shouldBe(empty).setValue("My Test Song");
        $("[name='artist']").shouldBe(empty).setValue("Test Band");
        $("[aria-label='Create blank']").click();
        $("#panel-submit").shouldNotBe(visible);

        $("#menu-favorites").click();
        $("#title-contributions1").ancestor("a").click();
        $$("[data-field='name']").findBy(text("My Test Song")).click();
        $("#song-delete-icon").shouldBe(visible).click();
        switchTo().alert().sendKeys("test band, My Test Song");
        confirm();


        $("#apptab span").shouldHave(text("The tab was successfully deleted!"));

    }

    @Test
    void addSongToPlaylist() {

        Configuration.timeout = 10000;

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#favorite-toggle").shouldBe(visible).click();
        $("#menu-favorites").click();

        $("#title-playlists").ancestor("a").click();
        $("#create-playlist").click();
        $("#playlist-menu textarea").shouldBe(empty).setValue("MyTestPlaylist").pressEnter();

        $(byText("Favorites")).ancestor("a").click();

        $("#song-options-button").click();
        $("#song-options-button").$(byText("MyTestPlaylist")).click();

        $("#title-playlists").ancestor("a").click();

        $("[data-list='favorites'] [data-field='name']").shouldHave(text("Happy Ending"));
        $("[data-list='favorites'] [data-field='artist']").shouldHave(text("The Strokes"));

    }

    @Test
    void removeSongFromPlaylist() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#favorite-toggle").shouldBe(visible).click();
        $("#menu-favorites").click();

        $("#title-playlists").ancestor("a").click();
        $("#create-playlist").click();
        $("#playlist-menu textarea").shouldBe(empty).setValue("MyTestPlaylist").pressEnter();

        $(byText("Favorites")).ancestor("a").click();

        $("#song-options-button").click();
        $("#song-options-button").$(byText("MyTestPlaylist")).click();

        $("#title-playlists").ancestor("a").click();

        $("#song-options-button").shouldBe(visible).click();
        $(byText("Remove from this playlist")).click();

        $("[data-stub='nofavorites']").shouldBe(visible);

    }

    @Test
    void deletePlaylist() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-favorites").click();

        $("#title-playlists").ancestor("a").click();
        $("#create-playlist").click();
        $("#playlist-menu textarea").shouldBe(empty).setValue("MyTestPlaylist").pressEnter();

        $("#playlist-menu").$(byText("MyTestPlaylist")).hover();
        $("[data-feature='remove']").shouldBe(visible).doubleClick();

        $("[data-stub='no-playlists']").shouldBe(visible);

    }

}
