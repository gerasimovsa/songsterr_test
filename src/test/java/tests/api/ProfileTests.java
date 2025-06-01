package tests.api;

import api.AuthApi;
import api.ProfileApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import models.ProfileModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@Epic("Songsterr - API")
@Feature("Profile")
@Owner("gerasimovsa")
public class ProfileTests extends TestBase {

    ProfileApi profileApi = new ProfileApi();
    AuthApi authApi = new AuthApi();

    @Test
    @Tag("AuthRequired")
    @DisplayName("Update profile data")
    @Severity(CRITICAL)
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
    @DisplayName("Update profile data")
    @Severity(NORMAL)
    void changeProfileDataInvalidEmail() {

        ProfileModel invalidEmailProfile = ProfileModel.builder()
                .name("regularSereja")
                .email("gerasimovsa")
                .build();

        String response = authApi.postChangesWithInvalidEmail(cookie, invalidEmailProfile);

        assertThat(response).contains("error", "Please fix the errors and try again");
    }

}






