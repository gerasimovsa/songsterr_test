package app.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class Toolbar {

    @Step("Opening Sign In menu")
    public void openSignInMenu() {
        $("#menu-signin").shouldBe(visible).click();
    }

    @Step("Opening Account menu")
    public void openAccountMenu() {
        $("#menu-account").shouldBe(visible).click();
    }

    @Step("Opening Search panel")
    public void openSearchPanel() {
        $("#menu-search").shouldBe(visible).click();
    }

    @Step("Opening My Tabs panel")
    public void openMyTabsPanel() {
        $("#menu-favorites").shouldBe(visible).click();
    }

    @Step("Opening New Tabs panel")
    public void openNewTabPanel() {
        $("#menu-submit").shouldBe(visible).click();
    }

    @Step("Closing current panel")
    public void closeCurrentPanel() {
        actions()
                .moveByOffset(500, 500)
                .click()
                .perform();
    }

    @Step("Verifying {accountName} account name is displayed")
    public void verifyAccountNameIsDisplayed(String accountName) {
        $("#menu-account").lastChild().shouldHave(text(accountName));
    }

    @Step("Verifying NO account name is displayed")
    public void verifyNoAccountNameIsDisplayed() {
        $("#menu-signin").lastChild().shouldHave(text("Sign In"));
    }

}
