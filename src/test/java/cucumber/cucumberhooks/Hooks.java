package cucumber.cucumberhooks;

import api.drivers.Drivers;
import core.helpers.ADB;
import core.helpers.MacTerminalCmd;
import core.helpers.WebHelpers;
import core.managers.DriverManagerAndroid;
import core.managers.DriverManagerIOS;
import core.watchers.MyLogger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.helpers.NetworkHelpers.getConnectionStatus;
import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.json.parsers.ConfigJasonFileReading.runningSetup;
import static core.watchers.MobileTestWatcher.*;
import static core.watchers.ScreenshotFailedTests.screenshotFailedTestCucumber;

public class Hooks {

    @Before(order = 1)
    public void uninstallTheApp(Scenario scenario) throws IOException, InterruptedException {
        if (scenario.getName().contains("first uninstall") && runningSetup().getPlatformName().equals("ios")) {
            MyLogger.log.info("+++++++++++++ Uninstalling the app since it is required +++++++++++++");
            MacTerminalCmd.runCommand("ios-deploy --id " + runningSetup().getDeviceID() + " --uninstall_only --bundle_id " + runningSetup().getBundleId());
            MyLogger.log.info("+++++++++++++ Installing the app to be fresh installed +++++++++++++");
            MacTerminalCmd.runCommand("ios-deploy --id " + runningSetup().getDeviceID() + " --bundle " + runningSetup().getAppLocation());
        } else if (scenario.getName().contains("first uninstall") && runningSetup().getPlatformName().equals("android")) {
            MyLogger.log.info("+++++++++++++ Uninstalling the app since it is required +++++++++++++");
            MyLogger.log.info("adb -s " + runningSetup().getDeviceID() + " uninstall " + runningSetup().getAppPackage());
            ADB.command("adb -s " + runningSetup().getDeviceID() + " uninstall " + runningSetup().getAppPackage());


            MyLogger.log.info("+++++++++++++ Installing the app to be fresh installed +++++++++++++");
            ADB.command("adb -s " + runningSetup().getDeviceID() + " install " + runningSetup().getAppLocation());
        }
    }


    @Before(order = 2)
    public void starting(Scenario scenario) {
        synchronized (this) {
            startedTests++;
        }
        MyLogger.log.info("+++++++++++++ Starting test +++++++++++++");
        MyLogger.log.info("+++++++++++++ " + scenario.getId() + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ " + scenario.getName() + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Started Tests: " + startedTests + " +++++++++++++");
        try {
            if (getPlatformUnderTest().getPlatformName().equals("android")) {
                MyLogger.log.info("++++++++++++++++++++++ Android Driver creation started ++++++++++++++++++++++");
                DriverManagerAndroid.createDriver(scenario);
            } else if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                MyLogger.log.info("++++++++++++++++++++++ iOS Driver creation started ++++++++++++++++++++++");
                DriverManagerIOS.createiOSDriver(scenario);
            } else {
                MyLogger.log.info("Environment is other than Windows and Mac. Please revise getOS method");
                throw new Exception("Setup is ran on other environment; no Windows or Mac could be identified");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before(order = 3)
    public void seeIfWifiIsEnabled() throws FileNotFoundException {
        MyLogger.log.info("+++++++++++++ VERIFY IF WI-FI IS ENABLED BEFORE STARTING THE TEST +++++++++++++");
        if (runningSetup().getPlatformName().equals("android")) {
            if (getConnectionStatus().isWiFiEnabled()) {
                MyLogger.log.info("WIFI ON ---> WE CAN CONTINUE TESTING");
            } else {
                MyLogger.log.info("WIFI OFF ---> NO POINT TO CONTINUE TESTING");
                throw new AssertionError("WIFI OFF ---> NO POINT TO CONTINUE TESTING");
            }
        } else {
            //future implementation for iOS
        }
    }

    @Before(order = 4)
    public void startIowWebKit(Scenario scenario) throws Exception {
        if (scenario.getName().contains("[Payment]") && runningSetup().getPlatformName().equals("ios")) {
            WebHelpers.startIosWebKit();
        }
    }

    @After(order = 2)
    public void finished(Scenario scenario) {
        synchronized (this) {
            finishedTests++;
        }

        if (scenario.isFailed()) {
            synchronized (this) {
                failedTests++;
            }
            MyLogger.log.info("Test Failed: " + scenario.getId() + ", " + scenario.getName());
            try {
                scenario.embed(((TakesScreenshot) Drivers.getMobileDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
                screenshotFailedTestCucumber(scenario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            synchronized (this) {
                passedTests++;
            }
            MyLogger.log.info("Test Passed: " + scenario.getId() + ", " + scenario.getName());
        }

        MyLogger.log.info("+++++++++++++ Started Tests: " + startedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Succeeded Tests: " + passedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Failed Tests: " + failedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Finished Tests: " + finishedTests + " +++++++++++++");
        try {
            if (getPlatformUnderTest().getPlatformName().equals("android")) {
                MyLogger.log.info("++++++++++++++++++++++ Killing Android Driver ++++++++++++++++++++++");
                DriverManagerAndroid.killDriver();
            } else if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                MyLogger.log.info("++++++++++++++++++++++ Killing iOS Driver ++++++++++++++++++++++");
                DriverManagerIOS.killIOSDriver();
            } else {
                MyLogger.log.info("Environment is other than Windows and Mac. Please revise getOS method");
                throw new Exception("Setup is ran on other environment; no Windows or Mac could be identified");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After(order = 1)
    public void stopIowWebKit(Scenario scenario) throws Exception {
        if (scenario.getName().contains("[Payment]") && runningSetup().getPlatformName().equals("ios")) {
            WebHelpers.stopIosWebKit();
        }
    }


}
