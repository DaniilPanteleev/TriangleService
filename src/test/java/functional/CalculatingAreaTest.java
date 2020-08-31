package functional;

import annotations.TestCaseId;
import base.CalculationTestBase;
import entities.dto.creation.TriangleDto;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import services.rest.endpoints.TriangleEndpoints;
import utils.TriangleUtils;

import java.util.UUID;

import static entities.enums.TriangleServiceExceptionNames.NOT_FOUND;

@Feature("Calculation actions")
public class CalculatingAreaTest extends CalculationTestBase {

    @Test(dataProvider = "prepDataForCalculation")
    @Story("Check calculating area")
    @TestCaseId("TRGL-1")
    public void calculateAreaTest(TriangleDto triangleDto) {
        ValidatableResponse response = getTriangleHelper().getTriangleArea(triangleDto.getId());

        validateJsonSchema(response, "schemas/CalculateSuccess.json");

        checkSuccessCalculation(response, triangleDto, () -> TriangleUtils.calculateArea(triangleDto));
    }

    @Test
    @Story("Check calculating area")
    @TestCaseId("TRGL-2")
    public void calculateAreaForNotExistedTriangleTest() {
        String uuid = UUID.randomUUID().toString();
        ValidatableResponse response = getTriangleHelper().getTriangleArea(404, uuid);

        validateJsonSchema(response, "schemas/CommonError.json");

        checkErrorResponse(response, getTriangleHelper().getFullPath(TriangleEndpoints.getTriangleArea(uuid)), NOT_FOUND);
    }

}
