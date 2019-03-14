package core.classic.methods;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.apache.commons.net.tftp.TFTP.DEFAULT_TIMEOUT;

//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;

/**
 * Created by lumihai on 5/24/2017.
 */
public class Waiters {
    //fluent wait  for an element (used for clicks - wait for element before click)
    public void waitForElementVisibilityAndroid(By element) {
        FluentWait<AndroidDriver> wait = new FluentWait<AndroidDriver>(Drivers.androidDriver).withTimeout(ofSeconds(20)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementVisibilityIOS(By element) {
        FluentWait<IOSDriver> wait = new FluentWait<IOSDriver>(Drivers.driverIos).withTimeout(ofSeconds(20)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waiterForWebView(By element) {
        FluentWait<IOSDriver> wait = new FluentWait<IOSDriver>(Drivers.driverIos).withTimeout(ofSeconds(20)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementVisibility(By element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Drivers.getDriver())
                .withTimeout(ofSeconds(20))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitForElementVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), 30);
        wait.ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void simulateWaiterInsteadOsThreadSleep(MobileElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public boolean waitForInvisibility(By element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            MyLogger.log.info("Element is still visible: " + element);
            MyLogger.log.info(e.getMessage());
            throw new TimeoutException();

        }
    }

    public boolean waitForInvisibilityMobile(MobileElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), timeOut);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            MyLogger.log.info("Element is still visible: " + element);
            MyLogger.log.info(e.getMessage());
            throw new TimeoutException();

        }
    }


    public WebElement waitForElement(By by, WaitCondition waitCondition) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), Gestures.WAIT_TIME_IN_SECONDS);

            switch (waitCondition) {
                case CLICKABLE:
                    element = wait.until(ExpectedConditions.elementToBeClickable(by));
                    break;

                case VISIBLE:
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    break;

                case INVISIBLE:
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
                    break;

                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return element;
    }

    public static void waitForElementVisibilityWebElement(WebElement elem) {
        MyLogger.log.info("Waiting for element " + elem);
        waitForElementWebElement(ExpectedConditions.visibilityOf(elem), DEFAULT_TIMEOUT);
    }

    private static void waitForElementWebElement(ExpectedCondition<?> condition, int timeout) {
        newWait(Drivers.getDriver(), timeout).until(condition);
    }

    private static Wait<WebDriver> newWait(WebDriver driver, int timeout) {
        return new FluentWait<WebDriver>(driver).withTimeout(ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
    }

    public enum WaitCondition {
        CLICKABLE, VISIBLE, INVISIBLE;

        /**
         * Returns the name of the enum constant, in lowercase
         */
        @Override
        public String toString() {
            String s = super.toString();
            return s.toLowerCase();
        }
    }

    public void waitForElementVIsibilityMobileElement4(MobileElement element) {
        AppiumFluentWait<WebDriver> wait = new AppiumFluentWait(Drivers.getMobileDriver());
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    element.isDisplayed();
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        };
        wait.withTimeout(ofSeconds(10))
                .pollingEvery(ofSeconds(1))
                .until(elementIsDisplayed);
    }

    public void waitUntilElementNotDisplayed(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), 20);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    element.isDisplayed();
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }

    public void waitUntilElementContainsText(MobileElement element, String desiredWifi) {
        WebDriverWait wait = new WebDriverWait(Drivers.getMobileDriver(), 20);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    element.getAttribute("content-desc").contains(desiredWifi);
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                } catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.withTimeout(ofSeconds(20))
                .pollingEvery(ofSeconds(1))
                .until(elementIsDisplayed);
    }


    public void waitForElementToBeEnabledMobileElement(MobileElement element) {
        AppiumFluentWait<WebDriver> wait = new AppiumFluentWait(Drivers.getMobileDriver());
        ExpectedCondition elementIsEnabled = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    element.isEnabled();
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                } catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.withTimeout(ofSeconds(20))
                .pollingEvery(ofSeconds(1))
                .until(elementIsEnabled);
    }

}