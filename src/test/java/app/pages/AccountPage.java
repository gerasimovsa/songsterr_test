package app.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {

    @Step("Signing user out")
    public void signOutUser() {
        $("#signout").shouldBe(visible).click();
    }

    @Step("Editing user profile name to {name} and email to {email}")
    public void editAndSaveUserInfo(String name, String email) {
        $("[name='name']").setValue(name);
        $("[name='email']").setValue(email);
        $("#panel-account").find(byText("Save")).click();
    }

    @Step("Editing user account name to {name} and email to {email}")
    public void setNewPassword(String newPassword) {
        $("#panel-account").find(byText("Change password")).click();
        $("[name='password']").setValue(newPassword);
        $("[name='passwordAgain']").setValue(newPassword);
        $("#panel-changePassword").find(byText("Save")).click();
    }

    @Step("Verifying menu account is open")
    public void verifyAccountMenuOpened() {
        $("h1#top").shouldHave(text("Profile"));
    }

    @Step("Verifying account menu name is {name} and email is {email}")
    public void verifyAccountMenuHasUserInfo(String name, String email) {
        $("[name='name']").shouldHave(attribute("value", name));
        $("[name='email']").shouldHave(attribute("value", email));
    }

    @Step("Verifying password is changed")
    public void verifyPasswordChangeSuccessful() {
        $("#panel-changePassword h4").shouldHave(text("Your password has been changed."));
    }

}
