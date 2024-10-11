package thaonth7.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import thaonth7.drivers.DriverManager;
import thaonth7.helpers.PropertiesHelper;

public class BaseTest{

    public static void createDriver(){
        WebDriver driver = setupDriver("chrome");
        DriverManager.setDriver(driver);
    }

    public static void createDriver(String browser) {
        WebDriver driver;
        if (PropertiesHelper.getValue("BROWSER") != null && !PropertiesHelper.getValue("BROWSER").isEmpty()) {
            driver = setupDriver(PropertiesHelper.getValue("BROWSER"));
        } else {
            driver = setupDriver(browser);
        }
        DriverManager.setDriver(driver); //Gán giá trị driver vào trong ThreadLocal
    }

    public static WebDriver setupDriver(String browserName) {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }


    public static void closeDriver() {
        DriverManager.quit();
    }

}