package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class ControlsPanelTests extends TestBase {

    @Test
    void editSongFromNoteMenu() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("mypass123");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#control-editor").shouldBe(visible).click();
        $("[aria-controls='note-editing-menu']").click();
        $("button[aria-label='Set 1/2 duration']").shouldBe(visible).click();
        $("#control-editor").shouldBe(visible).click();


        $("[aria-label='Edited']").shouldBe(visible);

    }

    @Test
    void resetSongEdits() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("mypass123");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-search").click();
        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#control-editor").shouldBe(visible).click();
        $("[data-tab-control='bar-dots']").click();

        $("button[aria-label='Set 1/2 duration']").shouldBe(visible).click();
        $("#control-editor").shouldBe(visible).click();


        $("[aria-label='Edited']").shouldBe(visible);

    }

    @Test
    void selectTabInstrument() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#mixer-button").shouldBe(visible).click();
        $("#mixer-items-scroller").$(byText("Drums")).click();


        $("#song-sfx").shouldHave(text("Drum Tab"));

    }

    @Test
    void playSong() {

        Configuration.browserSize = "1920x1200";
        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("happy ending the strokes");
        $$("[data-field='name']").findBy(exactText("Happy Ending")).click();

        $("#control-play").shouldBe(visible).click();

        $("g[style*='visibility: visible;']").shouldNotHave(
                attribute("style", "transform: translate3d(93px, -22px, 0px); visibility: visible; opacity: 1;"),
                Duration.ofSeconds(10));

    }
}
