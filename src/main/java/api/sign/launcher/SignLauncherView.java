package api.sign.launcher;

import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;


public class SignLauncherView {

    public SignLauncherView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();
    private  final Swipe swipe = new Swipe();

    /**
     * LAUNCHER VIEW ELEMENTS
     */



    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/sign_fte_page_content_image\")")
    private MobileElement launcherScreen1;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"sign_fte_page_sign_in_button\")")
    private MobileElement login;


    /**
     * ASSERTATIONS, SEND KEYS
     */

//
//    public SignLauncherView validateElementsLauncherScreem() {
//        try {
//            MyLogger.log.info("Validating elements from Launcher Screen");
//            waiters.waitForElementVisibility(createAcc);
//            assertsUtils.isElementDisplayed(createAcc);
//            assertsUtils.isElementDisplayed(login);
//            assertsUtils.isElementEnabled(logo);
//            return this;
//        } catch (WebDriverException e) {
//            throw new AssertionError("Cannot validate elements from Launcher Screen");
//        }
//    }

    public SignLauncherView swipeLeftThreeTimes() {
        try {
            MyLogger.log.info("Swyping four times to get the screen with login button");
            for (int i = 0; i <= 2; i++) {

             swipe.swipeLeftElementMobile(launcherScreen1);
            }
            return new SignLauncherView();
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot swipe left damn it!");
        }
    }

    public SignLauncherView SwipeLeftThreeTimesS10() {
        try {
            MyLogger.log.info("Swyping four times to get the screen with login button");
            for (int i = 0; i <= 2; i++) {

                swipe.swipeLeftElementMobile(launcherScreen1);
            }
            return new SignLauncherView();
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot swipe left s10 damn it!");
        }
    }

    public SignLauncherView clickOnLoginButton() {
        try {
            MyLogger.log.info("Clicking on Login button on launcher screen");
            gestures.clickOnMobileElement(login);
            return new SignLauncherView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Login button from Sign Launcher screen");
        }
    }
//
//    public SignLauncherView clickOnDontAllowNotificationBtn() {
//        try {
//            MyLogger.log.info("Check if Send Notification Pop-Up is displayed");
//            try {
//                if (sendNotificationPopUp.isEnabled()) {
//                    MyLogger.log.info("Send Notification Pop-Up is displayed?: " + sendNotificationPopUp.isDisplayed());
//                    Drivers.getMobileDriver().switchTo().alert().dismiss();
//                    MyLogger.log.info("Send Notification Pop-Up was dismissed");
//                }
//            } catch (WebDriverException e) {
//                MyLogger.log.info("Send Notification Pop-Up is not displayed and we should validate elements from Launcher View");
//            }
//        } catch (WebDriverException e) {
//            throw new AssertionError("Cannot check if Send Notification Pop-Up is displayed");
//        }
//        return this;
//    }

}


