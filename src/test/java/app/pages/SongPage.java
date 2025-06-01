package app.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SongPage {

    @Step("Adding current song to favorites")
    public void addCurrentSongToFavorites() {
        $("#favorite-toggle").shouldBe(visible).click();
    }

    @Step("Deleting {artist} - {title} song from contributions")
    public void deleteContributedSong(String title, String artist) {
        $("#not-published").shouldBe(visible);
        $("#song-delete-icon").shouldBe(visible).click();
        switchTo().alert().sendKeys(artist + ", " + title);
        confirm();
    }

    @Step("Verifying song is deleted")
    public void verifySongIsDeleted() {
        $("#apptab span").shouldHave(text("The tab was successfully deleted!"));
    }

    @Step("Opening artist of current song")
    public void openArtistOfCurrentSong() {
        $("a[aria-label='artist']").shouldBe(visible).click();
    }

    @Step("Opening artist of current song")
    public void openRevisionsOfCurrentSong() {
        $("#revisions-toggle-tab").shouldBe(visible).click();
    }

    @Step("Selecting song bar at {row} with {offset} offset")
    public void selectSongBarAtRow(int rowNumber, int horizontalOffset) {
        SelenideElement measure = $$("#tablature svg").get(rowNumber);
        actions().moveToElement(measure).moveByOffset(horizontalOffset, 0).click().perform();
    }

    @Step("Marking current bar as learned")
    public void markCurrentBarAsLearned() {
        $("[aria-label='Open bar menu']").click();
        $("[data-action-code='markBarLearned']").click();
    }

    @Step("Enabling Edit Mode from the Bar menu")
    public void enableEditModeFromBarMenu() {
        $("[aria-label='Open bar menu']").click();
        $("[aria-label='Edit bar']").click();
    }

    @Step("Enabling Edit Mode from the Bar menu")
    public void selectOptionInNoteEditingMenu(String option) {
        $("[aria-controls='note-editing-menu']").click();
        $("button[aria-label='" + option + "']").shouldBe(visible).click();
    }

    @Step("Verifying that song page has {artist} - {title} title")
    public void verifySongPageHasHeaderTitle(String title, String artist) {
        $("[aria-label='title']").shouldHave(text(title));
        $("[aria-label='artist']").shouldHave(text(artist));
    }

    @Step("Verifying that song page has {artist} - {title} title")
    public void verifyRevisionsOpened() {
        $("#diff").shouldBe(visible);
        $("#revisions-list").shouldBe(visible);
    }

    @Step("Verifying sign up warning is displayed")
    public void verifySignUpWarningIsDisplayed() {
        $("#favorite-popup").shouldBe(visible);
        $("#favorite-popup div").shouldHave(text("Please sign up for free to favorite this tab"));
    }

    @Step("Verifying cursor has {x}-{y}-{z} transform")
    public void verifyCursorHasTransform(int x, int y, int z) {
        $("g[style*='visibility: visible;']")
                .shouldNotHave(attribute("style",
                        "transform: translate3d(" + x + "px, " + y + "px, " + z + "px" +
                                "); visibility: visible; opacity: 1;"));
    }

    @Step("Verifying cursor is at {x}-{y}-{z} location")
    public void verifyCursorIsNotOnDefaultPosition(int x, int y, int z) {
        $("g[style*='visibility: visible;']")
                .shouldNotHave(attribute(
                        "style",
                        "transform: translate3d(" + x + "px, " + y + "px, " + z + "px" + ");" +
                                " visibility: visible; opacity: 1;"), Duration.ofSeconds(10));
    }

    @Step("Verifying that bar number {barNumber} is marked as learned")
    public void verifyBarMarkedAsLearned(int barNumber) {
        $("#add-" + barNumber).shouldBe(visible);
        $("#add-" + barNumber).shouldHave(text(String.valueOf(barNumber)));
    }

    @Step("Verifying that Bar Edit Mode is enabled")
    public void verifyBarEditModeIsEnabled() {
        $("[aria-controls='note-editing-menu']").shouldBe(visible);
        $("#control-editor").shouldHave(attribute("aria-pressed", "true"));
    }

}
