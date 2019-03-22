package api.coca.cola.create.account.screen;

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

public class TermsOfService {
    public TermsOfService(){
        AppiumDriver driver= Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();

    @iOSXCUITFindBy(accessibility = "GDPR")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"GDPR\")")
    private MobileElement termsOfServiceWebView;

    @iOSXCUITFindBy(accessibility = "CloseButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/close_button\")")
    private MobileElement closeBtn;


    public TermsOfService validateElementsPrivacyInformationView() {
        try {
            MyLogger.log.info("Validating elements from Terms of Service View");
            waiters.waitForElementToBeEnabledMobileElement(termsOfServiceWebView);
            assertsUtils.isElementEnabled(termsOfServiceWebView);
            assertsUtils.isElementDisplayed(closeBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Terms of Service View");
        }
    }
    public GetMagicLinkView clickOnCloseBtn(){
        try {
            MyLogger.log.info("Trying to click on Close Button");
            gestures.clickOnMobileElement(closeBtn);
            return new GetMagicLinkView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Close Button");
        }

    }
}
