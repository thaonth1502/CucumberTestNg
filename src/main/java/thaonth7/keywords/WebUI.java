package thaonth7.keywords;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import thaonth7.constants.ConfigData;
import thaonth7.drivers.DriverManager;
import thaonth7.helpers.CaptureHelper;
import thaonth7.helpers.PropertiesHelper;
import thaonth7.helpers.SystemHelper;
import thaonth7.reports.AllureManager;
import thaonth7.utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = ConfigData.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = ConfigData.STEP_TIME ;
    private static int PAGE_LOAD_TIMEOUT = ConfigData.PAGE_LOAD_TIMEOUT;


    //Check data in Table

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Contains(int column, String value, String columnName) {

        LogUtils.info("Check data " + value + " in Table by column " + columnName);
//        ExtentTestManager.logMessage(Status.INFO, "Check data " + value + " in Table by column " + columnName);

        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Số dòng tìm thấy: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            LogUtils.info(value + " - ");
            LogUtils.info(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số:  " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Equals(int column, String value, String columnName) {

        LogUtils.info("\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
//        ExtentTestManager.logMessage(Status.INFO, "\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);

        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Số dòng tìm thấy: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.print(value + " - ");
            LogUtils.info(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().equals(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }

    // Upload file by Robot Class
    public static void uploadFileWithRobotClass(By elementFileForm, String filePath) {
        //Click để mở form upload
        WebUI.clickElement(elementFileForm);
        WebUI.sleep(2);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.sleep(1);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        WebUI.sleep(2);
    }

    /**
     * Wait for Page Load
     **/
    public static void waitForPageLoaded(){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready
        if(!jsReady){
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error){
                LogUtils.error("FAILED. Timeout waiting for page load");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load");
            }
        }
    }

    public static void waitForPageLoaded(int timeout){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready
        if(!jsReady){
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error){
                LogUtils.error("FAILED. Timeout waiting for page load");
                Assert.fail("FAILED. Timeout waiting for page load");
            }
        }
    }

    /**
     * Wait for Element
     **/

    // Wait for Element Visible
    public static void waitForElementVisible(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element Visible." + by.toString());
            Assert.fail("Timeout waiting for the element Visible." + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element Visible." + by.toString());
            Assert.fail("Timeout waiting for the element Visible." + by.toString());
        }
    }

    // Wait for Element Present
    public static void waitForElementPresent(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error){
            LogUtils.error("Element isn't present." + by.toString());
            Assert.fail("Element isn't present." + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error){
            LogUtils.error("Element isn't present." + by.toString());
            Assert.fail("Element isn't present." + by.toString());
        }
    }

    // Wait for Element to be able clicked
    public static void waitForElementToBeClickable(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click." + by.toString());
            Assert.fail("Timeout waiting for the element ready to click." + by.toString());
        }
    }

    public static void waitForElementToBeClickable(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click." + element.toString());
            Assert.fail("Timeout waiting for the element ready to click." + element.toString());
        }
    }

    public static void waitForElementToBeClickable(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to click." + by.toString());
            Assert.fail("Timeout waiting for the element ready to click." + by.toString());
        }
    }

    // Wait for Alert is Present
    public static void waitForAlertIsPresent(){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // Wait for Element To Be Selected
    public static void waitForElementToBeSelected(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to select." + by.toString());
            Assert.fail("Timeout waiting for the element ready to select." + by.toString());
        }
    }

    public static void waitForElementToBeSelected(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to select." + by.toString());
            Assert.fail("Timeout waiting for the element ready to select." + by.toString());
        }
    }

    public static void waitForElementToBeSelected(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Throwable error){
            LogUtils.error("Timeout waiting for the element ready to select." + element.toString());
            Assert.fail("Timeout waiting for the element ready to select." + element.toString());
        }
    }


    // Function check Element Exist
    public static Boolean checkElementExist(By by){
        List<WebElement> elementList = DriverManager.getDriver().findElements(by);

        if(elementList.size() > 0){
            LogUtils.info("Element " + by + "existing.");
            return true;
        } else {
            LogUtils.info("Element " + by + "NOT exist.");
            return false;
        }
    }

    /**
     * Functions basic
     */

    //Log Console
    public static void logConsole(Object message){
        LogUtils.info(message);
    }

    //Sleep
    public static void sleep(double second){
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Get Element
    public static WebElement getWebElement(By by){
        return DriverManager.getDriver().findElement(by);
    }

    //Get list Elements
    public static List<WebElement> getWebElements(By by){
        return DriverManager.getDriver().findElements(by);
    }

    //Open URL
    @Step("Open url {0}")
    public static void openURL(String url){
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("Open URL: " + url);
//        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("openURL_" + url));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("openURL_" + url));
            AllureManager.saveScreenshotPNG();
        }
    }

    public static String getCurrentURL(){
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        return currentUrl;
    }

    //Click to Element
    @Step("Click element {0}")
    public static void clickElement(By by){
        waitForElementToBeClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, int timeout){
        waitForElementToBeClickable(by, timeout);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("clickElement_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }


    //Set value for Element
    @Step("Set text {1} on element {0}")
    public static void setText(By by, String value){
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + by.toString()));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setText_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }

    @Step("Set text {1} on element {0}")
   public static void setText(WebElement element, String value){
        sleep(STEP_TIME);
        element.sendKeys(value);
        LogUtils.info("Set value: " + value + "on element " + element);
//        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + element);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setText_" + element.toString()));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setText_" + element.toString()));
            AllureManager.saveScreenshotPNG();
        }
   }

    @Step("Set key {1} on element {0}")
   public static void setKey(By by, Keys keys){
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(keys);
        LogUtils.info("Set key: " + keys.name() + "on element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Set key: " + keys.name() + " on element " + by);
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("setKey_" + by.toString()));
//            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("setKey_" + by.toString()));
            AllureManager.saveScreenshotPNG();
        }
   }

   public static void setTextAndKey(By by, String value, Keys keys){
        waitForElementPresent(by);
        getWebElement(by).sendKeys(value,keys);
        LogUtils.info("Set text: " + value + "on element " + by);
   }

   //Get value of Element
   @Step("Get text of element {0}")
    public static String getElementText(By by){
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
//       ExtentTestManager.logMessage(Status.PASS, "Get text: " + text);
       AllureManager.saveTextLog("Get text: " + text);
        return text;
    }

    //Get value of Element following element's attribute
    public static String getElementAttribute(By by, String attributeName){
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("Attribute value: " + text);
        return text;
    }

    /**
     * Assert and Verify
     */

    public static boolean verifyEquals(Object actual, Object expected){
        waitForPageLoaded();
        LogUtils.info("Verify equals: " + actual + "\uD83D\uDFF0" + expected);
//        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message){
        waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + "\uD83D\uDFF0" + expected);
//        ExtentTestManager.logMessage(Status.PASS, "Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected){
        waitForPageLoaded();
        LogUtils.info("Verify contains: " + actual + " contains " + expected);
//        ExtentTestManager.logMessage(Status.PASS, "Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String massage){
        waitForPageLoaded();
        LogUtils.info("Assert contains: " + actual + " contains " + expected);
//        ExtentTestManager.logMessage(Status.PASS, "Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, massage);
    }

    public static void assertTrue(boolean condition, String message){
        waitForPageLoaded();
        LogUtils.info("Assert true: " + condition);
//        ExtentTestManager.logMessage(Status.PASS, "Assert True: " + condition);
        if (!condition){
            Assert.assertTrue(condition, message);
        }
    }

    public static void assertFail(boolean condition, String message){
        waitForPageLoaded();
        LogUtils.info("Assert fail: " + condition);
//        ExtentTestManager.logMessage(Status.PASS, "Assert Fail: " + condition);
        if (condition){
            Assert.assertFalse(condition, message);
        }
    }
}
