package core.helpers;

import api.drivers.Drivers;
import core.classicmethods.Gestures;
import core.classicmethods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class IOSControl {
    private final Gestures gestures = new Gestures();
    private final Waiters waiters = new Waiters();

    public IOSControl() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Elements from MAIN OS SCREEN
    @iOSXCUITFindBy(accessibility = "Safari")
    private MobileElement safariOS;

    @iOSXCUITFindBy(accessibility = "Settings")
    private MobileElement settingsOS;


    //Eelements from SAFARI SCREEN
    @iOSXCUITFindBy(id = "URL")
    private MobileElement urlSafari;

    @iOSXCUITFindBy(accessibility = "Go")
    private MobileElement goBtnKeyboard;


    //Elements from SETTINGS
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCell' AND name MATCHES[c] 'Wi-Fi'")
    private MobileElement wifiCategory;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name MATCHES[c] 'Settings'")
    private MobileElement backFromWifiCategory;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCell' AND name CONTAINS[c] 'Signal'")
    private MobileElement connectedWIFI;

    @iOSXCUITFindBy(accessibility = "UIPreferencesBlueCheck")
    private MobileElement connectedToWifiCheck;


    //Elements from Data Connection
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCell' AND name MATCHES[c] 'Mobile Data'")
    private MobileElement mobileData;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCell' AND name MATCHES[c] 'Mobile Data' AND value MATCHES[c] 'Off'")
    private MobileElement mobileDataOFF;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSwitch' AND name MATCHES[c] 'Mobile Data' AND value MATCHES[c] '0'")
    private MobileElement mobileDataSwitchOFF;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSwitch' AND name MATCHES[c] 'Mobile Data' AND value MATCHES[c] '1'")
    private MobileElement mobileDataSwitchON;


    public IOSControl clickSafariFromOS() {
        try {
            MyLogger.log.info("Trying to click on Safari from OS Phone");
            gestures.clickOnMobileElement(safariOS);
        } catch (Exception e) {
            throw new AssertionError("Could not click on Safari from phone OS");
        }
        return this;
    }

    public IOSControl clickSettingsOS() {
        try {
            MyLogger.log.info("Trying to click on Settings from OS Phone");
            gestures.clickOnMobileElement(settingsOS);
        } catch (Exception e) {
            throw new AssertionError("Could not click on Settings from phone OS");
        }
        return this;
    }

    public IOSControl clickWIFICategory() {
        try {
            MyLogger.log.info("Trying to click on Wifi Category from OS Phone");
            try {
                if (backFromWifiCategory.isDisplayed()) {
                    MyLogger.log.info("You are already in WIFI category list");
                }
            } catch (Exception e) {
                gestures.clickOnMobileElement(wifiCategory);
            }
        } catch (Exception e) {
            throw new AssertionError("Could not click on Wifi Category from phone OS");
        }
        return this;
    }

    public IOSControl sendTextSafariURL(String address) {
        try {
            MyLogger.log.info("Trying to send text to Safari URL");
            gestures.sendText(urlSafari, address);
        } catch (Exception e) {
            throw new AssertionError("Could not send text to Safari URL");
        }
        return this;
    }

    public IOSControl clickGoKeyboard() {
        try {
            MyLogger.log.info("Trying to click on GO button from Safari Keyboard");
            gestures.clickOnMobileElement(goBtnKeyboard);
        } catch (Exception e) {
            throw new AssertionError("Could not click on GO button from Safari Keybaord");
        }
        return this;
    }

    public IOSControl selectDesiredWifi(String desiredWifi) {
        try {
            MyLogger.log.info("Trying to select desired WIFI from WIFI list");
            if (getConnectedSSID().contains(desiredWifi)) {
                MyLogger.log.info("You are already connected to desired wifiL: " + desiredWifi);
            } else {
                try {
                    waiters.simulateWaiterInsteadOsThreadSleep(safariOS, 5);
                } catch (WebDriverException ignored) {

                }
                MyLogger.log.info("Trying to click on desired wifi: " + desiredWifi);
                MobileElement element = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeCell' AND name CONTAINS[c] '" + desiredWifi + "'"));
                gestures.clickOnMobileElement(element);
                waiters.waitForElementToBeEnabledMobileElement(connectedToWifiCheck);
            }
        } catch (Exception e) {
            throw new AssertionError("Could not connect to desired WIFI from WIFI list");
        }
        return this;
    }

    public IOSControl clickMobileDataCategory() {
        try {
            MyLogger.log.info("Trying to click on Mobile Data Category from OS Phone");
            try {
                if (backFromWifiCategory.isDisplayed()) {
                    MyLogger.log.info("You are already in Mobile Data category list");
                }
            } catch (Exception e) {
                gestures.clickOnMobileElement(mobileData);
            }
        } catch (Exception e) {
            throw new AssertionError("Could not click on Mobile Data Category from phone OS");
        }
        return this;
    }

    public IOSControl enableDisableMobileData() {
        try {
            MyLogger.log.info("Trying to enable Mobile Data");
            try {
                if (mobileDataSwitchOFF.isDisplayed())
                    MyLogger.log.info("Mobile Data is DISABLED, so we can enable it");
                gestures.clickOnMobileElement(mobileDataSwitchOFF);
                waiters.waitForElementVIsibility(mobileDataSwitchON);
            } catch (Exception e) {
                MyLogger.log.info("Mobile Data is already ENABLED, so we can DISABLE it");
                gestures.clickOnMobileElement(mobileDataSwitchON);
                waiters.waitForElementVIsibility(mobileDataSwitchOFF);
            }
        } catch (Exception e) {
            throw new AssertionError("Could not enable Mobile Data");
        }
        return this;
    }

    private String getConnectedSSID() throws FileNotFoundException {
        String connectedSSID;
        connectedSSID = connectedWIFI.getAttribute("name");
        MyLogger.log.info(connectedSSID);
        return connectedSSID;
    }

}
