package allure;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureExtension implements AfterTestExecutionCallback {
    AllureUtil allureUtil = new AllureUtil();

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) allureUtil.captureScreenshotOnTestFailure();
    }
}
