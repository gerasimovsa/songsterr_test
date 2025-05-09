package tests.api;

import api.AuthApi;
import api.ProfileApi;
import models.ProfileModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static org.assertj.core.api.Assertions.assertThat;


public class ProfileTests extends TestBase {

    ProfileApi profileApi = new ProfileApi();
    AuthApi authApi = new AuthApi();

    @Test
    @Tag("AuthRequired")
    void changeProfileData() {

        ProfileModel expectedProfile = ProfileModel.builder()
                .name("regularSereja")
                .email("gerasimovsa201@gmail.com")
                .build();


        authApi.postChangesToProfile(cookie, expectedProfile);
        ProfileModel responseProfile = profileApi.getLoggedInProfile(cookie);

        assertThat(expectedProfile).isEqualTo(responseProfile);

    }

    @Test
    @Tag("AuthRequired")
    void changeProfileDataInvalidEmail() {

        ProfileModel invalidEmailProfile = ProfileModel.builder()
                .name("regularSereja")
                .email("gerasimovsa")
                .build();

        String response = authApi.postChangesWithInvalidEmail(cookie, invalidEmailProfile);

        assertThat(response).contains("error", "Please fix the errors and try again");
    }

}






