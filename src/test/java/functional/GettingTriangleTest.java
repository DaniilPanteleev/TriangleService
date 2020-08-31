package functional;

import annotations.TestCaseId;
import base.FunctionalTestBase;
import entities.dto.creation.TriangleDto;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import services.rest.endpoints.TriangleEndpoints;
import utils.JsonUtils;

import java.util.UUID;

import static entities.enums.TriangleServiceExceptionNames.NOT_FOUND;

@Feature("Manipulation actions")
public class GettingTriangleTest extends FunctionalTestBase {

    @Test
    @Story("Check getting triangle")
    @TestCaseId("TRGL-8")
    public void getTriangleTest() {
        ValidatableResponse response = getTriangleHelper().getTriangle(triangleDto.getId());
        TriangleDto actualTriangle = JsonUtils.getJsonObject(response, TriangleDto.class, "");

        validateJsonSchema(response, "schemas/CreateTriangleSuccess.json");

        checkSuccessResponse(getCreateTriangleDto(), actualTriangle);
    }

    @Test
    @Story("Check getting triangle")
    @TestCaseId("TRGL-9")
    public void getNotExistedTriangleTest() {
        String uuid = UUID.randomUUID().toString();
        ValidatableResponse response = getTriangleHelper().getTriangle(404, uuid);

        validateJsonSchema(response, "schemas/CommonError.json");

        checkErrorResponse(response, getTriangleHelper().getFullPath(TriangleEndpoints.getTriangle(uuid)), NOT_FOUND);
    }

}
