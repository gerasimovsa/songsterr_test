package app.components;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ControlsBar {

    public void toggleEditMode() {
        $("#control-editor").shouldBe(visible).click();
    }

    public void resetEdits() {
        $("#control-dots").shouldBe(visible).click();
        $("#controlinpopup-reset").shouldBe(visible).click();
    }

    public void selectInstrumentFromMixer(String instrument) {
        $("#mixer-button").shouldBe(visible).click();
        $("#mixer-items-scroller").$(byText(instrument)).click();
    }

    public void playSong() {
        $("#control-play").shouldBe(visible).click();
    }

    public void verifySongHasEditedStatus() {
        $("[aria-label='Edited']").shouldBe(visible);
    }

    public void verifySongIsNotEdited() {
        $("[aria-label='Edited']").shouldNotBe(visible);
    }

    public void verifyCurrentTabInstrument(String instrument) {
        $("#song-sfx").shouldHave(text(instrument + " Tab"));
    }

}
