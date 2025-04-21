package configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties", "classpath:default.properties"})
public interface TestPropertiesConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();
}