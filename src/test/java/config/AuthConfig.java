package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("auth.email")
    @DefaultValue("user@email.com")
    String authEmail();

    @Key("auth.username")
    @DefaultValue("user")
    String authUsername();

    @Key("auth.password")
    @DefaultValue("password")
    String authPassword();

}