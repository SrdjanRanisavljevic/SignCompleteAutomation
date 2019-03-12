package api.CocaCola.createAccountScreen;

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

public class CreateAccount {

    public CreateAccount() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


    /**
     * TOP BAR ELEMENTS
     */
    @iOSXCUITFindBy(accessibility = "chevronRight")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement backBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'CREATE ACCOUNT'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement registerPageTitle;

    /**
     * E-MAIL ELEMENTS
     */
    @iOSXCUITFindBy(accessibility = "Email address")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement emailLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Enter your email'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement emailDescription;

    @iOSXCUITFindBy(accessibility = "LoginInputTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement emailInput;

    @iOSXCUITFindBy(accessibility = "LoginInputConfirmButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"de.telekom.conectivity.inflight.qa:id/action_add_voucher\")")
    private MobileElement proceedBtn;

    public CreateAccount validateElementsLauncherScreem() {
        try {
            MyLogger.log.info("Validating elements from Create Account Screen");
            waiters.waitForElementVIsibility(registerPageTitle);
            assertsUtils.isElementDisplayed(registerPageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(emailDescription);
            assertsUtils.isElementDisplayed(emailLabel);
            assertsUtils.isElementDisplayed(emailInput);
            assertsUtils.isElementDisplayed(proceedBtn);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Create Account Screen");
        }
        return this;
    }

    public CreateAccount sendTextEmailAddress(String email) {
        try {
            MyLogger.log.info("Trying to send text: " + email + " to e-mail address input");
            gestures.sendText(emailInput, email);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + email + " to e-mail address input");
        }
        return this;
    }
}
