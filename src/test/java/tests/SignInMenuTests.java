package tests;

import org.junit.jupiter.api.Test;
import pages.SongsterrApp;


import static com.codeborne.selenide.Selenide.*;

public class SignInMenuTests {

    SongsterrApp app = new SongsterrApp();

    @Test
    void registerUser(){

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInMenu.openSignUpMenu();
        app.signInMenu.fillRegistrationUserData("John Doe", "johndoe213800@gmail.com","myPass123");
        app.signInMenu.submitSignUp();
        app.toolbar.openAccountMenu();

        app.accountMenu.verifyAccountMenuOpened("John Doe");
        app.accountMenu.verifyAccountMenuHasUserInfo("John Doe", "johndoe213800@gmail.com");

    }

    @Test
    void signInUser(){

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInMenu.submitSignIn();
        app.toolbar.openAccountMenu();

        app.accountMenu.verifyAccountMenuOpened("John Doe");
        app.accountMenu.verifyAccountMenuHasUserInfo("John Doe", "johndoe213800@gmail.com");

    }

    @Test
    void signOutUser(){

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInMenu.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountMenu.signOutUser();

        app.signInMenu.verifyUserLoggedOut();

    }

    @Test
    void editUserInfo() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInMenu.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountMenu.editAndSaveUserInfo("Joe Deer", "joedeer213800@gmail.com");
        app.toolbar.closeCurrentMenu();
        app.toolbar.openAccountMenu();

        app.accountMenu.verifyAccountMenuOpened("Joe Deer");
        app.accountMenu.verifyAccountMenuHasUserInfo("Joe Deer", "joedeer213800@gmail.com");

    }

    @Test
    void changeUserPassword() {

        open("https://songsterr.com");

        app.toolbar.openSignInMenu();
        app.signInMenu.fillSignInUserData("johndoe213800@gmail.com", "myPass123");
        app.signInMenu.submitSignIn();
        app.toolbar.openAccountMenu();
        app.accountMenu.setNewPassword("myPass321");

        app.accountMenu.verifyPasswordChangeSuccessful();
    }

}
