package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class NewTabsPageTests extends TestBase {

    @Test
    void createNewBlankSong() {

        Configuration.timeout = 10000;

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
        $("[aria-label='title']").shouldHave(text("My Test Song"));
        $("[aria-label='artist']").shouldHave(text("Test Band"));

    }

    @Test
    void createNewSongFromGPFile() {

        Configuration.timeout = 10000;

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-submit").click();
        $("[aria-label='Upload GuitarPro']").click();
        $("[name='title']").shouldBe(empty).setValue("Song from GP File");
        $("[name='artist']").shouldBe(empty).setValue("Test GP Band");


        File gpFile = new File("src/test/resources/my_gp_file.gp3");
        $("#tabFile").uploadFile(gpFile);
        $("[aria-label='Upload']").click();


        $("#panel-submit").shouldNotBe(visible);
        $("[aria-label='title']").shouldHave(text("Song from GP File"));
        $("[aria-label='artist']").shouldHave(text("Test GP Band"));

    }

    @Test
    void cannotCreateSongWithBlankTitle() {


        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("gerasimovsa20@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("newpassword1");
        $("#signin").click();
        $("#menu-account").lastChild().shouldHave(text("regular_s"));

        $("#menu-submit").click();
        $("[name='artist']").shouldBe(empty).setValue("Metallica");
        $("[aria-label='Create blank']").click();


        $("[name='title']").
                sibling(1).shouldHave(text("Should not be empty"));

    }

}

