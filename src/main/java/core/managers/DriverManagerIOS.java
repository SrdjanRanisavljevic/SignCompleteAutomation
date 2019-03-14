package core.managers;

import api.drivers.Drivers;
import core.constants.Arg;
import core.constants.Resources;
import core.json.parsers.ConfigJasonFileReading;
import core.watchers.MyLogger;
import core.watchers.Timer;
import cucumber.api.Scenario;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.IOSServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverManagerIOS extends ConfigJasonFileReading {

    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;

    private static DesiredCapabilities getCaps(Scenario scenario) throws Exception {
        MyLogger.log.info("+++++++++++ Creating iosDriver caps for device: " + deviceID + " +++++++++++");

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getIOSJasonResults().getPlatformVersion());
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getIOSJasonResults().getAutomationName());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, getIOSJasonResults().getDeviceName());
        caps.setCapability(MobileCapabilityType.APP, getIOSJasonResults().getAppLocation());
        caps.setCapability(MobileCapabilityType.UDID, getIOSJasonResults().getDeviceUDID());
        caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, getIOSJasonResults().getNewCommandTimeout());
        caps.setCapability(IOSMobileCapabilityType.COMMAND_TIMEOUTS, getIOSJasonResults().getCommandTimeouts());
        caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, getIOSJasonResults().getUseNewWDA());
        caps.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, getIOSJasonResults().getWdaLaunchTimeout());
        caps.setCapability(IOSMobileCapabilityType.WDA_STARTUP_RETRIES, getIOSJasonResults().getWdaStartupRetries());
        caps.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT, getIOSJasonResults().getWdaConnectionTimeout());
        caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, getIOSJasonResults().getWdaLocalPort());
        caps.setCapability(IOSMobileCapabilityType.RESET_ON_SESSION_START_ONLY, getIOSJasonResults().getResetOnSessionStartOnly());
//        caps.setCapability(IOSMobileCapabilityType.SHOW_XCODE_LOG, true);

        //caps.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        if (scenario.getName().contains("[German]")) {
            MyLogger.log.info("Device is set to German language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "de");
            caps.setCapability(MobileCapabilityType.LOCALE, "de_DE");
        } else if (scenario.getName().contains("[French]")) {
            MyLogger.log.info("Device is set to French language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "fr");
            caps.setCapability(MobileCapabilityType.LOCALE, "fr_FR");
        } else {
            MyLogger.log.info("Device is set to English language");
            caps.setCapability(MobileCapabilityType.LANGUAGE, "en");
            caps.setCapability(MobileCapabilityType.LOCALE, "en_UK");
        }
        boolean fullResetNeeded = true;
        if (!fullResetNeeded) {
            caps.setCapability(MobileCapabilityType.NO_RESET, true);
        }

        return caps;
    }

    private static AppiumDriverLocalService createService(Scenario scenario) throws Exception {
        Map<String, String> env = new HashMap<>(System.getenv());
        env.put("PATH", "/usr/local/bin:" + env.get("PATH"));

        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File(getIOSJasonResults().getNodeJS()))
                        .withAppiumJS(new File(getIOSJasonResults().getAppiumJS()))
                        .withIPAddress(getIOSJasonResults().getIp())
