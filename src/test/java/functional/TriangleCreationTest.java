package functional;

import annotations.TestCaseId;
import base.FunctionalTestBase;
import entities.dto.creation.CreateTriangleDto;
import entities.dto.creation.TriangleDto;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.javatuples.Pair;
import org.testng.annotations.Test;

@Feature("Triangle creation")
public class TriangleCreationTest extends FunctionalTestBase {

    @Test(dataProvider = "triangleCreationProvider", description = "Base triangle creation")
    @Story("Check creation")
    @TestCaseId("TRGL-7")
    public void baseTriangleCreationTest(CreateTriangleDto createTriangleDto) {
        Pair<ValidatableResponse, TriangleDto> response = getTriangleHelper().createTriangle(createTriangleDto);

        validateJsonSchema(response.getValue0(), "schemas/CreateTriangleSuccess.json");

        checkSuccessResponse(createTriangleDto, response.getValue1());
    }

}
