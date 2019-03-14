package core.classic.methods;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;

public class Swipe {


    public static void swipeUp() throws IOException {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MyLogger.log.info("Swipe up using iOS Driver");
            int x = width / 2;
            double startY = height * 0.80;
            double finalY = height * 0.20;
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(x, (int) startY))
                    .moveTo(PointOption.point(x, (int) -finalY))
                    .release()
                    .perform();
        } else {
            MyLogger.log.info("Swipe up using android Driver");
            int x = width / 2;
            double startY = height * 0.70;
            double finalY = height * 0.20;
            new TouchAction(Drivers.getMobileDriver()).press(PointOption.point(x, (int) startY))
                    .moveTo(PointOption.point(x, (int) finalY))
                    .release()
                    .perform();
        }
    }


    public static void swipeUpGeneral() throws IOException, ParseException {
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MobileGestures.mobileSwipe("up");
        } else {
            swipeUp();
        }
    }

    public static void swipeUpAboveKeyboardiOS() {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        new TouchAction(Drivers.getMobileDriver()).press(PointOption.point(width / 2, height / 2)).moveTo(PointOption.point(width / 2, -height - 1)).release().perform();

    }

    public static void swipeUpLeftPartOfScreen() throws IOException, ParseException {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MyLogger.log.info("Swipe up in the left part of the screen using iOS drivers");
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(width / 5, height - height / 3)).moveTo(PointOption.point(width / 5, -height - 1)).release().perform();
        } else {
            MyLogger.log.info("Swipe up in the left part of the screen using aOS drivers");
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(width / 5, height - (height / 3))).moveTo(PointOption.point(width / 5, height / 6)).release().perform();
        }
    }

    public static void swipeDown() throws FileNotFoundException {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MyLogger.log.info("Swipe dowb using iOS Driver");
            int x = width / 2;
            double startY = height * 0.30;
            double finalY = height * 0.80;
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(x, (int) startY))
                    .moveTo(PointOption.point(x, (int) finalY))
                    .release()
                    .perform();
        } else {
            MyLogger.log.info("Swipe down using android Driver");
            int x = width / 2;
            double startY = height * 0.30;
            double finalY = height * 0.90;
            new TouchAction(Drivers.getMobileDriver()).press(PointOption.point(x, (int) startY))
                    .moveTo(PointOption.point(x, (int) finalY))
                    .release()
                    .perform();
        }
    }

    public static void swipeLeftWithTouchiOS() throws IOException, ParseException {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MyLogger.log.info("Swipe left using iOS driver");
            new TouchAction<>(Drivers.getMobileDriver()).longPress(PointOption.point(width - 1, height / 2)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(-width - 1, height / 2)).release().perform();
        } else {
            MyLogger.log.info("Swipe left using android driver");
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(width - 1, height / 2)).moveTo(PointOption.point(width / 10, height / 2)).release().perform();
        }
    }

    public static void swipeRightWithTouchiOS() throws IOException, ParseException {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            MyLogger.log.info("Swipe right using iOS driver");
            new TouchAction<>(Drivers.getMobileDriver()).longPress(PointOption.point(0, height / 2)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width * 2, height / 2)).release().perform();

        } else {
            MyLogger.log.info("Swipe right using android driver");
            new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(1, height / 2)).moveTo(PointOption.point(width - 10, height / 2)).release().perform();
        }
    }

    public static void refreshEmailListUntilEmailIsDisplayed(String subject) throws FileNotFoundException {
        int retry = 0;
        while (retry < 20) {
            try {
                WebElement we = null;
                MyLogger.log.info("Trying to find element" + subject);
                we = Drivers.getMobileDriver().findElementById(subject);
                if (we.isDisplayed()) {
                    break;
                }
            } catch (Throwable e) {
                MyLogger.log.info("Element" + subject + "was not found so we will swipe to find it");
                swipeDown();
            }
            retry++;
        }
    }

    public static void findElementToClickOnItUpInPage2(By by) throws FileNotFoundException {
        WebElement we = null;
        int count = 0;
        while (count < 10) {
            try {
                MyLogger.log.info("Trying to see if element" + by + "is visible before swiping");
                we = Drivers.getMobileDriver().findElement(by);
                if (we.isDisplayed()) {
                    MyLogger.log.info("Element" + by + " is visible so no need to swipe");
                    break;
                }
            } catch (WebDriverException e) {
                if (e.getMessage().contains("could not be located")) {
                    MyLogger.log.info("Performing swipe down to find element" + by + " up in page");
                    swipeDown();
                } else {
                    MyLogger.log.info("Verify method because element " + by + "is still not visible after swiping");
                }
            }
            count++;
        }
    }

    public static void findElementToClickOnItDownInPage(By by) {
        WebElement we = null;
        int count = 0;
        while (count < 10) {
            try {
                MyLogger.log.info("Trying to see if element" + by + "is visible before swiping");
                we = Drivers.getMobileDriver().findElement(by);
                if (we.isDisplayed()) {
                    MyLogger.log.info("Element" + by + " is visible so no need to swipe");
                    break;
                }
            } catch (WebDriverException e) {
                if (e.getMessage().contains("could not be located")) {
                    MyLogger.log.info("Performing swipe down to find element" + by + " up in page");
                    MobileGestures.mobileSwipe("up");
                } else {
                    MyLogger.log.info("Verify method because element " + by + "is still not visible after swiping");
                }
            }
            count++;
        }
    }

    public static void swipeTwoFingers_FromLeftEdgeToRight() {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();

        int height = screenDim.height;
        int width = screenDim.width;

        TouchAction ta1 = new TouchAction(Drivers.getMobileDriver());
        TouchAction ta2 = new TouchAction(Drivers.getMobileDriver());
        MultiTouchAction ma = new MultiTouchAction(Drivers.getMobileDriver());
        ta1.press(PointOption.point(0, height / 4)).moveTo(PointOption.point(2 * width / 3, 0)).release();
        ta1.press(PointOption.point(0, height / 4)).moveTo(PointOption.point(2 * width / 3, 0)).release();
        ma.add(ta1).add(ta2).perform();
    }

    public void findElementToClickOnItDownInPageLeftPart(By by) throws IOException, ParseException {
        WebElement we = null;
        int count = 0;
        while (count < 10) {
            try {
                we = Drivers.getMobileDriver().findElement(by);
                if (we.isDisplayed()) {
                    break;
                }
            } catch (Throwable e) {
                if (e.getMessage().contains("could not be located")) {
                    swipeUp();
                } else {
                    System.out.println("please verify method because element is still not visible");
                }
            }
            count++;
        }

    }

    public void up_inElement(By by, int durationMilliSeconds) {

        Drivers.getMobileDriver().findElement(by).isDisplayed();

        WebElement we = Drivers.getMobileDriver().findElement(by);
        Point location = we.getLocation();
        Dimension size = we.getSize();

        int leftwidthpoint = location.x;
        int upheightpoint = location.y;
        int height = size.height;
        int width = size.width;

        // get middle of the element
        int startPointHorizontal = leftwidthpoint + (width / 2);
        // get lower point of element
        int startPointVertical = upheightpoint + (3 * height / 4);
        // get upper point of element
        int endPointVertical = upheightpoint + 1;
        // int endPointVertical = upheightpoint + (height / 4);

//        Drivers.androidDriver.swipe(startPointHorizontal, startPointVertical, startPointHorizontal, endPointVertical, durationMilliSeconds);
    }

    public void down_inElement(By by, int durationMilliSeconds) {

        Drivers.getMobileDriver().findElement(by).isDisplayed();

        WebElement we = Drivers.getMobileDriver().findElement(by);
        Point location = we.getLocation();
        Dimension size = we.getSize();

        int leftwidthpoint = location.x;
        int upheightpoint = location.y;
        int height = size.height;
        int width = size.width;

        // get middle of the element
        int startPointHorizontal = leftwidthpoint + (width / 2);
        // get upper point of element
        int startPointVertical = upheightpoint + (height / 4);
        // get lower point of element
        int endPointVertical = upheightpoint + height - 1;

//        Drivers.androidDriver.swipe(startPointHorizontal, startPointVertical, startPointHorizontal, endPointVertical, durationMilliSeconds);
    }


    public void right_inElement(By by) {

        Drivers.getMobileDriver().findElement(by).isDisplayed();

        WebElement we = Drivers.getMobileDriver().findElement(by);
        Point location = we.getLocation();
        Dimension size = we.getSize();

        int leftwidthpoint = location.x;
        int upheightpoint = location.y;
        int height = size.height;
        int width = size.width;

        // get middle of the element
        int startPointHorizontal = leftwidthpoint + (width / 2);
        // get upper point of element
        int startPointVertical = leftwidthpoint + (2 * height / 4);
        // get lower point of element
        int endPointHorizontal = leftwidthpoint + 1;

        new TouchAction(Drivers.getMobileDriver()).press(PointOption.point(width, height / 2)).moveTo(PointOption.point(0, height / 2)).release().perform();
    }

    public void swipeRightElementMobile(MobileElement element) {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        int x = element.getSize().getHeight();
        int y = element.getSize().getHeight();
        new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(x / 2, y / 2)).moveTo(PointOption.point(width - 10, height / 2)).release().perform();
    }

    public void swipeLeftElementMobile(MobileElement element) {
        Dimension screenDim = Drivers.getMobileDriver().manage().window().getSize();
        int height = screenDim.height;
        int width = screenDim.width;
        int x = element.getSize().getHeight();
        int y = element.getSize().getHeight();
        new TouchAction(Drivers.getMobileDriver()).longPress(PointOption.point(x / 2, y / 2)).moveTo(PointOption.point(width / 10, height / 2)).release().perform();
    }

    public void scrollDown(Gestures.HorizontalPosition horizontalPosition) {
        int durationmlseconds = 1000;
        up(durationmlseconds, horizontalPosition);
    }

    //scroll up using duration, position of finger
    private void up(int durationmlseconds, Gestures.HorizontalPosition horizontalPosition) {
        up(durationmlseconds, horizontalPosition, false);
    }

    //scroll up using duration, position of fnger, screen dimension
    private void up(int durationmlseconds, Gestures.HorizontalPosition horizontalPosition, boolean isScreenDifferent) {
        Dimension screenSize = Drivers.getMobileDriver().manage().window().getSize();

        int height = screenSize.height;
        int width = screenSize.width;

        int startHorizontal = 0;
        int startVertical = 0;
        int stopVertical = 0;

        switch (horizontalPosition) {
            case CENTER:
                startHorizontal = width / 2;
                break;
            case LEFT:
                startHorizontal = 2;
                break;
            case RIGHT:
                startHorizontal = width - 2;
                break;
            default:
                break;
        }
        startVertical = 3 * (height / 4);
        stopVertical = 2;

        if (isScreenDifferent) {

        } else {
            new TouchAction<>(Drivers.getMobileDriver()).tap(PointOption.point(startHorizontal, startVertical)).waitAction().moveTo(PointOption.point(startHorizontal, stopVertical)).release().perform();
        }
    }

    public void scrollUp(Gestures.HorizontalPosition horizontalPosition) {
        int durationmlseconds = 1000;
        down(durationmlseconds, horizontalPosition);
    }

    private void down(int durationmlseconds, Gestures.HorizontalPosition horizontalPosition) {
        Dimension screenSize = Drivers.getMobileDriver().manage().window().getSize();

        int height = screenSize.height;
        int width = screenSize.width;

        int startHorizontal = 0;
        int startVertical = 0;
        int stopVertical = 0;

        switch (horizontalPosition) {
            case CENTER:
                startHorizontal = width / 2;
                break;
            case LEFT:
                startHorizontal = 2;
                break;
            case RIGHT:
                startHorizontal = width - 2;
                break;
            default:
                break;
        }
        startVertical = (height / 6);
        stopVertical = width - 2;

        if (false) {

        } else {
            new TouchAction<>(Drivers.getMobileDriver()).tap(PointOption.point(startHorizontal, startVertical)).waitAction().moveTo(PointOption.point(startHorizontal, stopVertical)).release().perform();

        }
    }

    private static void newswipeRight(WebElement element) throws IOException, ParseException {
        int y = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.5);
        int startX = (int) (element.getSize().getWidth() * 0.5);
        int endX = (int) (element.getSize().getWidth() * 0.1);
        if (getPlatformUnderTest().getPlatformName().equals("android")) {
            PointOption start = PointOption.point(startX, y);
            PointOption end = PointOption.point(endX, y);
            new TouchAction(Drivers.getMobileDriver()).press(start).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(end).release().perform();
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("duration", 1.0);
            params.put("fromX", startX);
            params.put("fromY", y);
            params.put("toX", endX);
            params.put("toY", y);
            Drivers.getMobileDriver().executeScript("mobile: dragFromToForDuration", params);
        }
    }

}
