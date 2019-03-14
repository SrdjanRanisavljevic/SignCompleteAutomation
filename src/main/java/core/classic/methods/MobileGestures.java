package core.classic.methods;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class MobileGestures {

    /**
     * Mobile Swipe using JS - WORKS
     *
     * @param direction direction: Either 'up', 'down', 'left' or 'right'
     */
    public static void mobileSwipe(String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        js.executeScript("mobile: swipe", params);
    }

    /**
     * Mobile Swipe in Element using JS
     *
     * @param direction direction: Either 'up', 'down', 'left' or 'right'
     */
    public static void mobileSwipeInElement(RemoteWebElement element, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        params.put("direction", direction);
        js.executeScript("mobile: swipe", params);
    }

    public static void mobileSwipeInElement2(By by, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        params.put("direction", direction);
        js.executeScript("mobile: swipe", params);
    }

    public static void mobileSwipeInElementMobileElement(MobileElement by, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", by);
        params.put("direction", direction);
        js.executeScript("mobile: swipe", params);
    }

    /**
     * Mobile scroll to a certain text - WORKS
     * Other parameters:
     * predicateString - the NSPredicate locator of the child element, to which the scrolling should be performed. Has no effect if element is not a container
     * toVisible - Boolean parameter. If set to true then asks to scroll to the first visible element in the parent container. Has no effect if element is not set
     *
     * @param direction direction 'up', 'down', 'left' or 'right'
     */
    public static void mobileScroll(String name, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("direction", direction);
        js.executeScript("mobile: scroll", params);
    }

    public static void scrollDownAndroid (String direction){
        JavascriptExecutor js = Drivers.getMobileDriver();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObject);
    }

    /**
     * Mobile Pinch - used for app screen - WORKS
     * Other parameters:
     *
     * @param scale    Use a scale between 0 and 1 to "pinch close" or zoom out and a scale greater than 1 to "pinch open" or zoom in
     * @param velocity The velocity of the pinch in scale factor per second (float value)
     */
    public static void mobilePinchScreen(float scale, float velocity) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("scale", scale);
        params.put("velocity", velocity);
        js.executeScript("mobile: pinch", params);
    }

    /**
     * Mobile Pinch - used for an element from app screen
     * element: The internal element identifier (as hexadecimal hash string) to pinch on. Application element will be used instead if this parameter is not provided
     *
     * @param scale    Use a scale between 0 and 1 to "pinch close" or zoom out and a scale greater than 1 to "pinch open" or zoom in
     * @param velocity The velocity of the pinch in scale factor per second (float value)
     */
    public static void mobilePinchElement(RemoteWebElement element, int scale, int velocity, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        params.put("scale", scale);
        params.put("velocity", velocity);
        js.executeScript("mobile: pinch", params);
    }

    public static void mobilePinchElement2(By by, int scale, int velocity, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        params.put("scale", scale);
        params.put("velocity", velocity);
        js.executeScript("mobile: pinch", params);
    }

    /**
     * Mobile DoubleTap - used to double tap on an element - WORKS
     * element: The internal element identifier (as hexadecimal hash string) to double tap on
     * x: Screen x tap coordinate of type float. Mandatory parameter only if element is not set
     * y: Screen y tap coordinate of type float. Mandatory parameter only if element is not set
     *
     * @param element Element to tap on it
     */
    public static void mobileDoubleTap(RemoteWebElement element) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        js.executeScript("mobile: doubleTap", params);
    }

    public static void mobileDoubleTap2(By by) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        js.executeScript("mobile: doubleTap", params);
    }

    /**
     * Mobile TouchAndHold - used to long press on an element to enable additional options - WORKS
     * x: Screen x long tap coordinate of type float. Mandatory parameter only if element is not set
     * y: Screen y long tap coordinate of type float. Mandatory parameter only if element is not set
     *
     * @param element  Element to tap on it
     * @param duration How long to hold the press action
     */
    public static void mobileTouchAndHold(RemoteWebElement element, int duration) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        params.put("duration", duration);
        js.executeScript("mobile: touchAndHold", params);
    }

    public static void mobileTouchAndHold2(By by, int duration) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        params.put("duration", duration);
        js.executeScript("mobile: touchAndHold", params);
    }

    /**
     * Mobile tap  - Performs tap gesture by coordinates on the given element or on the screen.
     *
     * @param element The internal element identifier (as hexadecimal hash string) to long tap on
     * @param x       x tap coordinate of type float. Mandatory parameter
     * @param y       y tap coordinate of type float. Mandatory parameter
     */
    public static void mobileTap(RemoteWebElement element, float x, float y) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        params.put("x", x);
        params.put("y", y);
        js.executeScript("mobile: tap", params);
    }

    public static void mobileTap2(By by, float x, float y) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        params.put("x", x);
        params.put("y", y);
        js.executeScript("mobile: tap", params);
    }

    public static void mobileTapElement(RemoteWebElement element) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        js.executeScript("mobile: tap", params);
    }

    public static void mobileTapElement2(By by) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        js.executeScript("mobile: tap", params);
    }

    public static void mobileTapElementTwoFingers(RemoteWebElement element) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", element.getId());
        js.executeScript("mobile: twoFingerTap", params);
    }

    public static void mobileTapElementTwoFingers2(By by) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("element", Drivers.getMobileDriver().findElement(by));
        js.executeScript("mobile: twoFingerTap", params);
    }

    /**
     * Performs drag and drop gesture by coordinates. This can be done either on an element or on the screen
     *
     * @param element The internal element identifier (as hexadecimal hash string) to perform drag on. All the coordinates will be calculated relatively this this element position on the screen. Absolute screen coordinates are expected if this argument is not set
     * @param fromx   The x coordinate of starting drag point (type float). Mandatory parameter
     * @param fromy   The y coordinate of starting drag point (type float). Mandatory parameter
     * @param tox     The x coordinate of ending drag point (type float). Mandatory parameter
     * @param toy     The y coordinate of ending drag point (type float). Mandatory parameter
     */
    public static void mobileDragAndDrop(RemoteWebElement element, int fromx, int fromy, int tox, int toy) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 1.0);
        params.put("fromX", fromx);
        params.put("fromY", fromy);
        params.put("toX", tox);
        params.put("toY", toy);
        params.put("element", (element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);

    }

    public static void mobileDragAndDrop2(By by, int fromx, int fromy, int tox, int toy) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 1.0);
        params.put("fromX", fromx);
        params.put("fromY", fromy);
        params.put("toX", tox);
        params.put("toY", toy);
        params.put("element", Drivers.getMobileDriver().findElement(by));
        js.executeScript("mobile: dragFromToForDuration", params);

    }

    public static void mobileDragAndDrop3(int fromx, int fromy, int tox, int toy) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 0.5);
        params.put("fromX", fromx);
        params.put("fromY", fromy);
        params.put("toX", tox);
        params.put("toY", toy);
        js.executeScript("mobile: dragFromToForDuration", params);

    }

    /**
     * Performs selection of the next or previous picker wheel value. This might be useful if these values are populated dynamically, so you don't know which one to select or value selection does not work because of XCTest bug.
     * <p>
     * order: Either next to select the value next to the current one from the target picker wheel or previous to select the previous one. Mandatory parameter
     * offset: The value in range [0.01, 0.5]. It defines how far from picker wheel's center the click should happen. The actual distance is culculated by multiplying this value to the actual picker wheel height. Too small offset value may not change the picker wheel value and too high value may cause the wheel to switch two or more values at once. Usually the optimal value is located in range [0.15, 0.3]. 0.2 by default
     *
     * @param element PickerWheel's internal element id (as hexadecimal hash string) to perform value selection on. The element must be of type XCUIElementTypePickerWheel. Mandatory parameter
     */
    public static void selectPickerWheelValue(RemoteWebElement element, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("order", direction);
        params.put("offset", 0.15);
        params.put("element", (element).getId());
        js.executeScript("mobile: selectPickerWheelValue", params);

    }

    public static void selectPickerWheelValue(By element, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("order", direction);
        params.put("offset", 0.15);
        params.put("element", element);
        js.executeScript("mobile: selectPickerWheelValue", params);

    }

    public static void selectPickerWheelValue(MobileElement element, String direction) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("order", direction);
        params.put("offset", 0.15);
        params.put("element", element.getId());
        js.executeScript("mobile: selectPickerWheelValue", params);

    }

    /**
     * Mobile Alerts - to accept or to dismiss alerts
     *
     * @param action The following actions are supported: accept, dismiss and getButtons
     */
    public static void mobileAlerts(String action) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("action", action);
        js.executeScript("mobile: alert", params);
    }

    /**
     * Mobile tap on desired coordinates using touch actions method
     *
     * @param x
     * @param y
     */
    public static void tapGestureCoordinates(int x, int y) {
        new TouchAction<>(Drivers.getMobileDriver()).tap(PointOption.point(x, y)).release().perform();
    }

    public static void tapAboveKeyboardIos() {
        MobileElement keyboard = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeKeyboard'"));
        Rectangle rectangle = keyboard.getRect();
        int refElMidX = rectangle.getWidth();
        MyLogger.log.info("DESIRED X: " + refElMidX);
        int refElMidY = rectangle.getHeight();
        MyLogger.log.info("DESIRED y: " + refElMidY);
        int desiredElMidX = refElMidX / 2;
        MyLogger.log.info("DESIRED X: " + desiredElMidX);
        int desiredElMidY = refElMidY - 3;
        MyLogger.log.info("DESIRED y: " + desiredElMidY);
        tapGestureCoordinates(desiredElMidX, desiredElMidY);

    }

    public static void tapOnTopOfTheScreen() {
        Dimension initial_size = Drivers.getMobileDriver().manage().window().getSize();
        int height = initial_size.getHeight();
        int width = initial_size.getWidth();
//        tapGestureCoordinates(width / 2, 70);
        tapGestureCoordinates(3, 70);
    }

    public static void longTapGestureCoordinates(int x, int y) {
        new TouchAction<>(Drivers.getMobileDriver()).longPress(PointOption.point(x, y)).release().perform();
    }

    public static void swipeInElement(WebElement element) {
        JavascriptExecutor js = Drivers.androidDriver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "right");
        scrollObject.put("element", element.getText());
        js.executeScript("mobile: swipe", scrollObject);
    }

    public static void swipeInElement2(By by) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "right");
        scrollObject.put("element", Drivers.getDriver().findElement(by).getText());
        js.executeScript("mobile: swipe", scrollObject);
    }

    public static void setPasteBoard(String inputText) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> args = new HashMap<>();
