package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SongPage {

    public void toggleAddToFavorites() {
        $("#favorite-toggle").shouldBe(visible).click();
    }
}
