package api.coca.cola.profile.screen.settings.screen;

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

public class CocaColaWebView {

    public CocaColaWebView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();

    /**
     * TOP BAR ELEMENTS
     */

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Coca-Cola DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeWebView")
    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView")
    private MobileElement cocaColaWebPage;

    @iOSXCUITFindBy(accessibility = "CloseButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/close_button\")")
    private MobileElement closeBtn;


    public CocaColaWebView validateElementsFromCocaColaWebView() {
        try {
            MyLogger.log.info("Try to validate elements from Coca Cola Web View");
            waiters.waitForElementVisibility(closeBtn);
//            assertsUtils.isElementDisplayed(cocaColaWebPage);
            assertsUtils.isElementDisplayed(closeBtn);
            return this;
        } catch (WebDriverException i) {
            throw new AssertionError("Cannot validate elements from Coca Cola Web View");
        }
    }

    public SettingsView clickOnCloseBtn() {
        try {
            MyLogger.log.info("Try to click on close button");
            gestures.clickOnMobileElement(closeBtn);
            return new SettingsView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on close button");
        }
    }
}
