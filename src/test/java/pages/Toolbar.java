package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    public void openSignInMenu() {
        $("#menu-signin").shouldBe(visible).click();
    }

    public void openAccountMenu() {
        $("#menu-account").shouldBe(visible).click();
    }

    public void openSearchPanel() {
        $("#menu-search").shouldBe(visible).click();
    }

    public void openMyTabsPanel() {
        $("#menu-favorites").shouldBe(visible).click();
    }

    public void closeCurrentPanel() {
        $("#sidebar-overlay").click();
    }

    public void verifyAccountNameIsDisplayed(String accountName) {
        $("#menu-account").lastChild().shouldHave(text(accountName));
    }

    public void verifyNoAccountNameIsDisplayed() {
        $("#menu-account").lastChild().shouldHave(text("Sign In"));
    }

}
