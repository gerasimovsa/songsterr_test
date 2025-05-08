package tests.api;

import api.AuthApi;
import api.ProfileApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;


public class ProfileTests extends TestBase {

    ProfileApi profileApi = new ProfileApi();
    AuthApi authApi = new AuthApi();

    @Test
    @Tag("AuthRequired")
    void changeProfileData() throws JsonProcessingException {

        String profileJSON = "{\"name\":\"regularSereja\",\"email\":\"gerasimovsa201@gmail.com\"}";

        authApi.postChangesToProfile(cookie, profileJSON);
        profileApi.getLoggedInProfile(cookie, profileJSON);

    }

    @Test
    @Tag("AuthRequired")
    void changeProfileDataInvalidEmail() {

        String profileJSON = "{\"name\":\"regularSereja\",\"email\":\"gerasimovsa\"}";

        authApi.postChangesWithInvalidEmail(cookie, profileJSON);

    }


}






