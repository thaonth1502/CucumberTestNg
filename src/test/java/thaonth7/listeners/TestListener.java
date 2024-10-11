package thaonth7.listeners;

import thaonth7.helpers.CaptureHelper;
import thaonth7.helpers.PropertiesHelper;
import thaonth7.reports.AllureManager;
import thaonth7.reports.ExtentReportManager;
import thaonth7.reports.ExtentTestManager;
import thaonth7.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LogUtils.info(" ********* START TESTING " + iTestContext.getName() + " *********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("********* END TESTING " + iTestContext.getName() + " *********");
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info(" Starting test case " + getTestName(iTestResult));

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.startRecord(getTestName(iTestResult));
        }

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("Test case " + getTestName(iTestResult) + " passed.");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, getTestName(iTestResult) + " is passed.");

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")) {
            CaptureHelper.screenshot(getTestName(iTestResult));
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("Test case " + getTestName(iTestResult) + " failed.");
        LogUtils.error(iTestResult.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, getTestName(iTestResult) + " is failed.");

        //Allure Report
        //AllureManager.saveTextLog(iTestResult.getThrowable().toString());
        //AllureManager.saveTextLog(iTestResult.getName() + " is failed.");

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")) {
            CaptureHelper.screenshot(getTestName(iTestResult));
            ExtentTestManager.addScreenshot(getTestName(iTestResult));
            AllureManager.saveScreenshotPNG();
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("Test case " + getTestName(iTestResult) + " skipped.");
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, iTestResult.getThrowable().toString());

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }
}