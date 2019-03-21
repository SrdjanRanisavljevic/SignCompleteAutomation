package api.coca.cola.create.account.screen;

import api.coca.cola.profile.screen.settings.screen.CocaColaWebView;
import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConsentsView extends ScreenView {

    public ConsentsView() {
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

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'CONSENTS'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement consentsPageTitle;


    /**
     * PRIVACY INFORMATION VIEW ELEMENTS
     */

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Direct Marketing Communications'")
    @AndroidFindBy(xpath = "//android.widget.ExpandableListView/android.view.ViewGroup[1]/android.widget.TextView[1]")
    private MobileElement directMarketingCommLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Sending you direct marketing communications '")
    @AndroidFindBy(xpath = "//android.widget.ExpandableListView/android.view.ViewGroup[1]/android.widget.TextView[2]")
    private MobileElement directMarketingCommDescription;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSwitch' AND name CONTAINS[c] 'Direct Marketing Communications'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/list_item_consents_toggle\")")
    private MobileElement directMarketingCommSelectBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSwitch' AND value MATCHES '1'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/list_item_consents_toggle\").checked(true)")
    private MobileElement directMarketingCommSelectBtnIsTicked;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name CONTAINS[c] 'PRIVACY INFORMATION'")
    @AndroidFindBy(xpath = "//android.widget.ExpandableListView/android.view.ViewGroup[1]/android.widget.Button")
    private MobileElement expandPrivacyInfo;

    @iOSXCUITFindBy(accessibility = "PRIVACY NOTICE")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/consents_button_one\")")
    private MobileElement privacyNotice;

    @iOSXCUITFindBy(accessibility = "PROCEED")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/consents_button_two\")")
    private MobileElement proceedBtn;


    public ConsentsView validateElementsConsentsView() {
        try {
            MyLogger.log.info("Validating elements from Consents Information View");
            waiters.waitForElementVisibility(consentsPageTitle);
            assertsUtils.isElementDisplayed(consentsPageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(directMarketingCommLabel);
            assertsUtils.isElementDisplayed(directMarketingCommDescription);
//            assertsUtils.isElementDisplayed(specialOffersSelectBtn);
            assertsUtils.isElementDisplayed(expandPrivacyInfo);
            assertsUtils.isElementDisplayed(privacyNotice);
//            assertsUtils.isElementDisplayed(proceedBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Consents Information View");
        }
    }


    public ConsentsView tickThePromotionsToggle() {
        try {
            MyLogger.log.info("Trying to click on participate to promotions toggle");
            waiters.waitForElementVisibility(backBtn);
            gestures.clickOnMobileElement(directMarketingCommSelectBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on on participate to promotions toggle");
        }
    }


    public SetUpProfileView clickOnProceedBtn() throws IOException, ParseException {
        Swipe.swipeUp();
        Swipe.swipeUp();
        Swipe.swipeUp();
        MyLogger.log.info("Trying to click on proceed button");
        ScreenView screenView = utilView.clickOnProceedButton(new SetUpProfileView(), gestures, proceedBtn);
        return (SetUpProfileView) screenView;
    }


    public ConsentsView isPromotionsToggleTicked() throws FileNotFoundException {
        try {
            MyLogger.log.info("Trying to see if the promotions toggle is ticked");
            Swipe.swipeDown();
            Swipe.swipeDown();
            Swipe.swipeDown();
            waiters.waitForElementVisibility(directMarketingCommLabel);

            assertsUtils.isElementDisplayed(directMarketingCommSelectBtnIsTicked);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot check if the promotions toggle is ticked");
        }
    }


    public BirthdaySelectionView clickOnNavigateBackBtn() {
        MyLogger.log.info("Trying to click on navigate back button to move to Birthday Selection View");
        ScreenView screenView=utilView.clickOnNavigateBackBtn(new BirthdaySelectionView(),gestures,backBtn);
        return (BirthdaySelectionView) screenView;
    }


    public CocaColaWebView clickOnPrivacyNotice() {
        try {
            MyLogger.log.info("Trying to click on Privacy Notice");
            gestures.clickOnMobileElement(privacyNotice);
            return new CocaColaWebView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on Privacy Notice");
        }
    }


}
