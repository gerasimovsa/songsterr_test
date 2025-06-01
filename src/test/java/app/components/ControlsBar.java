package app.components;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ControlsBar {

    @Step("Toggling Edit Mode from the Controls panel")
    public void toggleEditMode() {
        $("#control-editor").shouldBe(visible).click();
    }

    @Step("Resetting Edit changes from the Control panel")
    public void resetEdits() {
        $("#control-dots").shouldBe(visible).click();
        $("#controlinpopup-reset").shouldBe(visible).click();
    }

    @Step("Selecting the {instrument} instrument from Controls panel")
    public void selectInstrumentFromMixer(String instrument) {
        $("#mixer-button").shouldBe(visible).click();
        $("#mixer-items-scroller").$(byText(instrument)).click();
    }

    @Step("Clicking on Play on the Controls panel")
    public void playSong() {
        $("#control-play").shouldBe(visible).click();
    }

    @Step("Verifying song is in Edit Mode")
    public void verifySongHasEditedStatus() {
        $("[aria-label='Edited']").shouldBe(visible);
    }

    @Step("Verifying song is NOT in Edit Mode")
    public void verifySongIsNotEdited() {
        $("[aria-label='Edited']").shouldNotBe(visible);
    }

    @Step("Verifying {instrument} instrument selected on Controls panel")
    public void verifyCurrentTabInstrument(String instrument) {
        $("#song-sfx").shouldHave(text(instrument + " Tab"));
    }

}
