package app.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {

    public void signOutUser() {
        $("#signout").shouldBe(visible).click();
    }

    public void editAndSaveUserInfo(String name, String email) {
        $("[name='name']").setValue(name);
        $("[name='email']").setValue(email);
        $("#panel-account").find(byText("Save")).click();
    }

    public void setNewPassword(String newPassword) {
        $("#panel-account").find(byText("Change password")).click();
        $("[name='password']").setValue(newPassword);
        $("[name='passwordAgain']").setValue(newPassword);
        $("#panel-changePassword").find(byText("Save")).click();
    }

    public void verifyAccountMenuOpened() {
        $("h1#top").shouldHave(text("Profile"));
    }

    public void verifyAccountMenuHasUserInfo(String name, String email) {
        $("[name='name']").shouldHave(attribute("value", name));
        $("[name='email']").shouldHave(attribute("value", email));
    }

    public void verifyPasswordChangeSuccessful() {
        $("#panel-changePassword h4").shouldHave(text("Your password has been changed."));
    }

}
