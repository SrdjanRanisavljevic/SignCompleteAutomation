package core.imageHelper;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;

import static core.jsonParsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.managers.ServerManager.*;

public class Screenshot {

    /**
     * Takes a screenshot of the device
     */
    public static void takeScreenshot() {
        takeScreenshot("");
    }

    /**
     * Take a screenshot of the device
     *
     * @param meaningfulFilelName - text to be added to the screenshot. Avoid crazy characters,
     *                            no validation on filename is done.
     */
//    public static void takeScreenshot(String meaningfulFilelName) {
//        takeScreenshot(meaningfulFilelName);
//    }

    /**
     * Take a screenshot of the device
     *  @param meaningfulFilelName - text to be added to the screenshot. Avoid crazy characters,
     *                            no validation on filename is done.
     *
     */
    public static void takeScreenshot(String meaningfulFilelName) {

        if ((long) 0 > 0) {
            try {
                Thread.sleep((long) 0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        File screenshot = getScreenshot();
        ImageUtil.saveImageAsArtefact(screenshot, meaningfulFilelName);
    }

    public static File getScreenshot() {
//        WebDriver augmentedDriver = new Augmenter()
//                .augment(Drivers.driver);
        WebDriver augmentedDriver = new Augmenter()
                .augment(Drivers.driverIos);
        File screenshot = null;

        int retry = 0;
        do {
            try {
                screenshot = ((TakesScreenshot) augmentedDriver)
                        .getScreenshotAs(OutputType.FILE);
            } catch (Throwable e) {
                // do nothing
            }
            retry++;
        } while ((screenshot == null) && (retry < 5));

        // it may have failed, but we dodn't have an exception
        if (screenshot == null) {
            screenshot = ((TakesScreenshot) augmentedDriver)
                    .getScreenshotAs(OutputType.FILE);
        }
        return screenshot;
    }

    private String name;
    private double size;
    private String location;

    public Screenshot() {
        this.name = "defaultScreenshotName";
    }

    public Screenshot(String location) throws Exception {
        String loc = location;

        if (isWindows() && getPlatformUnderTest().getPlatformName().equals("android")) {
            loc = loc.replace("/", "\\");
        } else if (isMac() && getPlatformUnderTest().getPlatformName().equals("android")) {
            loc = loc.replace("\\", "/");
        } else if (isMac() && getPlatformUnderTest().getPlatformName().equals("ios")) {
            loc = loc.replace("\\", "/");
        } else {
            MyLogger.log.info("Environment is other than Windows and Mac. Please revise getOS method");
            throw new Exception("Setup is ran on other environment; no Windows/Mac or drivers/ios could be identified");
        }

        this.location = loc;
        File f = new File(loc);
        if (!f.exists()) {
            MyLogger.log.error("Failed to create screenshot object. File " + loc + " not found");
            Assert.fail("Failed to create screenshot object. File " + loc + " not found");
        }
        this.name = f.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Screenshot [name=" + name + ", size=" + size + ", location=" + location + "]";
    }
}

