package api.coca.cola.create.account.screen;

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
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

public class CheckMagicLinkView extends ScreenView {

    public CheckMagicLinkView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


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


}