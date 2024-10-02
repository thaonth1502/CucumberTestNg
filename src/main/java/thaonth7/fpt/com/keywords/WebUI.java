package thaonth7.fpt.com.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import thaonth7.fpt.com.constants.ConfigData;
import thaonth7.fpt.com.drivers.DriverManager;

import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = ConfigData.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = ConfigData.STEP_TIME ;
    private static int PAGE_LOAD_TIMEOUT = ConfigData.PAGE_LOAD_TIMEOUT;

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
            System.out.println("Javascript is NOT Ready.");
            //Wait for javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error){
                System.out.println("FAILED. Timeout waiting for page load");
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
            System.out.println("Javascript is NOT Ready.");
            //Wait for javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error){
                System.out.println("FAILED. Timeout waiting for page load");
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
            System.out.println("Timeout waiting for the element Visible." + by.toString());
            Assert.fail("Timeout waiting for the element Visible." + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element Visible." + by.toString());
            Assert.fail("Timeout waiting for the element Visible." + by.toString());
        }
    }

    // Wait for Element Present
    public static void waitForElementPresent(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error){
            System.out.println("Element isn't present." + by.toString());
            Assert.fail("Element isn't present." + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error){
            System.out.println("Element isn't present." + by.toString());
            Assert.fail("Element isn't present." + by.toString());
        }
    }

    // Wait for Element to be able clicked
    public static void waitForElementToBeClickable(By by){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element ready to click." + by.toString());
            Assert.fail("Timeout waiting for the element ready to click." + by.toString());
        }
    }

    public static void waitForElementToBeClickable(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element ready to click." + element.toString());
            Assert.fail("Timeout waiting for the element ready to click." + element.toString());
        }
    }

    public static void waitForElementToBeClickable(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element ready to click." + by.toString());
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
            System.out.println("Timeout waiting for the element ready to select." + by.toString());
            Assert.fail("Timeout waiting for the element ready to select." + by.toString());
        }
    }

    public static void waitForElementToBeSelected(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element ready to select." + by.toString());
            Assert.fail("Timeout waiting for the element ready to select." + by.toString());
        }
    }

    public static void waitForElementToBeSelected(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Throwable error){
            System.out.println("Timeout waiting for the element ready to select." + element.toString());
            Assert.fail("Timeout waiting for the element ready to select." + element.toString());
        }
    }


    // Function check Element Exist
    public static Boolean checkElementExist(By by){
        List<WebElement> elementList = DriverManager.getDriver().findElements(by);

        if(elementList.size() > 0){
            System.out.println("Element " + by + "existing.");
            return true;
        } else {
            System.out.println("Element " + by + "NOT exist.");
            return false;
        }
    }

    /**
     * Functions basic
     */

    //Log Console
    public static void logConsole(Object message){
        System.out.println(message);
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
    public static void openURL(String url){
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        System.out.println("Open URL: " + url);
    }

    //Click to Element
    public static void clickElement(By by){
        waitForElementToBeClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        System.out.println("Click element: " + by);
    }

    public static void clickElement(By by, int timeout){
        waitForElementToBeClickable(by, timeout);
        sleep(STEP_TIME);
        getWebElement(by).click();
        System.out.println("Click element: " + by);
    }


    //Set value for Element
    public static void setText(By by, String value){
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        System.out.println("Set value: " + value + "on element " + by);
    }

   public static void setText(WebElement element, String value){
        sleep(STEP_TIME);
        element.sendKeys(value);
       System.out.println("Set value: " + value + "on element " + element);
   }

   public static void setKey(By by, Keys keys){
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(keys);
        System.out.println("Set key: " + keys.name() + "on element " + by);
   }

   public static void setTextAndKey(By by, String value, Keys keys){
        waitForElementPresent(by);
        getWebElement(by).sendKeys(value,keys);
       System.out.println("Set text: " + value + "on element " + by);
   }

   //Get value of Element
    public static String getElementText(By by){
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        System.out.println("Get text: " + text);
        return text;
    }

    //Get value of Element following element's attribute
    public static String getElementAttribute(By by, String attributeName){
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        System.out.println("Attribute value: " + text);
        return text;
    }

    /**
     * Assert and Verify
     */

    public static boolean verifyEquals(Object actual, Object expected){
        waitForPageLoaded();
        System.out.println("Verify equals: " + actual + "\uD83D\uDFF0" + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message){
        waitForPageLoaded();
        System.out.println("Assert equals: " + actual + "\uD83D\uDFF0" + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected){
        waitForPageLoaded();
        System.out.println("Verify contains: " + actual + " contains " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String massage){
        waitForPageLoaded();
        System.out.println("Assert contains: " + actual + " contains " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, massage);
    }

    public static void assertTrue(boolean condition, String message){
        waitForPageLoaded();
        System.out.println("Assert true: " + condition);
        if (!condition){
            Assert.assertTrue(condition, message);
        }
    }

    public static void assertFail(boolean condition, String message){
        waitForPageLoaded();
        System.out.println("Assert fail: " + condition);
        if (condition){
            Assert.assertFalse(condition, message);
        }
    }
}
