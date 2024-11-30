package tests;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SongPageTests {

    @Test
    void openArtistSearch() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        $("[aria-label='artist']").shouldBe(visible).click();

        $$("[data-field='artist']").should(containExactTextsCaseSensitive("Aphex Twin"));

    }

    @Test
    void openRevisionTab() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        $("#revisions-toggle-tab").shouldBe(visible).click();

        $("#diff").shouldBe(visible);
        $("#revisions-list").shouldBe(visible);

    }

    @Test
    void addSongToFavoritesWhenNotLoggedIn() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        $("#favorite-toggle").shouldBe(visible).click();

        $("#favorite-popup").shouldBe(visible);
        $("#favorite-popup div").shouldHave(text("Please sign up for free to favorite this tab"));

    }

    @Test
    void openFavoritesWhenNotLoggedIn() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        $("#menu-favorites").click();

        $("#panel-favorites").shouldBe(visible);
        $("#panel-favorites section div").shouldHave(text("Please sign up for free to create favorites"));

    }

    @Test
    void openSubmitMenuWhenNotLoggedIn() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        $("#menu-favorites").click();

        $("#panel-favorites").shouldBe(visible);
        $("#panel-favorites section div").shouldHave(text("Please sign up for free to create favorites"));

    }

}

//data-testid="Cursor"
//[data-notes-measure="15"]