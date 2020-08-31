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
public class CalculatingPerimeterTest extends CalculationTestBase {

    @Test(dataProvider = "prepDataForCalculation")
    @Story("Check calculating perimeter")
    @TestCaseId("TRGL-3")
    public void calculatePerimeterTest(TriangleDto triangleDto) {
        ValidatableResponse response = getTriangleHelper().getTrianglePerimeter(triangleDto.getId());

        validateJsonSchema(response, "schemas/CalculateSuccess.json");

        checkSuccessCalculation(response, triangleDto, () -> TriangleUtils.calculatePerimeter(triangleDto));
    }

    @Test
    @Story("Check calculating perimeter")
    @TestCaseId("TRGL-4")
    public void calculatePerimeterForNotExistedTriangleTest() {
        String uuid = UUID.randomUUID().toString();
        ValidatableResponse response = getTriangleHelper().getTrianglePerimeter(404, uuid);

        validateJsonSchema(response, "schemas/CommonError.json");

        checkErrorResponse(response, getTriangleHelper().getFullPath(TriangleEndpoints.getTrianglePerimeter(uuid)), NOT_FOUND);
    }

}
