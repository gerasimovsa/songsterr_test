package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SongPage {

    public void toggleAddToFavorites() {
        $("#favorite-toggle").shouldBe(visible).click();
    }

    public void deleteContributedSong(String title, String artist) {
        $("#not-published").shouldBe(visible);
        $("#song-delete-icon").shouldBe(visible).click();
        switchTo().alert().sendKeys(artist + ", " + title);
        confirm();
    }

    public void verifySongIsDeleted() {
        $("#apptab span").shouldHave(text("The tab was successfully deleted!"));
    }

    public void verifySongPageHasHeaderTitle(String title, String artist) {
        $("[aria-label='title']").shouldHave(text(title));
        $("[aria-label='artist']").shouldHave(text(artist));
    }

}
