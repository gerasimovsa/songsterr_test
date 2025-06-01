package app.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPanel {

    @Step("Entering {query} text in the Search bar")
    public void enterSearchQuery(String query) {
        $("#panel-search input").shouldBe(empty).setValue(query);
    }

    @Step("Opening search result by {text} text")
    public void openSearchResultByText(String text) {
        $$("[data-field='name']").findBy(text(text)).click();
    }

    @Step("Clearing Search bar")
    public void clearSearchBar() {
        $$("#panel-search svg").first().click();
        $("#panel-search input").shouldBe(empty);
    }

    @Step("Filtering search results by {option}")
    public void filterSearchResultsByDifficulty(String option) {
        $("#filterByDifficultySelectButton").click();
        $("#filterByDifficultySelect").find(byText(option)).click();
        $("#filterByDifficultySelectButton div").shouldHave(text("Advanced"));
    }

    @Step("Filtering by custom {option} tuning")
    public void createCustomTuningFilter(int option) {
        $("#filterByTuningSelectButton").click();
        $("[data-value='Create custom tuning']").click();
        $("#custom-tuning-filter-popup").shouldBe(visible).$("#strings-button").click();
        $("#strings-button-option-"+option).click();
        $("#custom-tuning-filter-popup").find(byText("Create")).click();
    }

    @Step("Filtering by custom {option} tuning")
    public void closeSearchPanelByClickingSidebar() {
        actions().moveToElement($("#filterByInstrumentSelect-button")).
                moveByOffset(-300, 0).click().perform();
    }

    @Step("Verifying that search results have {text} text in titles")
    public void verifySearchResultTitlesHaveText(String text) {
        $("[data-list='songs']").
                $$("[data-field='name']").
                shouldHave(containExactTextsCaseSensitive(text));
    }

    @Step("Verifying that search results have {text} text in artists")
    public void verifySearchResultArtistsHaveText(String text) {
        $("[data-list='songs']").
                $$("[data-field='artist']").
                shouldHave(containExactTextsCaseSensitive(text));
    }

    @Step("Verifying that Artist Search have {text} text in artists")
    public void verifyArtistSearchHaveText(String text) {
        $$("[data-field='artist']").
                shouldHave(containExactTextsCaseSensitive(text));
    }

    @Step("Verifying that search results have NO {text} text")
    public void verifySearchResultsHaveNoText(String text) {
        ElementsCollection titlesAndArtists = $$("[data-song=''] div>div");
        for (SelenideElement entry : titlesAndArtists) {
            entry.shouldNotHave(text(text));
        }
    }

    @Step("Verifying search results {count} count")
    public void verifySearchResultsCount(int count) {
        $$("[data-list='songs'] a").shouldHave(size(count));
    }

    @Step("Verifying that there are NO search results")
    public void verifyNoSearchResults() {
        $$("[data-list='songs'] a").shouldHave(size(0));
        $("[data-stub='notfound']").shouldBe(visible).
                $("div").shouldHave(text("Tabs not found"));
    }

    @Step("Verifying search results have {difficulty} difficulty")
    public void verifySearchResultsHaveDifficulty(String difficulty) {
        $$("[data-list='songs'] span").last().
                shouldHave(attributeMatching
                        ("title", difficulty + ". Tier (1|2|3)"));
    }

    @Step("Verifying search results panel is closed")
    public void verifySearchPanelIsClosed() {
        $("#panel-search").shouldNotBe(visible);
    }

    @Step("Verifying search results panel is opened")
    public void verifySearchPanelIsOpened() {
        $("#panel-search").shouldBe(visible);
    }

}
