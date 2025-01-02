package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MyTabsPanel {

    public void verifyFavoritesTabHasSong(String name, String artist){
        $("#fav-subpanel [data-field='name']").shouldHave(text(name));
        $("#fav-subpanel [data-field='artist']").shouldHave(text(artist));
    }
}
