package entities.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.enums.ServiceNames;

import java.util.List;

import static java.lang.String.format;

public class CommonConfigEntity {

    @JsonProperty("services")
    private List<ServicesEntity> services;

    public ServicesEntity getServiceByAlias(ServiceNames serviceName) {
        return services.stream()
                .filter(s -> s.getAlias().equals(serviceName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(format("Service with alies %s doesn't exist in CommonConfig file!", serviceName)));
    }

}
