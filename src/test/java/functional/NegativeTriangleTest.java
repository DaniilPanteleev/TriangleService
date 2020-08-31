package functional;

import base.TriangleNegativeTestBase;
import entities.config.NegativeTestDataEntity;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import utils.AllureUtils;

@Feature("Negative tests")
public class NegativeTriangleTest extends TriangleNegativeTestBase {

    @Test(dataProvider = "negative")
    @Story("Negative")
    public void negativeTriangleTest(NegativeTestDataEntity testDataEntity) {
        AllureUtils.updateTestCase(t -> t.setName(testDataEntity.getTitle()));
        AllureUtils.addTestCaseId(testDataEntity.getId());
        if (testDataEntity.getTriangleStoreFull()) createFullTriangleStore();

        ValidatableResponse response = getTriangleHelper().createTriangle(testDataEntity.getStatusCode(),
                testDataEntity.getTriangle(), testDataEntity.getRequestSpecExist() ? getTriangleHelper().getCommonRequestSpec() : null);

        validateJsonSchema(response, "schemas/CommonError.json");

        checkErrorResponse(response, getTriangleHelper().getBasePath(), testDataEntity.getException());
    }

}
