package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MyTabsPanel {

    public void removeFirstSongFromFavorites() {
        $("#song-options-button").click();
        $("[data-feature='remove']").click();
    }

    public void openContributionsTab() {
        $("#title-contributions1").ancestor("a").click();
    }

    public void openFavoritesTab() {
        $(byText("Favorites")).ancestor("a").click();
    }

    public void openPlaylistsTab() {
        $("#title-playlists").ancestor("a").click();
    }

    public void createPlaylist(String playlistName) {
        $("#create-playlist").click();
        $("#playlist-menu textarea").shouldBe(empty).setValue(playlistName).pressEnter();
    }

    public void addSongFromFavoritesToPlaylist(String playlistName) {
        $("#song-options-button").click();
        $("#song-options-button").$(byText(playlistName)).click();
    }

    public void removeSongFromPlaylist() {
        $("#song-options-button").shouldBe(visible).click();
        $(byText("Remove from this playlist")).click();
    }

    public void deletePlaylist(String playlistName) {
        $("#playlist-menu").$(byText(playlistName)).hover();
        $("[data-feature='remove']").shouldBe(visible).doubleClick();
    }

    public void verifyFavoritesTabHasSong(String name, String artist) {
        $("#fav-subpanel [data-field='name']").shouldHave(text(name));
        $("#fav-subpanel [data-field='artist']").shouldHave(text(artist));
    }

    public void verifyFavoritesTabIsEmpty() {
        $("[data-stub='nofavorites']").shouldBe(visible);
    }

    public void verifyPlaylistHasSong(String artist, String title) {
        $("[data-list='favorites'] [data-field='name']").shouldHave(text(title));
        $("[data-list='favorites'] [data-field='artist']").shouldHave(text(artist));
    }

    public void verifyPlaylistIsEmpty() {
        $("[data-stub='nofavorites']").shouldBe(visible);
    }

    public void verifyPlaylistsTabIsEmpty() {
        $("[data-stub='no-playlists']").shouldBe(visible);
    }

}
