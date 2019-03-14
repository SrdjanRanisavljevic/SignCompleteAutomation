package core.classic.methods;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.internal.Coordinates;

import static org.junit.Assert.*;

/**
 * Created by lumihai on 5/24/2017.
 */
public class AssertsUtils {

    private final Waiters waiters = new Waiters();

    //another type of wait for elements which appear in some circumstances
    public boolean isElementExist(By by) {
        try {
            int count = Drivers.getMobileDriver().findElements(by).size();
            return count >= 1;
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isElementExist(MobileElement mobileElement) {
        try {
            Coordinates count = mobileElement.getCoordinates();
            int x = count.onScreen().getX();
            MyLogger.log.info("X: " + x);
            int y = count.onScreen().getY();
            MyLogger.log.info("Y: " + y);
            return x >= 1 && y >= 1;
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isElementExistMobile(MobileElement by) {
        try {
            int x = by.getLocation().getX();
            int y = by.getLocation().getY();
            return x >= 1 && y >= 1;
        } catch (Throwable t) {
            return false;
        }
    }

    public void isElementDisplayed(By by) {
        Boolean reult = null;
        try {
            waiters.waitForElementVisibility(by);
            reult = Drivers.getMobileDriver().findElement(by).isDisplayed();
        } catch (TimeoutException e) {
            reult = false;
        }
        assertTrue("Element " + by + " was not displayed", reult);
    }

    public void isElementDisplayed(MobileElement by) {
        Boolean reult = null;
        try {
            waiters.waitForElementVisibility(by);
            reult = by.isDisplayed();
        } catch (TimeoutException e) {
            reult = false;
        }
        assertTrue("Element " + by + " was not displayed", reult);
    }

    public void isElementEnabled(By by) {
        Boolean reult = null;
        try {
            waiters.waitForElementVisibility(by);
            reult = Drivers.getMobileDriver().findElement(by).isEnabled();
        } catch (TimeoutException e) {
            reult = false;
        }
        assertTrue("Element" + by + "was not enabled", reult);
    }

    public void isElementEnabled(MobileElement by) {
        Boolean result = null;
        try {
            result = by.isEnabled();
        } catch (TimeoutException e) {
            result = false;
        }
        assertTrue("Element" + by + "was not enabled", result);
    }

    public boolean isElementEnabledMobile2(MobileElement by) {
        Boolean result = null;
        try {
            result = by.isEnabled();
            MyLogger.log.info("Element: " + by + " is enabled");
        } catch (TimeoutException e) {
            result = false;
            MyLogger.log.info("Element: " + by + " is not enabled");
        }
        return result;
    }


    public void isElementNotDisplayed(By by) {
        boolean result = isElementPresentBoolean(by);
        if (result) {
            result = Drivers.getMobileDriver().findElement(by).isDisplayed();
        }
        assertTrue("Element" + by + "was displayed", result);
    }


    public void isElementNotEnabled(By by) {
        boolean result = isElementPresentBoolean(by);
        if (result) {
            result = Drivers.getMobileDriver().findElement(by).isEnabled();
        }
        assertTrue("Element" + by + "was enabled", !result);
    }

    public boolean isElementNotExist(By by) {
        int count = Drivers.getMobileDriver().findElements(by).size();
        return count == 0;
    }

    public boolean isElementNotExistMobileElement(MobileElement by) {
        int x = by.getLocation().getX();
        int y = by.getLocation().getY();
        return x == 0 && y == 0;
    }

    public boolean isElementVisible(By by) {
        try {
            if (Drivers.getMobileDriver().findElement(by).isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean isElementVisible(MobileElement by) {
        try {
            if (by.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void isElementPresent(By by) {
        boolean result = Drivers.getMobileDriver().findElements(by).size() > 0;
        assertTrue("element" + by + "was not present", result);
    }

    private boolean isElementPresentBoolean(By by) {
        boolean isPresent = false;
        try {
            Waiters waiters = new Waiters();
            waiters.waitForElementVisibility(by);
            isPresent = true;
        } catch (TimeoutException e) {
            System.out.println("Element" + by + "was not displayed");
        }
        return isPresent;
    }

    public void isNotDisplayed(By by) {
        Boolean result = null;
        try {
            result = Drivers.getMobileDriver().findElement(by).isDisplayed();
        } catch (Exception e) {
            result = false;
        }
        assertTrue("element" + by + "was not displayed", result);
    }

    public void isNotDisplayed(MobileElement by) {
        Boolean result = null;
        try {
            result = by.isDisplayed();
            MyLogger.log.info(result + " is displayed");
        } catch (Exception e) {
            result = false;
            MyLogger.log.info(result + " is not displayed");
        }
        assertFalse("element " + by + " was displayed", (boolean) result);
    }

    public Boolean isElementPresentNew(By targetElement) throws InterruptedException {
        return (Drivers.getMobileDriver().findElements(targetElement).size() > 0);
    }

    public void AssertContains(String xPath, String expectedValue, Attribute attribute, String errorMessage) {
        AssertContains(By.xpath(xPath), expectedValue, attribute, errorMessage);
    }

    private void AssertContains(By by, String expectedValue, Attribute attribute, String errorMessage) {
        assertTrue(errorMessage, Drivers.getMobileDriver().findElement(by).getAttribute(attribute.toString())
                .contains(expectedValue));
    }

    public void AssertExists(String xpath, String errorMessage) {
        AssertExists(By.xpath(xpath), errorMessage);
    }

    public void AssertExists(By by, String errorMessage) {
        try {
            Drivers.getMobileDriver().findElement(by);
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found");
            MyLogger.log.info(String.format("XPATH %s not found.", by));
            throw e;
        }
    }

    public void AssertEquals(String xpath, String expectedValue, Attribute attribute, String errorMessage) {
        AssertEquals(By.xpath(xpath), expectedValue, attribute, errorMessage);
    }

    private void AssertEquals(By by, String expectedValue, Attribute attribute, String errorMessage) {
        assertEquals(expectedValue, Drivers.getMobileDriver().findElement(by).getAttribute(attribute.toString()));
    }

    public static void AssertNotEquals(String xPath, String expectedValue, Attribute attribute,
                                       String errorMessage) {
        try {
            AssertNotEquals(By.xpath(xPath), expectedValue, attribute, errorMessage);
        } catch (NoSuchElementException e) {
            MyLogger.log.info(String.format("XPATH %s not found.", xPath));
            throw e;
        }
    }

    private static void AssertNotEquals(By by, String expectedValue, Attribute attribute, String errorMessage) {
        assertNotSame(expectedValue,
                Drivers.getMobileDriver().findElement(by).getAttribute(attribute.toString()));
    }


    public enum Attribute {
        LABEL, NAME, VALUE, XPATH, TEXT, TAGNAME, VISIBLE, COLOR, CHECKED;

        /**
         * Returns the name of the enum constant, in lowercase
         */
        @Override
        public String toString() {
            String s = super.toString();
            return s.toLowerCase();
        }
    }
}
