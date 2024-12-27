package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SignInMenu {

    public void openSignUpMenu() {
        $("#create_acc").shouldBe(visible).click();
    }

    public void fillRegistrationUserData(String name, String email, String password) {
        $("[name='name']").shouldBe(empty).setValue(name);
        $("[name='email']").shouldBe(empty).setValue(email);
        $("[name='password']").shouldBe(empty).setValue(password);
    }

    public void submitSignUp() {
        $("[name='terms']").click();
        $("#signup").click();
    }

    public void fillSignInUserData(String email, String password) {
        $("[name='email']").shouldBe(empty).setValue(email);
        $("[name='password']").shouldBe(empty).setValue(password);
    }

    public void submitSignIn() {
        $("#signin").click();
    }

    public void verifyUserLoggedOut() {
        $("#menu-account").lastChild().shouldHave(text("Sign In"));
        $("h1#top").shouldHave(text("Sign In"));
    }

}
