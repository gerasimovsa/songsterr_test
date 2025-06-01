package app.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MyTabsPage {

    @Step("Removing first song in the Favorites list")
    public void removeFirstSongFromFavorites() {
        $("#song-options-button").click();
        $("[data-feature='remove']").click();
    }

    @Step("Opening contributions tab")
    public void openContributionsTab() {
        $("#title-contributions1").ancestor("a").click();
    }

    @Step("Opening favorites tab")
    public void openFavoritesTab() {
        $(byText("Favorites")).ancestor("a").click();
    }

    @Step("Opening playlists tab")
    public void openPlaylistsTab() {
        $("#title-playlists").ancestor("a").click();
    }

    @Step("Creating playlist with {playlistName} name")
    public void createPlaylist(String playlistName) {
        $("#create-playlist").click();
        $("#playlist-menu textarea").shouldBe(empty).setValue(playlistName).pressEnter();
    }

    @Step("Adding song to {playlistName} playlist")
    public void addSongFromFavoritesToPlaylist(String playlistName) {
        $("#song-options-button").click();
        $("#song-options-button").$(byText(playlistName)).click();
    }

    @Step("Removing the song from playlist")
    public void removeSongFromPlaylist() {
        $("#song-options-button").shouldBe(visible).click();
        $(byText("Remove from this playlist")).click();
    }

    @Step("Deleting the {playlistName} playlist")
    public void deletePlaylist(String playlistName) {
        $("#playlist-menu").$(byText(playlistName)).hover();
        $("[data-feature='remove']").shouldBe(visible).doubleClick();
    }

    @Step("Verifying favorites has {artist} - {name} song")
    public void verifyFavoritesTabHasSong(String name, String artist) {
        $("#fav-subpanel [data-field='name']").shouldHave(text(name));
        $("#fav-subpanel [data-field='artist']").shouldHave(text(artist));
    }

    @Step("Verifying favorites is empty")
    public void verifyFavoritesTabIsEmpty() {
        $("[data-stub='nofavorites']").shouldBe(visible);
    }

    @Step("Verifying playlist has {artist} - {name} song")
    public void verifyPlaylistHasSong(String artist, String title) {
        $("[data-list='favorites'] [data-field='name']").shouldHave(text(title));
        $("[data-list='favorites'] [data-field='artist']").shouldHave(text(artist));
    }

    @Step("Verifying playlist is empty")
    public void verifyPlaylistIsEmpty() {
        $("[data-stub='nofavorites']").shouldBe(visible);
    }

    @Step("Verifying playlist tab is empty")
    public void verifyPlaylistsTabIsEmpty() {
        $("[data-stub='no-playlists']").shouldBe(visible);
    }

}
