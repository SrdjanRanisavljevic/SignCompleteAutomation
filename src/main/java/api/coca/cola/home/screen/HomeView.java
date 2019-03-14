package api.coca.cola.home.screen;

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

public class HomeView {

    public HomeView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


    /**
     * BOTTOM BAR ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "Home")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/homeFragment\")")
    private MobileElement homeBtn;

    @iOSXCUITFindBy(accessibility = "Scan")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/scanActivity\")")
    private MobileElement scanBtn;

    @iOSXCUITFindBy(accessibility = "Share")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/shareActivity\")")
    private MobileElement shareActivityBtn;

    @iOSXCUITFindBy(accessibility = "Win")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/winFragment\")")
    private MobileElement winBtn;

    @iOSXCUITFindBy(accessibility = "Profile")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/profileFragment\")")
    private MobileElement profileBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentGemsIcon")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/home_number_of_gems\")")
    private MobileElement gemsBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentLove")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/home_love_button\")")
    private MobileElement loveBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentShare")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/home_share_button\")")
    private MobileElement homeShareBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentShare")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/home_more_button\")")
    private MobileElement homeMoreBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentShare")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/more_options_action_button\")")
    private MobileElement reportBtn;

    @iOSXCUITFindBy(accessibility = "HomeContentShare")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/more_options_cancel_button\")")
    private MobileElement cancelBtn;

    @iOSXCUITFindBy(accessibility = "ProfilePhotoPlaceholder")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/profile_photo\")")
    private MobileElement profilePhoto;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Prizes'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/firstSectionTitleTextView\")")
    private MobileElement prizesLabel;


    public HomeView validateElementsFromHomeView() {
        try {
            MyLogger.log.info("Validating elements from Home View");
            waiters.waitForElementVisibility(homeBtn);
            assertsUtils.isElementDisplayed(homeBtn);
            assertsUtils.isElementDisplayed(scanBtn);
            assertsUtils.isElementDisplayed(shareActivityBtn);
            assertsUtils.isElementDisplayed(winBtn);
            assertsUtils.isElementDisplayed(profileBtn);
            assertsUtils.isElementDisplayed(gemsBtn);
            assertsUtils.isElementDisplayed(loveBtn);
            assertsUtils.isElementDisplayed(homeShareBtn);
//            assertsUtils.isElementDisplayed(homeMoreBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Home View");
        }
    }

}
