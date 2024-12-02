package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
    void ipload() {

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

}




//todo header - create tab, upload gp, delete song
