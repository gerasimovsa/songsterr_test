package app.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

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

    public void openNewTabPanel() {
        $("#menu-submit").shouldBe(visible).click();
    }

    public void closeCurrentPanel() {
        actions()
                .moveByOffset( 500  , 500)
                .click()
                .perform();
    }

    public void verifyAccountNameIsDisplayed(String accountName) {
        $("#menu-account").lastChild().shouldHave(text(accountName));
    }

    public void verifyNoAccountNameIsDisplayed() {
        $("#menu-signin").lastChild().shouldHave(text("Sign In"));
    }

}
