package api.sign.login.Screen;

import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.*;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class SignLoginView {

    public SignLoginView() throws FileNotFoundException {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private String usermail = runningSetup().getUsermail();
    private String userPassword = runningSetup().getUserpassword();

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();

    // 8 SHARD USERS

    // PROD USER
    public final String prodUser = "echosign.igor+prod@gmail.com";


    //NA1


    public final String na1UserName = "echosign.miloss.na1@gmail.com";
    public final String na1UserNameB = "echosign.srdjan.na1@gmail.com";

    // NA2
    public final String na2UserName = "echosign.miloss.na2@gmail.com";
    public final String na2UserNameB = "echosign.srdjan.na2@gmail.com";


    public final String na3UserName = "echosign.miloss.na3@gmail.com";
    public final String na3UserNameB = "echosign.denis.na3@gmail.com";

    public final String eu1UserName = "echosign.miloss.eu1@gmail.com";
    public final String eu1UserNameB = "echosign.srdjan.eu1@gmail.com";

    public final String eu2UserName = "echosign.miloss.eu2@gmail.com";
    public final String eu2UserNameB = "echosign.srdjan.eu2@gmail.com";

    public final String jp1UserName = "echosign.miloss.jp1@gmail.com";
    public final String au1UserName = "echosign.miloss.au1@gmail.com";
    public final String in1UserName = "echosign.miloss.in1@gmail.com";

    UtilView utilView = new UtilView();


    /**
     * TOP BAR ELEMENTS
     */


    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'LOG IN'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"userEmail\")")
    private MobileElement userEmailField;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"userPassword\")")
    private MobileElement userPasswordField;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"login\")")
    private MobileElement signInButton;



//    public SignLogin validateElementsLauncherScreem() {
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

    public SignLoginView enterUserName(String userName) {
        try {
            MyLogger.log.info("Trying to send text: " + usermail + " to e-mail address input");
            gestures.clickOnMobileElement(userEmailField);
            gestures.sendText(userEmailField, userName);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + userName + " to e-mail address input");
        }
    }

    public SignLoginView enterPassword() {
        try {
            MyLogger.log.info("Trying to send text: " + userPassword + " to password input");
            gestures.clickOnMobileElement(userPasswordField);
            gestures.sendText(userPasswordField, userPassword);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + userPassword + " to password input field");
        }
    }

    public SignLoginView clickOnSignInButton() {
        try {
            MyLogger.log.info("Trying to click on Sign In button");
            gestures.clickOnMobileElement(signInButton);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on sign in button");
        }
    }

    public void randomSleep() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Cannot Sleep");

        }
    }
}


