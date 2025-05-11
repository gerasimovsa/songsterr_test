package app.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

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
        Selenide.sleep(2000);
        $("#signin").click();
    }

    public void verifyUserLoggedOut() {
        $("#panel-signin h1").shouldHave(text("Sign In"));
    }

}
