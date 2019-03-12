package api.CocaCola.launcherScreen;

import api.CocaCola.createAccountScreen.CreateAccount;
import api.CocaCola.loginScreen.LoginScreen;
import api.drivers.Drivers;
import core.classicmethods.AssertsUtils;
import core.classicmethods.Gestures;
import core.classicmethods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

public class LauncherScreen {

    public LauncherScreen() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();

    @iOSXCUITFindBy(accessibility = "CreateAccount")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement createAcc;

    @iOSXCUITFindBy(accessibility = "LogIn")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement login;

    @iOSXCUITFindBy(accessibility = "WelcomeScreenLogo")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement logo;

    public LauncherScreen validateElementsLauncherScreem() {
        try {
            MyLogger.log.info("Validating elements from Launcher Screen");
            waiters.waitForElementVIsibility(createAcc);
            assertsUtils.isElementDisplayed(createAcc);
            assertsUtils.isElementDisplayed(login);
            assertsUtils.isElementEnabled(logo);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Launcher Screen");
        }
        return this;
    }

    public CreateAccount clickRegisterAccount() {
        try {
            MyLogger.log.info("Trying to click on Create Account button");
            gestures.clickOnMobileElement(createAcc);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Create Account button from Launcher screen");
        }
        return new CreateAccount();
    }

    public LoginScreen clickLoginCategory() {
        try {
            MyLogger.log.info("Trying to click on Login Category button");
            gestures.clickOnMobileElement(login);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Login Category button from Launcher screen");
        }
        return new LoginScreen();
    }
}
