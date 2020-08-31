package functional;

import annotations.TestCaseId;
import base.FunctionalTestBase;
import entities.dto.creation.TriangleDto;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import services.rest.endpoints.TriangleEndpoints;

import java.util.List;
import java.util.UUID;

import static entities.enums.TriangleServiceExceptionNames.NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Manipulation actions")
public class DeletingTriangleTest extends FunctionalTestBase {

    @Test
    @Story("Check deleting triangle")
    @TestCaseId("TRGL-5")
    public void deleteTriangleTest() {
        getTriangleHelper().deleteTriangle(triangleDto.getId());

        List<TriangleDto> triangleList = getTriangleHelper().getAllTriangles().getValue1();
        assertThat(triangleList)
                .flatExtracting("id")
                .doesNotContain(triangleDto.getId());
    }

    @Test
    @Story("Check deleting triangle")
    @TestCaseId("TRGL-6")
    public void deleteNotExistedTriangleTest() {
        String uuid = UUID.randomUUID().toString();
        ValidatableResponse response = getTriangleHelper().getTriangle(404, uuid);

        validateJsonSchema(response, "schemas/CommonError.json");

        checkErrorResponse(response, getTriangleHelper().getFullPath(TriangleEndpoints.getTriangle(uuid)), NOT_FOUND);
    }

}
