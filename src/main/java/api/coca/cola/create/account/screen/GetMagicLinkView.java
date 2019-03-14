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

public class GetMagicLinkView extends ScreenView {

    public GetMagicLinkView() {
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
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Navigate up\")")
    private MobileElement backBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'CREATE ACCOUNT'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement registerPageTitle;


    /**
     * ELEMENTS FROM GET MAGIC LINK VIEW
     */

    @iOSXCUITFindBy(accessibility = "Get the magic link")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_header\")")
    private MobileElement getMagicLinkLabel;

    @iOSXCUITFindBy(accessibility = "Magic link is going to be sent to your email address displayed below")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_sub_header\")")
    private MobileElement getMagicLinkDescription;

//    @iOSXCUITFindBy(accessibility = "LoginInputTextField")
//    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_text\")")
//    private MobileElement getMagicLinkEmail;

    @iOSXCUITFindBy(accessibility = "I ACCEPT")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_button\")")
    private MobileElement magicLinkBtn;

    @iOSXCUITFindBy(accessibility = "The Privacy Policy and Terms and Conditions")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"The Privacy Policy and Terms and Conditions\")")
    private MobileElement termsAndConditionsLabel;


    /**
     * BOTTOM BAR ELEMENTS
     */

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"PRIVACY POLICY\"]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_button_privacy_policy\")")
    private MobileElement privacyPolicyBtn;

    @iOSXCUITFindBy(accessibility = "TERMS OF SERVICE")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_button_terms_of_service\")")
    private MobileElement termsOfServiceBtn;


    public GetMagicLinkView validateElementsGetMagicLinkView() {
        try {
            MyLogger.log.info("Validating elements from Get Magic Link View");
            waiters.waitForElementVisibility(registerPageTitle);
            assertsUtils.isElementDisplayed(registerPageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(getMagicLinkLabel);
            assertsUtils.isElementDisplayed(getMagicLinkDescription);
//            assertsUtils.isElementDisplayed(getMagicLinkEmail);
            assertsUtils.isElementDisplayed(magicLinkBtn);
            assertsUtils.isElementDisplayed(termsAndConditionsLabel);
            assertsUtils.isElementDisplayed(privacyPolicyBtn);
            assertsUtils.isElementDisplayed(termsOfServiceBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Get Magic Link View");
        }
    }


    public CheckMagicLinkView clickOnEmailMeMagicLink() {
        try {
            MyLogger.log.info("Trying to click on Email Me Magic Link button");
            gestures.clickOnMobileElement(magicLinkBtn);
        } catch (WebDriverException e) {
            //do nothing
        }
        return new CheckMagicLinkView();
    }

}
