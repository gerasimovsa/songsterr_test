package tests.web;

import models.ProfileModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import app.SongsterrApp;
import tests.TestBase;


import static com.codeborne.selenide.Selenide.*;

public class SignInPageTests extends TestBase {

    SongsterrApp app = new SongsterrApp();

    @Test
    void registerUser() {

        ProfileModel newProfile = ProfileModel.builder()
                .name("John Doe")
                .email("johndoe213@gmail.com")
                .password("myPass123")
                .build();

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.openSignUpMenu();
        app.signInPage.fillRegistrationUserData(
                newProfile.getName(),
                newProfile.getEmail(),
                newProfile.getPassword()
        );
        app.signInPage.submitSignUp();

        app.toolbar.openAccountMenu();
        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed(newProfile.getName());
        app.accountPage.verifyAccountMenuHasUserInfo(newProfile.getName(), newProfile.getEmail());

    }

    @Test
    void signInUser() {

        ProfileModel newProfile = ProfileModel.builder()
                .name("regularS")
                .email("gerasimovsa20@gmail.com")
                .password("mypass123")
                .build();

        open("/");

        app.toolbar.openSignInMenu();
        app.signInPage.fillSignInUserData(newProfile.getEmail(), newProfile.getPassword());
        app.signInPage.submitSignIn();
        app.toolbar.openAccountMenu();

        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed(newProfile.getName());
        app.accountPage.verifyAccountMenuHasUserInfo(newProfile.getName(), newProfile.getEmail());

    }

    @Test
    @Tag("AuthRequired")
    void signOutUser() {


        app.toolbar.openAccountMenu();
        app.accountPage.signOutUser();

        app.toolbar.verifyNoAccountNameIsDisplayed();
        app.signInPage.verifyUserLoggedOut();

    }

    @Test
    @Tag("AuthRequired")
    void editUserInfo() {

        ProfileModel newProfile = ProfileModel.builder()
                .name("TestName")
                .email("test_email@gmail.com")
                .build();

        app.toolbar.openAccountMenu();
        app.accountPage.editAndSaveUserInfo(newProfile.getName(), newProfile.getEmail());
        app.toolbar.closeCurrentPanel();
        app.toolbar.openAccountMenu();

        app.accountPage.verifyAccountMenuOpened();
        app.toolbar.verifyAccountNameIsDisplayed(newProfile.getName());
        app.accountPage.verifyAccountMenuHasUserInfo(newProfile.getName(), newProfile.getEmail());

    }

    @Test
    @Tag("AuthRequired")
    void changeUserPassword() {

        ProfileModel newProfile = ProfileModel.builder()
                .password("myPass321")
                .build();

        app.toolbar.openAccountMenu();
        app.accountPage.setNewPassword(newProfile.getPassword());

        app.accountPage.verifyPasswordChangeSuccessful();
    }

}
