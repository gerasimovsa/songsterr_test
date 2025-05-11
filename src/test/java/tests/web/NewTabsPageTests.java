package tests.web;

import models.SongModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class NewTabsPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    @Tag("AuthRequired")
    void createNewBlankSong() {

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
    void createNewSongFromGPFile() {

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
    void cannotCreateSongWithBlankTitle() {

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

