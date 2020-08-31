package services.rest.helpers;

import io.restassured.response.ValidatableResponse;
import org.javatuples.Pair;
import services.rest.RestService;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static java.lang.String.format;

public abstract class BaseHelper<T> {

    protected RestService restService;

    public BaseHelper(RestService restService) {
        this.restService = restService;
    }

    public String getBasePath() {
        return restService.getBasePath();
    }

    public String getFullPath(Pair<Method, String> endpoint) {
        return format("%s%s", getBasePath(), endpoint.getValue1());
    }

    protected ValidatableResponse handleStatusCodeValidation(Supplier<ValidatableResponse> responseSupplier, Integer statusCode) {
        return responseSupplier.get().statusCode(statusCode);
    }

    /**
     * Default status code validation
     * @param responseSupplier
     * @return
     */
    protected ValidatableResponse handleStatusCodeValidation(Supplier<ValidatableResponse> responseSupplier) {
        return handleStatusCodeValidation(responseSupplier, 200);
    }

}
