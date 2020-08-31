package listeners;

import annotations.TestCaseId;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import java.lang.reflect.Method;

public class AllureTestIdListener implements IResultListener {

    @Override
    public void onTestStart(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestCaseId.class)) {
            Allure.getLifecycle().updateTestCase(t -> t.getParameters().add(
                    new Parameter().setName("TestCaseId").setValue(method.getAnnotation(TestCaseId.class).value())));
        }
    }

}
