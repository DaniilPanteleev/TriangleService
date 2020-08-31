package services.environment;

import entities.config.CommonConfigEntity;
import utils.JsonUtils;

import java.util.Optional;

public class EnvironmentService {

    private static EnvironmentService environmentService;

    private CommonConfigEntity commonConfigEntity;

    public static EnvironmentService getInstance() {
        if (!Optional.ofNullable(environmentService).isPresent()) {
            environmentService = new EnvironmentService();
        }
        return environmentService;
    }

    public CommonConfigEntity getCommonConfigEntity() {
        return commonConfigEntity;
    }

    private EnvironmentService() {
        init();
    }

    private void init() {
        commonConfigEntity = JsonUtils.getJsonObjectFromFile("config/CommonConfig.json", CommonConfigEntity.class);
    }
}
