package api.coca.cola.sign.login.Screen;

import api.coca.cola.SignLauncher.SignLauncherView;
import api.coca.cola.create.account.screen.CheckMagicLinkView;
import api.coca.cola.create.account.screen.EmailAddressView;
import api.coca.cola.login.screen.LoginView;
import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.*;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
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

    public SignLoginView enterUserName() {
        try {
            MyLogger.log.info("Trying to send text: " + usermail + " to e-mail address input");
            gestures.clickOnMobileElement(userEmailField);
            gestures.sendText(userEmailField, usermail);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + usermail + " to e-mail address input");
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


