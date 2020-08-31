package services.rest;

import entities.config.ServicesEntity;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.javatuples.Pair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.function.Consumer;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class RestService {

    private Boolean isLogResponse;
    private ServicesEntity service;

    public RestService(ServicesEntity service, Boolean isLogResponse) {
        this.service = service;
        this.isLogResponse = isLogResponse;
    }

    public RequestSpecification withChangesToRqSpec(Consumer<RequestSpecBuilder> requestSpecBuilderConsumer) {
        RequestSpecBuilder requestSpecBuilder = getBaseRequestSpecBuilder();
        requestSpecBuilderConsumer.accept(requestSpecBuilder);
        return requestSpecBuilder.build();
    }

    public String getBasePath() {
        return service.getBasePath();
    }

    /**
     * Default send request method with JSON body, response logging is off
     * @param body
     * @param endpoint
     * @param requestSpecification
     * @return
     */
    public ValidatableResponse doRequestWithBody(String body, Pair<Method, String> endpoint, RequestSpecification requestSpecification) {
        return doRequest(body, endpoint, Optional.ofNullable(requestSpecification).orElse(getBaseRequestSpecBuilder().build()), isLogResponse);
    }

    /**
     * Default send request method without JSON body, response logging is off
     * @param endpoint
     * @param requestSpecification
     * @return
     */
    public ValidatableResponse doRequestWithoutBody(Pair<Method, String> endpoint, RequestSpecification requestSpecification) {
        return doRequest(null, endpoint, Optional.ofNullable(requestSpecification).orElse(getBaseRequestSpecBuilder().build()), isLogResponse);
    }

    @Step("Send request to \"{endpoint}\"")
    private ValidatableResponse doRequest(String body, Pair<Method, String> endpoint, RequestSpecification requestSpecification, Boolean isLogResponse) {
        RequestSpecification fullReqSpec = given()
                .spec(requestSpecification);

        ValidatableResponse response;
        switch (endpoint.getValue0()) {
            case GET:
                response = fullReqSpec
                        .get(endpoint.getValue1())
                        .then();
                break;
            case DELETE:
                response = fullReqSpec
                        .delete(endpoint.getValue1())
                        .then();
                break;
            case POST:
                response = fullReqSpec
                        .body(body)
                        .post(endpoint.getValue1())
                        .then();
                break;
            default:
                throw new RuntimeException(format("Method %s doesn't have implementation!", endpoint.getValue0()));
        }

        logResponse(response, isLogResponse);
        return response;
    }

    private void logResponse(ValidatableResponse response, Boolean isLogResponse) {
        if (isLogResponse) response.log().all();
    }

    private RequestSpecBuilder initBaseRequestSpec() {
        URI serviceUri;
        try {
            serviceUri = new URI(service.getUrl());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Exception in base URI creation");
        }

        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(serviceUri)
                .setBasePath(service.getBasePath())
                .setContentType(ContentType.JSON);
    }

    private RequestSpecBuilder getBaseRequestSpecBuilder() {
        return initBaseRequestSpec();
    }

}
