package api.drivers;

import core.helpers.ADB;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class Drivers {

    public static AndroidDriver androidDriver;
    public static IOSDriver driverIos;
    public static ChromeDriver chromeDriver;
    public static RemoteWebDriver mobileWebDriver;
    public static WebDriver fireFoxDriver;

    public static ADB adb;

    public static AppiumDriver getMobileDriver() {
        AppiumDriver driver = null;
        try {
            if (runningSetup().getPlatformName().equals("ios")) {
                driver = Drivers.driverIos;
            } else if (runningSetup().getPlatformName().equals("android")) {
                driver = Drivers.androidDriver;
            } else {
                throw new Exception("No ios or android was found in platform jason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver getWebDriver() {
        WebDriver driver = null;
        try {
            if (getPlatformUnderTest().getPlatformName().equals("webchrome")) {
                driver = Drivers.chromeDriver;
            } else if (getPlatformUnderTest().getPlatformName().equals("webios")) {
                driver = Drivers.androidDriver;
            } else if (getPlatformUnderTest().getPlatformName().equals("webfirefox")) {
                driver = Drivers.fireFoxDriver;
            } else {
                throw new Exception("No ios or android was found in platform jason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        WebDriver driver = null;
        try {
            if (getPlatformUnderTest().getPlatformName().equals("ios") || getPlatformUnderTest().getPlatformName().equals("android")) {
                driver = getMobileDriver();
            } else {
                driver = getWebDriver();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
