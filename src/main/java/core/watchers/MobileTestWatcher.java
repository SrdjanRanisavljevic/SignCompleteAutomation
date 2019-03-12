package core.watchers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

import static core.jsonParsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.watchers.ScreenshotFailedTests.screenshotFailedTest;

public class MobileTestWatcher extends TestWatcher {
    private static final TestInfo testInfo = new TestInfo();
    public static int startedTests = 0;
    public static int failedTests = 0;
    public static int passedTests = 0;
    public static final int skippedTests = 0;
    public static int finishedTests = 0;

    @BeforeClass
    public static void setUp() throws IOException {
//        createHTMLReport();
    }

    @Override
//    protected void starting(Description description) {
    protected void starting(Description description) {
        synchronized (this) {
            startedTests++;
        }
        testInfo.reset();
        MyLogger.log.info("+++++++++++++ Starting test +++++++++++++");
        MyLogger.log.info("+++++++++++++ " + description.getClassName() + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ " + description.getMethodName() + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ " + description.getDisplayName() + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Started Tests: " + startedTests + " +++++++++++++");
        try {
            if (getPlatformUnderTest().getPlatformName().equals("android")) {
                MyLogger.log.info("++++++++++++++++++++++ Android Driver creation started ++++++++++++++++++++++");
//                DriverManagerAndroid.createDriver();
            } else if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                MyLogger.log.info("++++++++++++++++++++++ iOS Driver creation started ++++++++++++++++++++++");
//                DriverManagerIOS.createiOSDriver();
            } else {
                MyLogger.log.info("Environment is other than Windows and Mac. Please revise getOS method");
                throw new Exception("Setup is ran on other environment; no Windows or Mac could be identified");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        startMemRec();
    }

    @Override
    protected void failed(Throwable t, Description description) {
        synchronized (this) {
            failedTests++;
        }
        MyLogger.log.info("Test Failed: " + description.getClassName() + ", " + description.getMethodName());
        TestInfo.printResults();
        try {
            screenshotFailedTest(description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void succeeded(Description description) {
        synchronized (this) {
            passedTests++;
        }
        MyLogger.log.info("Test Passed:");
        TestInfo.printResults();
    }

    @Override
    protected void finished(Description description) {
//        stopMemRec(description.getClassName() + System.currentTimeMillis());
        synchronized (this) {
            finishedTests++;
        }

        MyLogger.log.info("+++++++++++++ Started Tests: " + startedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Succeeded Tests: " + passedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Failed Tests: " + failedTests + " +++++++++++++");
        MyLogger.log.info("+++++++++++++ Finished Tests: " + finishedTests + " +++++++++++++");
        try {
            if (getPlatformUnderTest().getPlatformName().equals("android")) {
                MyLogger.log.info("++++++++++++++++++++++ Killing Android Driver ++++++++++++++++++++++");
//                DriverManagerAndroid.killDriver();
            } else if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                MyLogger.log.info("++++++++++++++++++++++ Killing Android Driver ++++++++++++++++++++++");
//                DriverManagerAndroid.killDriver();
            } else {
                MyLogger.log.info("Environment is other than Windows and Mac. Please revise getOS method");
                throw new Exception("Setup is ran on other environment; no Windows or Mac could be identified");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() throws IOException {
//        getResutls();
//        closeHTMLReport();
    }


}
