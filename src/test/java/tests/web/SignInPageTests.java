package tests.web;

import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;


import static com.codeborne.selenide.Selenide.*;

public class SignInPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void registerUser() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInPage.openSignUpMenu();
        app.signInPage.fillRegistrationUserData("John Doe", "johndoe213800@gmail.com", "myPass123");
        app.signInPage.submitSignUp();
        app.toolbar.openAccountMenu();

        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed("John Doe");
        app.accountPage.verifyAccountMenuHasUserInfo("John Doe", "johndoe213800@gmail.com");

    }

    @Test
    void signInUser() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInPage.submitSignIn();
        app.toolbar.openAccountMenu();

        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed("John Doe");
        app.accountPage.verifyAccountMenuHasUserInfo("John Doe", "johndoe213800@gmail.com");

    }

    @Test
    void signOutUser() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInPage.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountPage.signOutUser();

        app.toolbar.verifyNoAccountNameIsDisplayed();
        app.signInPage.verifyUserLoggedOut();

    }

    @Test
    void editUserInfo() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInPage.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountPage.editAndSaveUserInfo("Joe Deer", "joedeer213800@gmail.com");
        app.toolbar.closeCurrentPanel();
        app.toolbar.openAccountMenu();

        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed("Joe Deer");
        app.accountPage.verifyAccountMenuHasUserInfo("Joe Deer", "joedeer213800@gmail.com");

    }

    @Test
    void changeUserPassword() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInPage.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountPage.setNewPassword("myPass321");

        app.accountPage.verifyPasswordChangeSuccessful();
    }

}
