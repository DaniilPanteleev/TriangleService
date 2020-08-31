package entities.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.enums.ServiceNames;

public class ServicesEntity {

    @JsonProperty("alias")
    private ServiceNames alias;

    @JsonProperty("url")
    private String url;

    @JsonProperty("basePath")
    private String basePath;

    public ServiceNames getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

    public String getBasePath() {
        return basePath;
    }

}
