package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    @Test
    void registerUser(){

//        executeJavaScript("$('#showroom').remove()"); // Only in the song page
        open("https://songsterr.com");

        $("#menu-signin").click();
        $("#create_acc").click();
        $("[name='name']").shouldBe(empty).setValue("John Doe");
        $("[name='email']").shouldBe(empty).setValue("johndoe213800@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("myPass123");
        $("[name='terms']").click();
        $("#signup").click();
        $("#menu-account").click();

        $("#menu-account").lastChild().shouldHave(text("John Doe"));
        $("h1#top").shouldHave(text("Account"));
        $("[name='name']").shouldHave(text("John Doe"));
        $("[name='email']").shouldHave(text("johndoe213800@gmail.com"));

    }

    @Test
    void signInUser(){

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("johndoe213800@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("myPass123");
        $("#signin").click();
        $("#menu-account").click();

        $("#menu-account").lastChild().shouldHave(text("John Doe"));
        $("h1#top").shouldHave(text("Account"));
        $("[name='name']").shouldHave(text("John Doe"));
        $("[name='email']").shouldHave(text("johndoe213800@gmail.com"));
    }

    @Test
    void signOutUser(){

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("johndoe213800@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("myPass123");
        $("#signin").click();
        $("#menu-account").click();
        $("#signout").click();

        $("#menu-account").lastChild().shouldHave(text("Sign In"));
        $("h1#top").shouldHave(text("Sign In"));

    }

    @Test
    void editUserInfo() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").shouldBe(empty).setValue("johndoe213800@gmail.com");
        $("[name='password']").shouldBe(empty).setValue("myPass123");
        $("#signin").click();
        $("#menu-account").click();
        $("[name='name']").setValue("Joe Deer");
        $("[name='email']").setValue("joedeer213800@gmail.com");
        $("#panel-account").find(byText("Save")).click();
        $("#sidebar-overlay").click();
        $("#menu-account").click();

        $("#menu-account").lastChild().shouldHave(text("Joe Deer"));
        $("[name='name']").shouldHave(text("Joe Deer"));
        $("[name='email']").shouldHave(text("joedeer213800@gmail.com"));

    }

    @Test
    void changeUserPassword() {

        open("https://songsterr.com");

        $("#menu-signin").click();
        $("[name='email']").setValue("johndoe213800@gmail.com");
        $("[name='password']").setValue("myPass123");
        $("#signin").click();
        $("#menu-account").click();
        $("#panel-account").find(byText("Change password")).click();
        $("[name='password']").setValue("myPass321");
        $("[name='passwordAgain']").setValue("myPass321");
        $("#panel-changePassword").find(byText("Save")).click();


        $("#panel-changePassword h4").shouldHave(text("Your password has been changed."));

    }

}
