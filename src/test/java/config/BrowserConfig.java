package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:browser.properties"
})
public interface BrowserConfig extends Config {


    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("base.url")
    @DefaultValue("https://songsterr.com")
    String baseUrl();

    @Key("timeout")
    @DefaultValue("15000")
    long timeout();

}