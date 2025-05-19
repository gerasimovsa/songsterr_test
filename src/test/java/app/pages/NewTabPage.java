package app.pages;

import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class NewTabPage {


    @Step("Creating new {artist} - {title} blank song")
    public void createNewBlankTab(String title, String artist) {
        $("[name='title']").shouldBe(empty).setValue(title);
        $("[name='artist']").shouldBe(empty).setValue(artist);
        $("[aria-label='Create blank']").click();
    }

    @Step("Creating new {artist} - {title} song tab from gp file")
    public void createNewTabFromGuitarProTab(String title, String artist, String filepath) {
        $("[aria-label='Upload GuitarPro']").click();
        $("[name='title']").shouldBe(empty).setValue(title);
        $("[name='artist']").shouldBe(empty).setValue(artist);
        File gpFile = new File(filepath);
        $("#tabFile").uploadFile(gpFile);
        $("[aria-label='Upload']").click();
    }

    @Step("Verifying new tab panel is closed")
    public void verifyNewTabPanelIsClosed() {
        $("#panel-submit").shouldNotBe(visible);
    }

    @Step("Verifying new tab title cannot be empty warning is shown")
    public void verifyBlankTitleFieldHasWarning() {
        $("[name='title']").
                sibling(1).shouldHave(text("Should not be empty"));
    }

}
