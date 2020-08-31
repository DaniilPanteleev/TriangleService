package base;

import entities.config.NegativeTestDataEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import utils.JsonUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TriangleNegativeTestBase extends TestBase {

    @BeforeMethod
    public void init() {
        clearData();
    }

    @DataProvider(name = "negative")
    protected Iterator<Object> negativeTestDataProvider() {
        List<NegativeTestDataEntity> testDataEntityList = JsonUtils.getJsonArrayFromFile("data/NegativeTestData.json", NegativeTestDataEntity.class);
        return testDataEntityList.stream().map(t ->
                (Object) NegativeTestDataEntity.NegativeTestDataEntityBuilder.from(t)
                        .withTriangle(Optional.ofNullable(t.getTriangle()).orElse(getCreateTriangleDto()))
                        .withIsTriangleStoreFull(Optional.ofNullable(t.getTriangleStoreFull()).orElse(false))
                        .withIsRequestSpecExist(Optional.ofNullable(t.getRequestSpecExist()).orElse(true))
                        .build())
                .collect(Collectors.toList())
                .iterator();
    }

}
