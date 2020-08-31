package entities.config;

public final class CommonProperties {

    private CommonProperties() {
    }

    public static final String userAuthToken = System.getProperty("token");

    public static final String defaultSeparator = ";";

}
