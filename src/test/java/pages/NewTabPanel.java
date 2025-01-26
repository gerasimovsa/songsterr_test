package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class NewTabPanel {


    public void createNewBlankTab(String title, String artist) {
        $("[name='title']").shouldBe(empty).setValue(title);
        $("[name='artist']").shouldBe(empty).setValue(artist);
        $("[aria-label='Create blank']").click();
    }

    public void verifyNewTabPanelIsClosed() {
        $("#panel-submit").shouldNotBe(visible);
    }

}
