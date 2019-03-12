package core.helpers;

import api.drivers.Drivers;
import core.classicmethods.AssertsUtils;
import core.watchers.MyLogger;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileHelpers {
    private static final String NATIVE_APP = "NATIVE_APP";
    private static Dimension screenSize;
    private final AssertsUtils assertsUtils = new AssertsUtils();


    public static void switchToNativeContext() {
        MyLogger.log.info("Trying to switch to NATIVE_APP");
        if (Drivers.getMobileDriver().getContext().equalsIgnoreCase(NATIVE_APP)) {
            MyLogger.log.info("Already on NATIVE_APP");
        } else {
            Drivers.getMobileDriver().getContextHandles();
            try {
                Drivers.driverIos.context(NATIVE_APP);
                MyLogger.log.info("Successfully switched to NATIVE_APP");
            } catch (NoSuchContextException e) {
                MyLogger.log.error("Could not switch to NATIVE_APP");
                throw e;
            }
        }

    }

    public static boolean isLandscapeMode() {
        MyLogger.log.info("Verifying if screen in in LANDSCAPE MODE");
        return Drivers.getMobileDriver().getOrientation().equals(ScreenOrientation.LANDSCAPE);
    }

    public static boolean isPortraitMode() {
        MyLogger.log.info("Verifying if screen in in PORTRAIT MODE");
        return Drivers.getMobileDriver().getOrientation().equals(ScreenOrientation.PORTRAIT);
    }

    /**
     * EXECUTE A SHELL COMMAND ON MAC USING JAVA
     *
     * @param command desired command to be executed
     * @return
     */

    public static String execMacOsCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            MyLogger.log.info("Executing delete shell command: " + command);
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    /**
     * Method to install the app
     *
     * @param appPath path to your ipa file
     */
    public static void installApp(String appPath) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        MyLogger.log.info("Installing the app: " + appPath);
        Map<String, Object> params = new HashMap<>();
        params.put("app", appPath);
        js.executeScript("mobile: installApp", params);
    }

    /**
     * Method to uninstall the app
     *
     * @param appBundleId Bundle id of your app
     */
    public static void uninstallApp(String appBundleId) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        MyLogger.log.info("Uninstalling the app: " + appBundleId);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", appBundleId);
        js.executeScript("mobile: removeApp", params);
    }

    /**
     * Method to verify if the app is installed
     *
     * @param appBundleId Bundle id of your app
     */
    public static boolean isInstalled(String appBundleId) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", appBundleId);
        final boolean isInstalled = (Boolean) js.executeScript("mobile: isAppInstalled", params);
        MyLogger.log.info("Application is: " + isInstalled);
        return isInstalled;
    }

    /**
     * Method to close the app
     *
     * @param appBundleId Bundle id of your app
     */
    public static void closeApp(String appBundleId) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        MyLogger.log.info("Closing the app: " + appBundleId);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", appBundleId);
        final boolean wasRunningBefore = (Boolean) js.executeScript("mobile: terminateApp", params);
    }

    /**
     * Method to Launch the app
     *
     * @param appBundleId Bundle id of your app
     */
    public static void launchApp(String appBundleId) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        MyLogger.log.info("Launching the app: " + appBundleId);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", appBundleId);
        js.executeScript("mobile: launchApp", params);
    }

    /**
     * Method for getting memory info for current process
     * Example of usage: int totalPss1 = getMemoryInfo(driver).get(PSS_TYPE);
     * PSS_TYPE parameter: totalPss
     *
     * @param packageID   - app package id
     * @param action      - parameter for action: memoryinfo
     * @param memoryUsage - parameter for memory usage wait: 30000
     * @return
     * @throws Exception
     */
    private static HashMap<String, Integer> getMemoryInfo(String packageID, String action, int memoryUsage) throws Exception {
        List<List<Object>> data = Drivers.androidDriver.getPerformanceData(packageID, action, memoryUsage);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    /**
     * Method for simulating iOS upgrade
     *
     * @param bundleId   - bundle id of the application
     * @param newAppPath - path to the new app
     */
    public static void iOSuPgrade(String bundleId, String newAppPath) {
        HashMap<String, String> bundleArgs = new HashMap<>();
        bundleArgs.put("bundleId", bundleId);
        Drivers.driverIos.executeScript("mobile: terminateApp", bundleArgs);

        HashMap<String, String> installArgs = new HashMap<>();
        installArgs.put("app", newAppPath);
        Drivers.driverIos.executeScript("mobile: installApp", installArgs);

        Drivers.driverIos.executeScript("mobile: launchApp", bundleArgs);
    }

    /**
     * Method for simulating aOS upgrade
     *
     * @param newAppPath  - path to the new app to which you want to update
     * @param appPackage  - app package of the app
     * @param appActivity - app activity of the app
     */
    public static void aOSuPgrade(String newAppPath, String appPackage, String appActivity) {
        Drivers.androidDriver.installApp(newAppPath);
        Activity activity = new Activity(appPackage, appActivity);
        Drivers.androidDriver.startActivity(activity);
    }

    public void testPushNotifications(String bundleId, String identifier) {
        screenSize = Drivers.getMobileDriver().manage().window().getSize();
        MyLogger.log.info("Terminating the app in order to look for the push notification");
        Drivers.getMobileDriver().terminateApp(bundleId);
        MyLogger.log.info("Opening OS notification bar");
        showNotifications();
        try {
            MyLogger.log.info("Verify if notification identified by XPATH is displayed on screen");
            By pushItem = By.xpath(identifier);
            assertsUtils.isElementDisplayed(pushItem);
        } catch (AssertionError e) {
            MyLogger.log.info("Verify if notification identified by ID is displayed on screen");
            By pushItem = By.id(identifier);
            assertsUtils.isElementDisplayed(pushItem);
        }
        MyLogger.log.info("Closing OS notification bar");
        hideNotifications();
        MyLogger.log.info("Reactivating app under test");
        Drivers.getMobileDriver().activateApp(bundleId);
    }

    private void showNotifications() {
        manageNotifications(true);
    }

    private void hideNotifications() {
        manageNotifications(false);
    }

    private void manageNotifications(Boolean show) {
        int yMargin = 5;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction(Drivers.getMobileDriver());
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.perform();
    }

    public static void startMemRec() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("timeout", 60000);
        args.put("pid", "current");
        args.put("profileName", "Time Profiler");
        Drivers.getMobileDriver().executeScript("mobile: startPerfRecord", args);

    }

    public static void stopMemRec(String suiteName) {
        File traceZip = new File("src/test/resources/" + suiteName + ".zip");
        HashMap<String, Object> args = new HashMap<>();
        args = new HashMap<>();
        args.put("profileName", "Time Profiler");
        String b64Zip = (String) Drivers.getMobileDriver().executeScript("mobile: stopPerfRecord", args);
        byte[] bytesZip = Base64.getMimeDecoder().decode(b64Zip);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(traceZip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write(bytesZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
