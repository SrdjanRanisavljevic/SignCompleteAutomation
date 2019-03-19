package api.coca.cola.create.account.screen;

import api.coca.cola.email.screen.CheckingMails;
import api.coca.cola.utils.screen.views.ScreenView;
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
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class CheckMagicLinkView extends ScreenView {

    public CheckMagicLinkView() throws FileNotFoundException {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


    /**
     * Enter code - variables
     */

    private String host = "pop.gmail.com";// change accordingly
    private String mailStoreType = "pop3";
    private String usermail = runningSetup().getUsermail();
    private String password = runningSetup().getUserpassword();
    private String code = "";
    CheckingMails cm = new CheckingMails();


    /**
     * TOP BAR ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "WelcomeScreenLogo")
    @AndroidFindBy(xpath = "//android.widget.ImageView[1]")
    private MobileElement logo;

    @iOSXCUITFindBy(accessibility = "Magic link")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_title\")")
    private MobileElement magicLinkLabel;

    @iOSXCUITFindBy(accessibility = "Almost there! The magic link has been sent to the email account you provided!")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_subtitle\")")
    private MobileElement magicLinkDescription;

    @iOSXCUITFindBy(accessibility = "RedScreenCheckEmail")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_button_check_email\")")
    private MobileElement checkEmailBtn;

    @iOSXCUITFindBy(accessibility = "ENTER CODE")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_button_enter_code\")")
    private MobileElement enterCodeBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"SEND LINK AGAIN\"]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_button_send_again\")")
    private MobileElement sendLinkAgainBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Gmail\")")
    private MobileElement gMail;


    /**
     * Elements for an already registered user
     */

    @iOSXCUITFindBy(accessibility = "Oh, we already know you!")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Oh, we already know you!\")")
    private MobileElement alreadyknowYouLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Looks like you already have a Coca-Cola account!'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/magic_link_subtitle\")")
    private MobileElement alreadyknowYouText;


    /**
     * Enter Code Pop-up Elements
     */

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Magic Link'")
    private MobileElement verificationCodeLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Enter your code '")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/enter_text_message\")")
    private MobileElement verificationCodeDescription;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/text_value\")")
    private MobileElement verificationCodeInput;

    @iOSXCUITFindBy(accessibility = "Login")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/text_confirm\")")
    private MobileElement proceedBtn;


    public CheckMagicLinkView validateElementsCheckMagicLinkView() {
        try {
            MyLogger.log.info("Validating elements from Check Magic Link View");
            waiters.waitForElementVisibility(magicLinkLabel);
            assertsUtils.isElementDisplayed(magicLinkLabel);
            assertsUtils.isElementEnabled(logo);
            assertsUtils.isElementDisplayed(magicLinkDescription);
            assertsUtils.isElementDisplayed(checkEmailBtn);
            assertsUtils.isElementDisplayed(enterCodeBtn);
            assertsUtils.isElementDisplayed(sendLinkAgainBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Check Magic Link View");
        }
    }


    public CheckMagicLinkView clickOnCheckEmailBtn() {
        try {
            MyLogger.log.info("Trying to click on Check Email button");
            gestures.clickOnMobileElement(checkEmailBtn);
            try {
                if (gMail.isDisplayed()) {
                    MyLogger.log.info("Trying to click on Gmail App");
                    gestures.clickOnMobileElement(gMail);
                }
            } catch (WebDriverException e) {
                MyLogger.log.info("Cannot click on Gmail App");
            }
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Check Email button");
        }
    }


    public CheckMagicLinkView validateElementsAlreadyRegisteredUser() {
        try {
            MyLogger.log.info("Try to validate elements from Check Magic Link View for a registered user");
            waiters.waitForElementVisibility(alreadyknowYouLabel);
            assertsUtils.isElementEnabled(logo);
            assertsUtils.isElementDisplayed(checkEmailBtn);
            assertsUtils.isElementDisplayed(enterCodeBtn);
            assertsUtils.isElementDisplayed(sendLinkAgainBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Check Magic Link View for a registered user");
        }
    }


    public CheckMagicLinkView validateMessageForAlreadyRegisteredUser() {
        try {
            MyLogger.log.info("Try to validate the message displayed for an already registered user");
            waiters.waitForElementVisibility(alreadyknowYouText);
            Assert.assertTrue("The user is not registered yet", alreadyknowYouText.getAttribute("name").contains("Looks like you already have a Coca-Cola account!"));
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate the message displayed for an already registered user");
        }
    }


    public CheckMagicLinkView clickOnEnterCodeBtn() {
        try {
            MyLogger.log.info("Trying to click on Enter Code button");
            gestures.clickOnMobileElement(enterCodeBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Enter Code button");
        }
    }


    public CheckMagicLinkView sendTextVerificationCode() throws Exception {
        try {
            MyLogger.log.info("Trying to get the verification code");
            code = cm.writePart(cm.fetch(host, mailStoreType, usermail, password));
            gestures.sendText(verificationCodeInput, code);
            gestures.clickOnMobileElement(proceedBtn);
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot get the verification code");
        }
        return this;
    }



}
