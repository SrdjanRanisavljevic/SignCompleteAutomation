package api.coca.cola.login.screen;

import api.coca.cola.create.account.screen.CheckMagicLinkView;
import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
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

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class LoginView {

    public LoginView() throws FileNotFoundException {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private String usermail = runningSetup().getUsermail();

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();
    UtilView utilView = new UtilView();


    /**
     * TOP BAR ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "chevronRight")
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Navigate up\")")
    private MobileElement backBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'LOG IN'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement loginPageTitle;

    /**
     * LOG IN Elements
     */

    @iOSXCUITFindBy(accessibility = "Email address")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/emailAddressSubtitle\")")
    private MobileElement emailLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Enter your email'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/emailAddressText\")")
    private MobileElement emailDescription;

    @iOSXCUITFindBy(accessibility = "LoginInputTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/auto_generated_edit_text\")")
    private MobileElement emailInput;

    @iOSXCUITFindBy(accessibility = "LoginInputConfirmButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/next_lottie_button\")")
    private MobileElement proceedBtn;


    /**
     * Wrong Email Elements from Pop-up
     */

    @iOSXCUITFindBy(accessibility = "Wrong Email")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/plain_dialog_text\")")
    private MobileElement wrongEmailLabel;

    @iOSXCUITFindBy(accessibility = "Hey, it looks like we have never seen this email before!")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/plain_dialog_subtext\")")
    private MobileElement wrongEmailDescription;

    @iOSXCUITFindBy(accessibility = "TRY AGAIN")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/dialog_button_one\")")
    private MobileElement tryAgainBtn;

    @iOSXCUITFindBy(accessibility = "REGISTER")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/dialog_button_two\")")
    private MobileElement registerBtn;


    public LoginView validateElementsLoginScreen() {
        try {
            MyLogger.log.info("Validating elements from Login Screen");
            waiters.waitForElementVisibility(loginPageTitle);
            assertsUtils.isElementDisplayed(loginPageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(emailDescription);
            assertsUtils.isElementDisplayed(emailLabel);
            assertsUtils.isElementDisplayed(emailInput);
            assertsUtils.isElementDisplayed(proceedBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Login Screen");
        }
    }


    public LoginView sendTextEmailAddressUsingJson() {
        try {
            MyLogger.log.info("Trying to send text: " + usermail + " to e-mail address input");
            gestures.sendText(emailInput, usermail);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + usermail + " to e-mail address input");
        }
    }


    public CheckMagicLinkView clickProceedWithLogin() throws FileNotFoundException {
        MyLogger.log.info("Trying to click on proceed button to move to Check Magic Link View");
        ScreenView screenView = utilView.clickOnProceedButton(new CheckMagicLinkView(), gestures, proceedBtn);
        return (CheckMagicLinkView) screenView;
    }


    public LoginView sendTextEmailAddressUsingString(String email) {
        try {
            MyLogger.log.info("Trying to send text: " + email + " to e-mail address input");
            gestures.sendText(emailInput, email);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + email + " to e-mail address input");
        }
    }


    public LoginView clickOnTryAgainBtn() {
        try {
            MyLogger.log.info("Trying to click on Try Again button");
            gestures.clickOnMobileElement(tryAgainBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Try Again button");
        }
    }

}
