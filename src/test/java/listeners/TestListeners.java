package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListeners implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    // Thread-safe ExtentTest
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

        try {

            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriver();
            
            if (driver !=null) {

                String path = ScreenshotUtils.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );

                extentTest.get().addScreenCaptureFromPath(path);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

