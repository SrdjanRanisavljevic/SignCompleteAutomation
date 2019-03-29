package api.coca.cola.launcher.screen;

import api.coca.cola.create.account.screen.EmailAddressView;
import api.coca.cola.login.screen.LoginView;
import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;


public class LauncherView {

    public LauncherView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();

    /**
     * LAUNCHER VIEW ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "CreateAccount")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/welcome_button_create_account\")")
    private MobileElement createAcc;

    @iOSXCUITFindBy(accessibility = "LogIn")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/welcome_button_log_in\")")
    private MobileElement login;

    @iOSXCUITFindBy(accessibility = "WelcomeScreenLogo")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\")")
    private MobileElement logo;

    /**
     * SEND NOTIFICATION POP-UP ELEMENTS
     */

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeAlert' AND name CONTAINS[c] '“Coca-Cola DEV”'")
    private MobileElement sendNotificationPopUp;

    public LauncherView validateElementsLauncherScreem() {
        try {
            MyLogger.log.info("Validating elements from Launcher Screen");
            waiters.waitForElementVisibility(createAcc);
            assertsUtils.isElementDisplayed(createAcc);
            assertsUtils.isElementDisplayed(login);
            assertsUtils.isElementEnabled(logo);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Launcher Screen");
        }
    }

    public EmailAddressView clickRegisterAccount() {
        try {
            MyLogger.log.info("Trying to click on Create Account button");
            gestures.clickOnMobileElement(createAcc);
            return new EmailAddressView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Create Account button from Launcher screen");
        }
    }

    public LoginView clickLoginCategory() {
        try {
            MyLogger.log.info("Trying to click on Login Category button");
            gestures.clickOnMobileElement(login);
            return new LoginView();
        } catch (WebDriverException | FileNotFoundException e) {
            throw new AssertionError("Cannot click on Login Category button from Launcher screen");
        }
    }

    public LauncherView clickOnDontAllowNotificationBtn() {
        try {
            MyLogger.log.info("Check if Send Notification Pop-Up is displayed");
            try {
                if (sendNotificationPopUp.isEnabled()) {
                    MyLogger.log.info("Send Notification Pop-Up is displayed?: " + sendNotificationPopUp.isDisplayed());
                    Drivers.getMobileDriver().switchTo().alert().dismiss();
                    MyLogger.log.info("Send Notification Pop-Up was dismissed");
                }
            } catch (WebDriverException e) {
                MyLogger.log.info("Send Notification Pop-Up is not displayed and we should validate elements from Launcher View");
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot check if Send Notification Pop-Up is displayed");
        }
        return this;
    }


}
