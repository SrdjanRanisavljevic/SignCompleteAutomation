package api.coca.cola.create.account.screen;

import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Waiters;
import core.helpers.JavaHelpers;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;

public class EmailAddressView extends ScreenView {

    public EmailAddressView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

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

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'CREATE ACCOUNT'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement registerPageTitle;

    /**
     * E-MAIL VIEW ELEMENTS
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

    @iOSXCUITFindBy(accessibility = "LoginInputConfirmButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/textinput_error\")")
    private MobileElement textInputError;


    public EmailAddressView validateElementsEmailAddressView() {
        try {
            MyLogger.log.info("Validating elements from Email Address View");
            waiters.waitForElementVisibility(registerPageTitle);
            assertsUtils.isElementDisplayed(registerPageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(emailDescription);
            assertsUtils.isElementDisplayed(emailLabel);
            assertsUtils.isElementDisplayed(emailInput);
            assertsUtils.isElementDisplayed(proceedBtn);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Email Address View");
        }
        return this;
    }


    public EmailAddressView sendTextEmailAddress(String email) {
        try {

            String emails = JavaHelpers.typeRandomNumbers(4) + "@gmail.com";
            MyLogger.log.info("Trying to send text: " + emails + "@gmail.com");
            gestures.sendText(emailInput, email + emails);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + email + " to e-mail address input");
        }
    }


    public CountrySelectionView clickOnProceedButton() {
        MyLogger.log.info("Trying to click on proceed button to move to Country Selection View");
        ScreenView screenView = utilView.clickOnProceedButton(new CountrySelectionView(), gestures, proceedBtn);
        return (CountrySelectionView) screenView;
    }


    public EmailAddressView sendTextAlreadyRegisteredEmail(String email) {
        try {
            MyLogger.log.info("Trying to send text: " + email + " to e-mail address input");
            gestures.sendText(emailInput, email);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + email + " to e-mail address input");
        }
    }


    public EmailAddressView getDisplayedEmail(String prefixEmail) throws FileNotFoundException {
        try {
            MyLogger.log.info("Trying to get the displayed E-mail");
            waiters.waitForElementVisibility(backBtn);
            if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                Assert.assertTrue("The displayed e-mail does not correspond to the expected e-mail", emailInput.getAttribute("value").contains(prefixEmail));
            } else {
                Assert.assertTrue("The displayed e-mail does not correspond to the expected e-mail", emailInput.getAttribute("name").contains(prefixEmail));
            }
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on navigate back button to move to Launcher Screen");
        }

    }


    public EmailAddressView clickRegisterEmailForWrongEnteredEmail() {
        MyLogger.log.info("Trying to click on proceed button on Email Address view");
        ScreenView screenView = utilView.clickOnProceedButton(new EmailAddressView(), gestures, proceedBtn);
        return (EmailAddressView) screenView;
    }


    public EmailAddressView validateWrongEmailInput() {
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
