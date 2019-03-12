package core.watchers;

import api.drivers.Drivers;
import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static core.jsonParsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.watchers.TestInfo.name;

public class ScreenshotFailedTests {

    public static String getName(Description description) {
        String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
        return screenshotDirectory + File.separator + description.getClassName() + "_" + description.getMethodName() + "_" + name() + ".png";
    }

    public static String getNameCucumber(Scenario scenario) {
        String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
        return screenshotDirectory + File.separator + scenario.getId() + "_" + scenario.getName() + ".png";
    }

    public static void screenshotFailedTest(Description description) throws Exception {
        try {
            String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
            String screenshotAbsolutePath = screenshotDirectory + File.separator + description.getClassName() + "_" + description.getMethodName() + "_" + name() + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                try {
                    writeScreenshotToFile(getDriver(), screenshot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenshotToFile((IOSDriver) new Augmenter().augment(getDriver()), screenshot);
                }
                System.out.println("Written screenshot to " + screenshotAbsolutePath);

            } else {
                System.err.println("Unable to create " + screenshotAbsolutePath);
            }
        } catch (Exception ex) {
            System.err.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }
    }

    public static void screenshotFailedTestCucumber(Scenario scenario) throws Exception {
        try {
            String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
            String screenshotAbsolutePath = screenshotDirectory + File.separator + scenario.getId() + "_" + scenario.getName() + "_" + name() + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                try {
                    writeScreenshotToFile(getDriver(), screenshot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenshotToFile((IOSDriver) new Augmenter().augment(getDriver()), screenshot);
                }
                System.out.println("Written screenshot to " + screenshotAbsolutePath);

            } else {
                System.err.println("Unable to create " + screenshotAbsolutePath);
            }
        } catch (Exception ex) {
            System.err.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }
    }

    private static boolean createFile(File screenshot) {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenshot.createNewFile();
                } catch (IOException errorCreatingScreenshot) {
                    errorCreatingScreenshot.printStackTrace();
                }
            }
        }

        return fileCreated;
    }

    private static void writeScreenshotToFile(AppiumDriver driver, File screenshot) {
        try {
            FileOutputStream screenshotStream = new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        } catch (IOException unableToWriteScreenshot) {
            System.err.println("Unable to write " + screenshot.getAbsolutePath());
            unableToWriteScreenshot.printStackTrace();
        }
    }

    private static AppiumDriver getDriver() throws Exception {
        AppiumDriver driver = null;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            driver = Drivers.driverIos;
        } else if (getPlatformUnderTest().getPlatformName().equals("android")) {
            driver = Drivers.androidDriver;
        } else {
            throw new Exception("No ios or android was found in platform jason");
        }
        return driver;
    }

}
