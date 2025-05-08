package app.pages;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class NewTabPage {


    public void createNewBlankTab(String title, String artist) {
        $("[name='title']").shouldBe(empty).setValue(title);
        $("[name='artist']").shouldBe(empty).setValue(artist);
        $("[aria-label='Create blank']").click();
    }

    public void createNewTabFromGuitarProTab(String title, String artist, String filepath) {
        $("[aria-label='Upload GuitarPro']").click();
        $("[name='title']").shouldBe(empty).setValue(title);
        $("[name='artist']").shouldBe(empty).setValue(artist);
        File gpFile = new File(filepath);
        $("#tabFile").uploadFile(gpFile);
        $("[aria-label='Upload']").click();
    }

    public void verifyNewTabPanelIsClosed() {
        $("#panel-submit").shouldNotBe(visible);
    }

    public void verifyBlankTitleFieldHasWarning() {
        $("[name='title']").
                sibling(1).shouldHave(text("Should not be empty"));
    }

}
