package core.managers;

import api.drivers.Drivers;
import core.constants.Arg;
import core.constants.Resources;
import core.helpers.ADB;
import core.json.parsers.ConfigJasonFileReading;
import core.watchers.MyLogger;
import core.watchers.Timer;
import cucumber.api.Scenario;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverManagerAndroid extends ConfigJasonFileReading {

    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;

    private static DesiredCapabilities getCaps(Scenario scenario) throws Exception {
        MyLogger.log.info("+++++++++++ Creating androidDriver caps for device: " + deviceID + " +++++++++++");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getAndroidJasonResults().getPlatformName());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, getAndroidJasonResults().getDeviceName());
        caps.setCapability(MobileCapabilityType.APP, getAndroidJasonResults().getAppLocation());
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getAndroidJasonResults().getAutomationName());
        caps.setCapability(MobileCapabilityType.UDID, getAndroidJasonResults().getDeviceUDID());
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, getAndroidJasonResults().getNewCommandTimeout());
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getAndroidJasonResults().getAppActivity());
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getAndroidJasonResults().getAppPackage());
        caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, getAndroidJasonResults().isUnicodeKeyboard());
        caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, getAndroidJasonResults().getSystemPort());
        if (scenario.getName().contains("[German]")) {
            MyLogger.log.info("Device is set to German language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "de");
            caps.setCapability(MobileCapabilityType.LOCALE, "DE");
        } else if (scenario.getName().contains("[French]")) {
            MyLogger.log.info("Device is set to French language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "fr");
            caps.setCapability(MobileCapabilityType.LOCALE, "FR");
        } else {
            MyLogger.log.info("Device is set to English language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "en");
            caps.setCapability(MobileCapabilityType.LOCALE, "EN");
        }
        //        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//        caps.setCapability(AndroidMobileCapabilityType.AVD, AVD_EMULATOR());
//        caps.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, "120000");
        return caps;
    }

    private static URL host(String deviceID) throws Exception {
        String UDID = getAndroidJasonResults().getDeviceID();
        if (hosts == null) {
            hosts = new HashMap<String, URL>();
            hosts.put(UDID, new URL("http://127.0.0.1:4723/wd/hub"));
        }
        return hosts.get(deviceID);
    }

    private static ArrayList<String> getAvailableDevices() throws FileNotFoundException {
        MyLogger.log.info("Checking for available devices");
        ArrayList<String> avaiableDevices = new ArrayList<String>();
        ArrayList connectedDevices = ADB.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            String device = connectedDevice.toString();
            ArrayList apps = new ADB(device).getInstalledPackages();
            if (!apps.contains(getAndroidJasonResults().getAppPackage())) {
                if (useDevice(deviceID)) avaiableDevices.add(device);
                else MyLogger.log.info("Device: " + deviceID + " is being used by another JVM");
            } else
                MyLogger.log.info("Device: " + device + " has " + getAndroidJasonResults().getAppPackage() + " installed, assuming it is under testing");
        }
        if (avaiableDevices.size() == 0) {
            throw new RuntimeException("Not a single device is available for testing at this time");
//            createEmulator();
//            try {
//                Thread.sleep(40000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ArrayList connectedDevices2 = ADB.getConnectedDevices();
//            for (Object connectedDevice : connectedDevices2) {
//                String device = connectedDevice.toString();
//                ArrayList apps = new ADB(device).getInstalledPackages();
//                if (!apps.contains(unlockPackage)) {
//                    if (useDevice(deviceID)) avaiableDevices.add(device);
//                    else MyLogger.log.info("Device: " + deviceID + " is being used by another JVM");
//                } else
//                    MyLogger.log.info("Device: " + device + " has " + unlockPackage + " installed, assuming it is under testing");
//            }
        }
        return avaiableDevices;
    }

    private static AppiumDriverLocalService createService(Scenario scenario) throws Exception {

        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File(getAndroidJasonResults().getNodeJS()))
                        .withAppiumJS(new File(getAndroidJasonResults().getAppiumJS()))
                        .withIPAddress(getAndroidJasonResults().getIp())
//                        .usingAnyFreePort()
                        .usingPort(getAndroidJasonResults().getAppiumServerPort())
                        .withArgument(GeneralServerFlag.ROBOT_ADDRESS, getAndroidJasonResults().getDeviceID())
                        .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
                                getAndroidJasonResults().getBootstratPort())
                        .withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, getAndroidJasonResults().getChromePort())
                        .withArgument(Arg.LOG_LEVEL, getAndroidJasonResults().getLogLevel())
                        .withStartUpTimeOut(120, TimeUnit.SECONDS)
                        .withCapabilities(getCaps(scenario)));
        MyLogger.log.info("+++++++++++++++++++++++ STARTING APPIUM SERVER ++++++++++++++++++++++");
        MyLogger.log.info(String.format(
                "Appium server running for device with UDID: " + getAndroidJasonResults().getDeviceID() + " using bootstrap port: " + getAndroidJasonResults().getBootstratPort() + " and chromedriverport: " +
                        getAndroidJasonResults().getChromePort()));
        MyLogger.log.info("++++++++++++++++++ STARTED APPIUM SERVER ++++++++++++++++++: " + service.getUrl());
        return (AppiumDriverLocalService) service;
    }

    public static void createDriver(Scenario scenario) throws Exception {
        MyLogger.log.info("+++++++  Checking for available devices +++++++");
        ArrayList connectedDevices = ADB.getConnectedDevices();
        if (connectedDevices.size() == 0) {
            throw new RuntimeException("Not a single device is available for testing at this time");
        } else if (connectedDevices.size() > 0) {
            MyLogger.log.info("+++++++  Devices list is > 0 +++++++");
            MyLogger.log.info("+++++++  Try to see if our device is in connected devices list +++++++");
            if (getConnectedDevices().contains(getAndroidJasonResults().getDeviceID())) {
                MyLogger.log.info("Device " + getAndroidJasonResults().getDeviceID() + " was found and setup can continue");
            } else {
                throw new AssertionError(getAndroidJasonResults().getDeviceID() + " was not found in available " + getConnectedDevices());
            }
        }
        String device = getAndroidJasonResults().getDeviceID();
        try {
            deviceID = device;
            if (useDevice(deviceID)) {
                queueUp();
                gracePeriod();
                MyLogger.log.info("Trying to create new Driver for device: " + device);
                createService(scenario).start();
                Drivers.androidDriver = getNewDriver((AppiumDriverLocalService) service, getCaps(scenario));
                Drivers.adb = new ADB(device);
                leaveQueue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Ignore and try next device
        }
    }

    public static void killDriver() throws FileNotFoundException {
        if (Drivers.androidDriver != null) {
            try {
                MyLogger.log.info("+++++++++++ Killing ANDROID Driver +++++++++++");
                Drivers.androidDriver.quit();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not kill ANDROID Driver +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Closing ANDROID Driver +++++++++++");
                Drivers.androidDriver.close();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not close ANDROID Driver +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Closing ANDROID Service/Server +++++++++++");
                service.stop();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not close ANDROID Service/Server +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Trying to uninstall Inflight application after run is finished +++++++++++");
                Drivers.adb.uninstallApp(getAndroidJasonResults().getAppPackage());
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not uninstall Inflight application after run is finished +++++++++++");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else MyLogger.log.info("+++++++++++ Drivers Driver is not initialized, nothing to kill +++++++++++");
    }

    private static void queueUp() {
        try {
            MyLogger.log.info("Queueing Up: " + deviceID);
            JSONObject json = new JSONObject();
            json.put("queued_at", Timer.getTimeStamp());
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.put(deviceID, json);
            MyLogger.log.info("JSON Queue: " + jsonQueue);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean useDevice(String deviceID) {
        try {
            JSONObject json = Resources.getQueue();
            if (json.containsKey(deviceID)) {
                JSONObject deviceJson = (JSONObject) json.get(deviceID);
                long time = (long) deviceJson.get("queued_at");
                int diff = Timer.getDifference(time, Timer.getTimeStamp());
                return diff >= 30;
            } else return true;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void gracePeriod() {
        int waitTime = 0;
        try {
            JSONObject json = Resources.getQueue();
            Set keys = json.keySet();

            JSONObject ourDeviceJson = (JSONObject) json.get(deviceID);
            json.remove(deviceID);
            long weQueuedAt = (long) ourDeviceJson.get("queued_at");

            for (Object key : keys) {
                JSONObject deviceJson = (JSONObject) json.get(key);
                long theyQueuedAt = (long) deviceJson.get("queued_at");
                //If we did not queue first we need to wait for the other device to initialize androidDriver so there is no collision
                if (weQueuedAt > theyQueuedAt) {
                    //But only if device queued first and recently, otherwise we can assume device was already initialized or no longer being used
                    int diff = Timer.getDifference(theyQueuedAt, Timer.getTimeStamp());
                    if (diff < 50) {
                        MyLogger.log.info("Device: " + key + " queued first, I will need to give it extra time to initialize");
                        waitTime += 15;
                    }
                }
            }
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leaveQueue() {
        try {
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.remove(deviceID);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static AndroidDriver getNewDriver(AppiumDriverLocalService service1, Capabilities capabilities) {
        AndroidDriver ad = null;
        try {
            ad = new AndroidDriver(service1, capabilities);
        } catch (Throwable t) {
            // if it failed first time, try again
            ad = new AndroidDriver(service1, capabilities);
        }

        return ad;
    }

    public static URL getHubUrl() throws Exception {
        URL url = null;
        String hubUrl = "http://" + getAndroidJasonResults().getIp() + ":" + getAndroidJasonResults().getAppiumPort() + "/wd/hub";
        try {
            url = new URL(hubUrl);
        } catch (MalformedURLException e) {
            MyLogger.log.error(e.getMessage());
        }
        return url;
    }

    private static List<String> getConnectedDevices() {
        ArrayList connectedDevices = ADB.getConnectedDevices();
        List<String> device = new ArrayList<>();
        for (Object connectedDevice : connectedDevices) {
            device.add(connectedDevice.toString());
        }
        return device;
    }
}
