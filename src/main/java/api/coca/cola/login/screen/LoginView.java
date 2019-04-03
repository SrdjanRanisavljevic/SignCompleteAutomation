package api.coca.cola.login.screen;

import api.coca.cola.create.account.screen.CheckMagicLinkView;
import api.coca.cola.create.account.screen.EmailAddressView;
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
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class LoginView extends ScreenView {

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


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/textinput_error\")")
    private MobileElement textInputError;

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


    public LoginView validateWrongEmailNotification() {
        try {
            MyLogger.log.info("Validate wrong email notification is displayed and elements are displayed");
            waiters.waitForElementVisibility(wrongEmailLabel);
            assertsUtils.isElementDisplayed(wrongEmailLabel);
            assertsUtils.isElementDisplayed(wrongEmailDescription);
            assertsUtils.isElementDisplayed(tryAgainBtn);
            assertsUtils.isElementDisplayed(registerBtn);
            Assert.assertTrue("The email address is already registered", wrongEmailDescription.getAttribute("name").equals("Hey, it looks like we have never seen this email before!"));
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate wrong email notification is displayed and elements are displayed");
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


    public EmailAddressView clickOnRegisterBtn() {
        try {
            MyLogger.log.info("Trying to click on Register button");
            gestures.clickOnMobileElement(registerBtn);
            return new EmailAddressView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Register button");
        }
    }

    public LoginView dataKeptOnBackgroundInteraction(String element) {
        try {
            MyLogger.log.info("Validate that the input for email is kept on background interaction for Login Screen");
            try {
                if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                    waiters.waitForElementVisibility(emailLabel);
                    Assert.assertTrue("Email address was cleared on background interaction", emailInput.getAttribute("value").equals(element));
                } else {
                    waiters.waitForElementVisibility(emailLabel);
                    Assert.assertTrue("Email address was cleared on background interaction", emailInput.getAttribute("text").equals(element));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        } catch (NoSuchElementException e) {
            throw new AssertionError("Cannot validate the input for email is kept on background interaction for Login Screen");
        }
    }

    public LoginView dataNotKeptOnExitFromLogin(String element) {
        try {
            MyLogger.log.info("Validate that the input for email is not kept after exit and enter again in Login Screen");
            try {
                if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                    Assert.assertFalse(emailInput.getAttribute("value").equals(element));
                } else {
                    Assert.assertFalse(emailInput.getAttribute("text").equals(element));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        } catch (NoSuchElementException e) {
            throw new AssertionError("Cannot validate the input for email is kept on background interaction for Login Screen");
        }
    }

    public LoginView clickOnLoginButtonForIncorrectEmailFormat() {
        MyLogger.log.info("Trying to click on proceed button to display the error message");
        ScreenView screenView = null;
        try {
            screenView = utilView.clickOnProceedButton(new LoginView(), gestures, proceedBtn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (LoginView) screenView;
    }

    public LoginView verifyInvalidEmailAddressError() {
        try {
            MyLogger.log.info("Verifying that invalid email error is displayed when filling a wrong email address format");
            waiters.waitForElementVisibility(textInputError);
            assertsUtils.isElementDisplayed(textInputError);
            Assert.assertTrue("The email address format is correct", textInputError.getAttribute("name").equals("Invalid email address"));
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot verify invalid email address error is displayed when filling a wrong email address format");
        }
    }

}
