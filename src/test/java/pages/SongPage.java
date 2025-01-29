package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SongPage {

    public void addCurrentSongToFavorites() {
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

    public void openArtistOfCurrentSong() {
        $("a[aria-label='artist']").shouldBe(visible).click();
    }

    public void openRevisionsOfCurrentSong() {
        $("#revisions-toggle-tab").shouldBe(visible).click();
    }

    public void selectSongBarAtRow(int rowNumber, int horizontalOffset) {
        SelenideElement measure = $$("#tablature svg").get(rowNumber);
        actions().moveToElement(measure).moveByOffset(horizontalOffset, 0).click().perform();
    }

    public void markCurrentBarAsLearned() {
        $("[aria-label='Open bar menu']").click();
        $("[data-action-code='markBarLearned']").click();
    }

    public void enableEditModeFromBarMenu() {
        $("[aria-label='Open bar menu']").click();
        $("[aria-label='Edit bar']").click();
    }

    public void selectOptionInNoteEditingMenu(String option) {
        $("[aria-controls='note-editing-menu']").click();
        $("button[aria-label='" + option + "']").shouldBe(visible).click();
    }

    public void verifySongPageHasHeaderTitle(String title, String artist) {
        $("[aria-label='title']").shouldHave(text(title));
        $("[aria-label='artist']").shouldHave(text(artist));
    }

    public void verifyRevisionsOpened() {
        $("#diff").shouldBe(visible);
        $("#revisions-list").shouldBe(visible);
    }

    public void verifySignUpWarningIsDisplayed() {
        $("#favorite-popup").shouldBe(visible);
        $("#favorite-popup div").shouldHave(text("Please sign up for free to favorite this tab"));
    }

    public void verifyCursorHasTransform(int x, int y, int z) {
        $("g[style*='visibility: visible;']")
                .shouldNotHave(attribute("style",
                        "transform: translate3d(" + x + "px, " + y + "px, " + z + "px" +
                                "); visibility: visible; opacity: 1;"));
    }

    public void verifyCursorIsNotOnDefaultPosition(int x, int y, int z) {
        $("g[style*='visibility: visible;']")
                .shouldNotHave(attribute(
                        "style",
                        "transform: translate3d(" + x + "px, " + y + "px, " + z + "px" + ");" +
                                " visibility: visible; opacity: 1;"), Duration.ofSeconds(10));
    }

    public void verifyBarMarkedAsLearned(int barNumber) {
        $("#add-" + barNumber).shouldBe(visible);
        $("#add-" + barNumber).shouldHave(text(String.valueOf(barNumber)));
    }

    public void verifyBarEditModeIsEnabled() {
        $("[aria-controls='note-editing-menu']").shouldBe(visible);
        $("#control-editor").shouldHave(attribute("aria-pressed", "true"));
    }

}
