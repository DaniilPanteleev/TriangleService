package base;

import entities.dto.creation.CreateTriangleDto;
import entities.dto.creation.TriangleDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import utils.JsonUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalTestBase extends TestBase {

    protected TriangleDto triangleDto;

    @BeforeMethod
    public void init() {
        clearData();
        triangleDto = getTriangleHelper().createTriangle(getCreateTriangleDto()).getValue1();
    }

    @DataProvider(name = "triangleCreationProvider")
    public Iterator<Object> commonTriangleDtoProvider() {
        List<CreateTriangleDto> testDataEntityList = JsonUtils.getJsonArrayFromFile("data/TriangleCreationTestData.json", CreateTriangleDto.class);
        return testDataEntityList.stream().map(t ->
                (Object) t)
                .collect(Collectors.toList())
                .iterator();
    }

}
