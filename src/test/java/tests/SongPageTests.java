package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SongPageTests extends TestBase {

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

    @Test
    void moveCursorToSongBar() {

        Configuration.browserSize = "1920x1200";
        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        SelenideElement measure = $$("#tablature svg").get(2);
        actions().moveToElement(measure).moveByOffset(600, 0).click().perform();

        $("g[style*='visibility: visible;']").
                shouldNotHave(attribute("style", "transform: translate3d(1337px, -22px, 0px); visibility: visible; opacity: 1;"));
    }

    @Test
    void markSongBarAsLearned() {

        Configuration.browserSize = "1920x1200";
        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        SelenideElement measure = $$("#tablature svg").get(2);
        actions().moveToElement(measure).moveByOffset(600, 0).click().perform();
        $("[aria-label='Open bar menu']").click();
        $("[data-action-code='markBarLearned']").click();


        $("#add-15").shouldBe(visible);
        $("#add-15").shouldHave(text("15"));
    }

    @Test
        void editSongBarFromTablist() {

        Configuration.browserSize = "1920x1200";
        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("aphex twin - rhubarb");
        $("[data-list='songs']").$(byText("Rhubarb")).click();
        SelenideElement measure = $$("#tablature svg").get(2);
        actions().moveToElement(measure).moveByOffset(600, 0).click().perform();
        $("[aria-label='Open bar menu']").click();
        $("[aria-label='Edit bar']").click();


        $("[aria-controls='note-editing-menu']").shouldBe(visible);
        $("#control-editor").shouldHave(attribute("aria-pressed", "true"));
    }

}
