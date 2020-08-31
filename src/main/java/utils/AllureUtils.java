package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.TestResult;

import java.util.function.Consumer;

public final class AllureUtils {

    private AllureUtils() {
    }

    public static void updateTestCase(Consumer<TestResult> resultConsumer) {
        Allure.getLifecycle().updateTestCase(resultConsumer);
    }

    public static void addTestCaseId(String value) {
        AllureUtils.updateTestCase(t -> t.getParameters().add(new Parameter().setName("TestCaseId").setValue(value)));
    }

}