//            args.put("content", new String(Files.readAllBytes(new File("/etc/passwd").toPath()), Charset.forName("latin-1")));
        args.put("content", inputText);
        js.executeScript("mobile: setPasteboard", args);
    }

    public static void getPasteBoard() {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> args = new HashMap<>();
        js.executeAsyncScript("mobile: getPasteboard");
    }

    public static void scrollFromToAndroid(MobileElement fromElement, MobileElement toElement) {
        JavascriptExecutor js = Drivers.getMobileDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", fromElement);
        params.put("elementToId", toElement);
        js.executeScript("mobile:scrollBackTo", params);

    }

    public static void tapOutsideTheEmailListBox(MobileElement element){
        Rectangle rectangle = element.getRect();
        int refElMidX = rectangle.getWidth();
        MyLogger.log.info("DESIRED X: " + refElMidX);
        int refElMidY = rectangle.getHeight();
        MyLogger.log.info("DESIRED y: " + refElMidY);
        int desiredElMidX = refElMidX +20;
        MyLogger.log.info("DESIRED X: " + desiredElMidX);
        int desiredElMidY = refElMidY /2;
        MyLogger.log.info("DESIRED y: " + desiredElMidY);
        tapGestureCoordinates(desiredElMidX, desiredElMidY);
    }

}
