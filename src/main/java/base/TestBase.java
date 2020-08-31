package base;

import entities.config.CommonProperties;
import entities.config.ExceptionEntity;
import entities.dto.creation.CreateTriangleDto;
import entities.dto.creation.TriangleDto;
import entities.enums.TriangleServiceExceptionNames;
import entities.mothers.TriangleExceptionMother;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.environment.EnvironmentService;
import services.rest.RestService;
import services.rest.helpers.TriangleHelper;
import utils.JsonUtils;
import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static entities.enums.ServiceNames.TRIANGLE;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class TestBase {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected String commonErrorMessage = "Actual \"%s\":\n\"%s\"\ndoesn't equal to expected:\n\"%s\"\n";

    protected ThreadLocal<TriangleHelper> triangleHelperTL = ThreadLocal.withInitial(() -> null);
    protected ThreadLocal<CreateTriangleDto> createTriangleDtoTL = ThreadLocal.withInitial(() -> null);

    protected void clearData() {
        List<TriangleDto> createdTriangleList = getTriangleHelper().getAllTriangles().getValue1();
        if (!createdTriangleList.isEmpty()) {
            createdTriangleList.parallelStream().forEach(t -> getTriangleHelper().deleteTriangle(t.getId()));
        }
    }

    @Step("Checking success triangle creation")
    protected void checkSuccessResponse(CreateTriangleDto expectedTriangle, TriangleDto actualTriangle) {
        SoftAssertions softAssertions = new SoftAssertions();

        assertThat(actualTriangle.getId())
                .withFailMessage("Actual id value: \"%s\" is not corresponding to UUIDv4 format!", actualTriangle.getId())
                .matches(Pattern.compile("^[0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$", Pattern.CASE_INSENSITIVE));

        List<String> expectedTriangleSideList = Arrays.asList(
                expectedTriangle.getInput().split(
                        Optional.ofNullable(expectedTriangle.getSeparator())
                                .orElse(CommonProperties.defaultSeparator)));
        TriangleDto convertedExpectedTriangle = TriangleDto.TriangleDtoBuilder.aTriangleDto()
                .withFirstSide(Double.parseDouble(expectedTriangleSideList.get(0)))
                .withSecondSide(Double.parseDouble(expectedTriangleSideList.get(1)))
                .withThirdSide(Double.parseDouble(expectedTriangleSideList.get(2)))
                .build();

        assertThat(actualTriangle)
                .withFailMessage(commonErrorMessage, "triangle", actualTriangle, expectedTriangle)
                .isEqualToIgnoringGivenFields(convertedExpectedTriangle, "id");

        softAssertions.assertAll();
    }

    @Step("Checking error while triangle creation")
    protected void checkErrorResponse(ValidatableResponse response, String expectedPath, TriangleServiceExceptionNames exceptionName) {
        ExceptionEntity exceptionEntity = TriangleExceptionMother.getExceptionEntity(exceptionName);

        SoftAssertions softAssertions = new SoftAssertions();

        String fieldName = "path";
        String actualValue = response.extract().jsonPath().getString(fieldName);
        softAssertions.assertThat(actualValue)
                .withFailMessage(commonErrorMessage, fieldName, actualValue, expectedPath)
                .isEqualTo(expectedPath);

        fieldName = "message";
        actualValue = response.extract().jsonPath().getString(fieldName);
        softAssertions.assertThat(actualValue)
                .withFailMessage(commonErrorMessage, fieldName, actualValue, exceptionEntity.getMessage())
                .isEqualTo(exceptionEntity.getMessage());

        fieldName = "error";
        actualValue = response.extract().jsonPath().getString(fieldName);
        softAssertions.assertThat(actualValue)
                .withFailMessage(commonErrorMessage, fieldName, actualValue, exceptionEntity.getError())
                .isEqualTo(exceptionEntity.getError());

        if (Optional.ofNullable(exceptionEntity.getException()).isPresent()) {
            fieldName = "exception";
            actualValue = response.extract().jsonPath().getString(fieldName);
            assertThat(actualValue)
                    .withFailMessage(commonErrorMessage, fieldName, actualValue, exceptionEntity.getException())
                    .isEqualTo(exceptionEntity.getException());
        }

        softAssertions.assertAll();
    }

    @Step("[Precondition] Create 10 triangles")
    protected List<TriangleDto> createFullTriangleStore() {
        return IntStream.range(0, 11).boxed().parallel().map(i -> getTriangleHelper().createTriangle(getCreateTriangleDto()).getValue1()).collect(Collectors.toList());
    }

    protected CreateTriangleDto getCreateTriangleDto() {
        if (!Optional.ofNullable(createTriangleDtoTL.get()).isPresent()) {
            createTriangleDtoTL.set(JsonUtils.getJsonObjectFromFile("requests/CreateTriangle.json", CreateTriangleDto.class));
        }
        return createTriangleDtoTL.get();
    }

    protected TriangleHelper getTriangleHelper() {
        if (!Optional.ofNullable(triangleHelperTL.get()).isPresent()) {
            triangleHelperTL.set(new TriangleHelper(
                    new RestService(
                            EnvironmentService.getInstance().getCommonConfigEntity().getServiceByAlias(TRIANGLE),
                            true)
            ));
        }
        return triangleHelperTL.get();
    }

    @Step("Validate response by JSON Schema")
    protected void validateJsonSchema(ValidatableResponse response, String schemaFilePath) {
        Allure.getLifecycle().addAttachment("JSON Schema", "application/json", ".json", Utils.readFile(schemaFilePath).getBytes());
        response.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilePath));
    }

}
