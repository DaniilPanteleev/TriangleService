package base;

import entities.dto.creation.CreateTriangleDto;
import entities.dto.creation.TriangleDto;
import io.qameta.allure.Step;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utils.JsonUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationTestBase extends TestBase {

    @BeforeClass
    public void init() {
        clearData();
    }

    @Step("Check perimeter calculation result")
    protected void checkSuccessCalculation(ValidatableResponse response, TriangleDto triangle, Supplier<Double> doubleSupplier) {
        String paramName = "result";
        BigDecimal result = response.extract().jsonPath(JsonPathConfig.jsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get(paramName);
        assertThat(result.doubleValue())
                .withFailMessage(commonErrorMessage + "for triangle:\"%s\"", paramName, result, doubleSupplier.get(), triangle.toString())
                .isEqualTo(doubleSupplier.get());
    }

    @DataProvider(name = "prepDataForCalculation")
    protected Iterator<Object> prepDataForCalcDataProvider() {
        List<CreateTriangleDto> testDataEntityList = JsonUtils.getJsonArrayFromFile("data/TriangleCreationTestData.json", CreateTriangleDto.class);
        return testDataEntityList.stream()
                .map(s -> (Object) getTriangleHelper().createTriangle(s).getValue1())
                .collect(Collectors.toList())
                .iterator();
    }

}
