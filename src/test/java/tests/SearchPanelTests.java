package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;

public class SearchPanelTests {

    @Test
    void searchByTitle() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("Wonderwall");

        $("[data-list='songs']").
                $$("[data-field='name']").
                    shouldHave(containExactTextsCaseSensitive("Wonderwall"));

    }

    @Test
    void searchByArtist() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("Radiohead");

        $("[data-list='songs']").
                $$("[data-field='artist']").
                shouldHave(containExactTextsCaseSensitive("Radiohead"));

    }

    @Test
    void clearSearch() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("FortySecondsToMars");
        $$("#panel-search svg").first().click();

        $("#panel-search input").shouldBe(empty);
        ElementsCollection titlesAndArtists = $$("[data-song=''] div>div");
        for(SelenideElement entry : titlesAndArtists) {
            entry.shouldNotHave(text("FortySecondsToMars"));
        }

    }

    /* todo
    Filter Search results
    Open song
    Song not found */

}


