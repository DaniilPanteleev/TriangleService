package services.rest.helpers;

import entities.config.CommonProperties;
import entities.dto.creation.CreateTriangleDto;
import entities.dto.creation.TriangleDto;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.javatuples.Pair;
import services.rest.RestService;
import services.rest.endpoints.TriangleEndpoints;

import java.util.List;

import static entities.enums.CustomHttpHeaderNames.X_USER;

public class TriangleHelper extends BaseHelper {

    public TriangleHelper(RestService restService) {
        super(restService);
    }

    public RequestSpecification getCommonRequestSpec() {
        return restService.withChangesToRqSpec(requestSpecBuilder -> {
            requestSpecBuilder.addHeader(X_USER.getHeaderName(), CommonProperties.userAuthToken);
            requestSpecBuilder.log(LogDetail.ALL);
        });
    }

    public Pair<ValidatableResponse, TriangleDto> createTriangle(CreateTriangleDto body) {
        ValidatableResponse response = handleStatusCodeValidation(() -> createTriangle(body, getCommonRequestSpec()));
        return Pair.with(response, response.extract().jsonPath().getObject("", TriangleDto.class));
    }

    public ValidatableResponse createTriangle(Integer statusCode, CreateTriangleDto body, RequestSpecification requestSpecification) {
        return handleStatusCodeValidation(() -> createTriangle(body, requestSpecification), statusCode);
    }

    public ValidatableResponse createTriangle(CreateTriangleDto body, RequestSpecification requestSpecification) {
        return restService.doRequestWithBody(body.toJsonWithoutNull(), TriangleEndpoints.postTriangle(), requestSpecification);
    }

    public ValidatableResponse deleteTriangle(String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.deleteTriangle(id), getCommonRequestSpec()));
    }

    public Pair<ValidatableResponse, List<TriangleDto>> getAllTriangles() {
        ValidatableResponse response = handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getAllTriangles(), getCommonRequestSpec()));
        return Pair.with(response, response.extract().jsonPath().getList("", TriangleDto.class));
    }

    public ValidatableResponse getTrianglePerimeter(String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTrianglePerimeter(id), getCommonRequestSpec()));
    }

    public ValidatableResponse getTrianglePerimeter(Integer statusCode, String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTrianglePerimeter(id), getCommonRequestSpec()), statusCode);
    }

    public ValidatableResponse getTriangleArea(String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTriangleArea(id), getCommonRequestSpec()));
    }

    public ValidatableResponse getTriangleArea(Integer statusCode, String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTriangleArea(id), getCommonRequestSpec()), statusCode);
    }

    public ValidatableResponse getTriangle(String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTriangle(id), getCommonRequestSpec()));
    }

    public ValidatableResponse getTriangle(Integer statusCode, String id) {
        return handleStatusCodeValidation(() -> restService.doRequestWithoutBody(TriangleEndpoints.getTriangle(id), getCommonRequestSpec()), statusCode);
    }


}
