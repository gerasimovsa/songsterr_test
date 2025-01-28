package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPanel {

    public void enterSearchQuery(String query) {
        $("#panel-search input").shouldBe(empty).setValue(query);
    }

    public void openSearchResultByText(String text) {
        $$("[data-field='name']").findBy(text(text)).click();
    }

    public void clearSearchBar() {
        $$("#panel-search svg").first().click();
        $("#panel-search input").shouldBe(empty);
    }

    public void filterSearchResultsByDifficulty(String option) {
        $("#filterByDifficultySelectButton").click();
        $("#filterByDifficultySelect").find(byText(option)).click();
        $("#filterByDifficultySelectButton div").shouldHave(text("Advanced"));
    }

    public void createCustomTuningFilter(int option) {
        $("#filterByTuningSelectButton").click();
        $("[data-value='Create custom tuning']").click();
        $("#custom-tuning-filter-popup").shouldBe(visible).$("#strings-button").click();
        $("#strings-button-option-"+option).click();
        $("#custom-tuning-filter-popup").find(byText("Create")).click();
    }

    public void closeSearchPanelByClickingSidebar() {
        actions().moveToElement($("#filterByInstrumentSelect-button")).
                moveByOffset(-300, 0).click().perform();
    }

    public void verifySearchResultTitlesHaveText(String text) {
        $("[data-list='songs']").
                $$("[data-field='name']").
                shouldHave(containExactTextsCaseSensitive(text));
    }

    public void verifySearchResultArtistsHaveText(String text) {
        $("[data-list='songs']").
                $$("[data-field='artist']").
                shouldHave(containExactTextsCaseSensitive(text));
    }

    public void verifySearchResultsHaveNoText(String text) {
        ElementsCollection titlesAndArtists = $$("[data-song=''] div>div");
        for (SelenideElement entry : titlesAndArtists) {
            entry.shouldNotHave(text(text));
        }
    }

    public void verifySearchResultsCount(int count) {
        $$("[data-list='songs'] a").shouldHave(size(count));
    }

    public void verifyNoSearchResults() {
        $$("[data-list='songs'] a").shouldHave(size(0));
        $("[data-stub='notfound']").shouldBe(visible).
                $("div").shouldHave(text("Tabs not found"));
    }

    public void verifySearchResultsHaveDifficulty(String difficulty) {
        $$("[data-list='songs'] span").last().
                shouldHave(attributeMatching
                        ("title", difficulty + ". Tier (1|2|3)"));
    }

    public void verifySearchPanelIsClosed() {
        $("#panel-search").shouldNotBe(visible);
    }

    public void verifySearchPanelIsOpened() {
        $("#panel-search").shouldBe(visible);
    }

}
