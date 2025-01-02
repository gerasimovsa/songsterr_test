package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPanel {

    public void enterSearchQuery(String query) {
        $("#panel-search input").shouldBe(empty).setValue(query);
    }

    public void selectSearchResult(String text) {
        $$("[data-field='name']").findBy(exactText(text)).click();
    }

}
