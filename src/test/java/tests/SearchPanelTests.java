package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
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
    void searchNoTabsFound() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("zxcvbnmqwertyui");

        $$("[data-list='songs']").shouldHave(size(0));
        $("[data-stub='notfound']").shouldBe(visible).
                $("div").shouldHave(text("Tabs not found"));

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

    @Test
    void filterSearchByDifficulty() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("The Strokes");
        $("#filterByDifficultySelectButton").click();
        $("#filterByDifficultySelect").find(byText("Advanced")).click();

        $("#filterByDifficultySelectButton div").shouldHave(text("Advanced"));
        $$("[data-list='songs'] span").shouldHave(size(4));
        $$("[data-list='songs'] span").last().shouldHave(attributeMatching("title", "Advanced. Tier (1|2|3)"));

    }

    @Test
    void filterSearchByCustomTuning() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("Michael Jackson");
        $("#filterByTuningSelectButton").click();
        $("[data-value='Create custom tuning']").click();
        $("#custom-tuning-filter-popup").shouldBe(visible).$("#strings-button").click();
        $("#strings-button-option-3").click();
        $("#custom-tuning-filter-popup").find(byText("Create")).click();

        $("[data-list='songs']").
                $("[data-field='name']").
                shouldHave(text("Smooth Criminal"));
        $("[data-list='songs']").
                $("[data-field='artist']").
                shouldHave(text("Michael Jackson"));



    }

    @Test
    void openSongFromSearch() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("bondage fairies - gay wedding");
        $("[data-list='songs']").$(byText("Gay Wedding")).shouldBe(visible).click();

        $("#panel-search").shouldNotBe(visible);
        $("[aria-label='title']").shouldHave(text("Gay Wedding"));
        $("[aria-label='artist']").shouldHave(text("Bondage Fairies"));

    }

    @Test
    void reopenSearchPanel() {

        open("https://songsterr.com");

        $("#panel-search input").shouldBe(empty).setValue("Frank Sinatra");
        actions().moveToElement($("#filterByInstrumentSelectButton")).
                moveByOffset(-300, 0).click().perform();
        $("#menu-search").click();

        $("#panel-search").shouldBe(visible);
        $("[data-field='artist']").shouldHave(text("Frank Sinatra"));

    }

}


