package app.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    @Step("Opening sign in menu")
    public void openSignUpMenu() {
        $("#create_acc").shouldBe(visible).click();
    }

    @Step("Filling registration form with {name} name, {email} email and password")
    public void fillRegistrationUserData(String name, String email, String password) {
        $("[name='name']").shouldBe(empty).setValue(name);
        $("[name='email']").shouldBe(empty).setValue(email);
        $("[name='password']").shouldBe(empty).setValue(password);
    }

    @Step("Filling registration form with {name} name, {email} email and password")
    public void submitSignUp() {
        $("[name='terms']").click();
        $("#signup").click();
    }

    @Step("Filling sign in form with {email} email and password")
    public void fillSignInUserData(String email, String password) {
        $("[name='email']").shouldBe(empty).setValue(email);
        $("[name='password']").shouldBe(empty).setValue(password);
    }

    @Step("Submitting sign in")
    public void submitSignIn() {
        Selenide.sleep(2000);
        $("#signin").click();
    }

    @Step("Verifying that user is logged out")
    public void verifyUserLoggedOut() {
        $("#panel-signin h1").shouldHave(text("Sign In"));
    }

}
