package Utils;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    // Utility method to get WebDriver instance from test class or its superclass
//    private WebDriver getDriver(ITestResult result) {
//        Object testInstance = result.getInstance();
//        Class<?> clazz = testInstance.getClass();
//        while (clazz != null) {
//            try {
//                Field field = clazz.getDeclaredField("driver");
//                field.setAccessible(true); // Handle private/protected fields
//                Object driverObj = field.get(testInstance);
//                if (driverObj instanceof WebDriver) {
//                    logger.debug("WebDriver found in class: {}", clazz.getName());
//                    return (WebDriver) driverObj;
//                }
//            } catch (NoSuchFieldException e) {
//                // Field not found in this class, try superclass
//                logger.debug("Driver field not found in {}, checking superclass", clazz.getName());
//                clazz = clazz.getSuperclass();
//            } catch (IllegalAccessException e) {
//                logger.error("Failed to access WebDriver field in class {}: {}", clazz.getName(), e.getMessage(), e);
//                return null;
//            }
//        }
//        logger.error("WebDriver field not found in class hierarchy for {}", testInstance.getClass().getName());
//        return null;
//    }




    // Attach screenshot to Allure report
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] ScreenshotAsBytes(String name, WebDriver driver) {
        logger.debug("Attaching screenshot to Allure for test: {}", name);
        return ScreenshotUtil.ScreenshotAsBytes(driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("TestListener: onTestFailure triggered for {}", result.getMethod().getMethodName());
        // WebDriver driver = getDriver(result);
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String methodName = result.getMethod().getMethodName();


            logger.debug("WebDriver found, capturing screenshot for test: {}", methodName);

            // Take screenshot and save to file (optional, for local debugging)
            try {
                File screenshotFile = ScreenshotUtil.takeScreenshot(driver, methodName);
                logger.info("Screenshot saved to: {}", screenshotFile.getAbsolutePath());
            } catch (IOException e) {
                logger.error("Failed to save screenshot for test {}: {}", methodName, e.getMessage(), e);
            }

            // Attach screenshot to Allure
            try {
                ScreenshotAsBytes(methodName, driver);
            } catch (Exception e) {
                logger.error("Failed to attach screenshot to Allure for test {}: {}", methodName, e.getMessage(), e);
            }
        } else {
            logger.warn("WebDriver is null, cannot capture screenshot for test: {}", result.getMethod().getMethodName());
        }
    }

    // Other ITestListener methods (optional, implement as needed)
    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}