//                        .usingAnyFreePort()
                        .usingPort(getIOSJasonResults().getAppiumServerPort())
                        .withEnvironment(env)
                        .withStartUpTimeOut(120, TimeUnit.SECONDS)
                        .withArgument(IOSServerFlag.WEBKIT_DEBUG_PROXY_PORT, getIOSJasonResults().getIosWebKit())
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                        .withArgument(Arg.LOG_LEVEL, getIOSJasonResults().getLogLevel())
                        .withCapabilities(getCaps(scenario)));
        MyLogger.log.info("+++++++++++++++++++++++ STARTING APPIUM SERVER ++++++++++++++++++++++");
        MyLogger.log.info(String.format(
                "Appium server running for device with UDID: " + getIOSJasonResults().getDeviceID()));
        MyLogger.log.info("++++++++++++++++++ STARTED APPIUM SERVER ++++++++++++++++++: " + service.getUrl());
        return (AppiumDriverLocalService) service;
    }

    public static void createiOSDriver(Scenario scenario) throws Exception {
        String device = getIOSJasonResults().getDeviceID();
        try {
            deviceID = device;
            if (useDevice(deviceID)) {
                queueUp();
                gracePeriod();
                MyLogger.log.info("Trying to create new Driver for device: " + device);
                createService(scenario).start();
                Drivers.driverIos = getNewDriver((AppiumDriverLocalService) service, getCaps(scenario));
                leaveQueue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Ignore and try next device
        }
    }

    public static void killIOSDriver() {
        if (Drivers.driverIos != null) {
            try {
                MyLogger.log.info("+++++++++++ Killing iOS Driver +++++++++++");
                Drivers.driverIos.quit();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not kill iOS Driver +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Closing iOS Driver +++++++++++");
                Drivers.driverIos.close();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not close iOS Driver +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Closing iOS Service/Server +++++++++++");
                service.stop();
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not close iOS Service/Server +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Trying to uninstall Inflight application after run is finished +++++++++++");
                String cmd = "ios-deploy --id " + runningSetup().getDeviceID() + " --uninstall_only --bundle_id de.telekom.connectivity.inflight";
                Runtime.getRuntime().exec(cmd);
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not uninstall Inflight application after run is finished +++++++++++");
            }
            try {
                MyLogger.log.info("+++++++++++ Trying to uninstall WDA application after run is finished +++++++++++");
                String cmd = "ios-deploy --id " + runningSetup().getDeviceID() + " --uninstall_only --bundle_id com.facebook.WebDriverAgentRunner";
                Runtime.getRuntime().exec(cmd);
            } catch (Throwable t) {
                MyLogger.log.info("+++++++++++ Could not uninstall WDA application after run is finished +++++++++++");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            MyLogger.log.info("+++++++++++ IOS Driver is not initialized, nothing to kill +++++++++++");
        }
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
                //If we did not queue first we need to wait for the other device to initialize iosDriver so there is no collision
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

    public static void leaveQueue() {
        try {
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.remove(deviceID);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static IOSDriver getNewDriver(AppiumDriverLocalService service1, Capabilities capabilities) {
        IOSDriver ad = null;
        try {
            ad = new IOSDriver(service1, capabilities);
        } catch (Throwable t) {
            // if it failed first time, try again
            ad = new IOSDriver(service1, capabilities);
        }
        return ad;
    }

    public static synchronized void stopIpProxyXcodeBuild() throws Exception {
        killIproxy();
        killXcodeBuild();
    }

    private static synchronized void killIproxy() throws Exception {
        MyLogger.log.info("Killing any leftover open iproxy processes");
        String wdaLocalPort = Integer.toString(getIOSJasonResults().getWdaLocalPort());
        if (null == wdaLocalPort || "".equals(wdaLocalPort)) {
            MyLogger.log.warn("wdaLocalPort was not set in the configFile. Falling back to default port value of 8100");
            wdaLocalPort = "8100";
        }
        ProcessBuilder pb = getProcessBuilderForProcess(wdaLocalPort);
        ArrayList<String> pids = getPidsInProcessBuilderForProcessesThatContain(pb, "iproxy", wdaLocalPort);
        killProcesses(pids);
    }

    private static synchronized void killXcodeBuild() throws Exception {
        MyLogger.log.info("Killing any leftover open xcodebuild processes");
        ProcessBuilder pb = getProcessBuilderForProcess("xcodebuild");
        ArrayList<String> pids = getPidsInProcessBuilderForProcessesThatContain(pb, "xcodebuild", getIOSJasonResults().getDeviceID());
        killProcesses(pids);
    }

    private static ProcessBuilder getProcessBuilderForProcess(String processIdentifier) {
        // init the processbuilder
        ProcessBuilder pb = new ProcessBuilder("echo hello world");
        MyLogger.log.info("Getting process builder for process containing: " + processIdentifier);
        // search for all processes running that contain processIdentifier
        pb = new ProcessBuilder("/bin/sh", "-c", "ps -e | grep " + processIdentifier + " | grep -v grep");
        return pb;
    }

    private static ArrayList<String> getPidsInProcessBuilderForProcessesThatContain(ProcessBuilder pb,
                                                                                    String... searchTerms) {
        ArrayList<String> pids = new ArrayList<>();
        Process p;
        String message;
        try {
            p = pb.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((message = bufferedReader.readLine()) != null) {
                // filter only the lines that are linked to searchTerms
                // processes
                MyLogger.log.info("Killing process " + message);
                pids.add(message.trim().substring(0, message.trim().indexOf(' ')));
            }
            p.waitFor();
        } catch (Exception e) {
            MyLogger.log.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return pids;
    }

    private static void killProcesses(ArrayList<String> pids) {
        // init the processbuilder
        ProcessBuilder pb = new ProcessBuilder("echo hello world");
        Process p;
        for (String pid : pids) {
            // loop through all the pids and murder each one
            pb = new ProcessBuilder("/bin/sh", "-c", "kill -9 " + pid);
            try {
                p = pb.start();
                p.waitFor();
                MyLogger.log.info("Killed process " + pid);
            } catch (Exception e) {
                MyLogger.log.error(e.getMessage(), e);
                Assert.fail(e.getMessage());
            }
        }
        if (!pids.isEmpty()) {
            MyLogger.log.info("Successfully killed " + pids.size() + " processes");
        } else {
            MyLogger.log.info("Nothing to kill.");
        }
    }

}
