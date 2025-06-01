package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.SongModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Tag("Web")
@Epic("Songsterr - Web")
@Feature("New Tab page")
@Owner("gerasimovsa")
public class NewTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Crete new blank song")
    @Severity(CRITICAL)
    void createNewBlankSongTest() {

        SongModel newSong = SongModel.builder()
                .artist("Test Band")
                .title("My Test Song")
                .build();

        open("/");

        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewBlankTab(newSong.getTitle(), newSong.getArtist());

        app.newTabPage.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle(newSong.getTitle(), newSong.getArtist());

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Import song from GP file")
    @Severity(CRITICAL)
    void createNewSongFromGPFileTest() {

        SongModel newSong = SongModel.builder()
                .artist("Test GP Band")
                .title("Song from GP File")
                .build();

        open("/");

        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewTabFromGuitarProTab
                (newSong.getTitle(), newSong.getArtist(), "src/test/resources/my_gp_file.gp3");

        app.newTabPage.verifyNewTabPanelIsClosed();
        app.songPage.verifySongPageHasHeaderTitle(newSong.getTitle(), newSong.getArtist());

    }

    @Test
    @Tag("AuthRequired")
    @DisplayName("Cannot create song with blank title")
    @Severity(CRITICAL)
    void cannotCreateSongWithBlankTitleTest() {

        SongModel newSong = SongModel.builder()
                .artist("Test Band")
                .title(" ")
                .build();

        open("/");

        app.toolbar.openNewTabPanel();
        app.newTabPage.createNewBlankTab(newSong.getTitle(), newSong.getArtist());

        app.newTabPage.verifyBlankTitleFieldHasWarning();

    }

}

