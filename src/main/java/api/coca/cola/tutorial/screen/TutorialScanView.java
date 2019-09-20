package api.coca.cola.tutorial.screen;

import api.coca.cola.home.screen.HomeViewSign;
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

public class TutorialScanView {

    public TutorialScanView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


    /**
     * SCAN VIEW ELEMENTS
     */


    @iOSXCUITFindBy(accessibility = "Scan")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Scan\")")
    private MobileElement tutorialScanLabel;

    @iOSXCUITFindBy(accessibility = "Scan a can or bottle of Coke, Fanta or Sprite to unlock word")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/tutorial_description\")")
    private MobileElement tutorialScanDescription;

    @iOSXCUITFindBy(accessibility = "Skip")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip\")")
    private MobileElement skipTutorialBtn;

    @iOSXCUITFindBy(accessibility = "Next")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/tutorial_next\")")
    private MobileElement nextTutorialBtn;


    public TutorialScanView validateElementsFromTutorialScanView() {
        try {
            MyLogger.log.info("Validating elements from Tutorial Scan View");
            waiters.waitForElementVisibility(tutorialScanLabel);
            assertsUtils.isElementDisplayed(tutorialScanLabel);
            assertsUtils.isElementDisplayed(tutorialScanDescription);
            assertsUtils.isElementDisplayed(skipTutorialBtn);
            assertsUtils.isElementDisplayed(nextTutorialBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Tutorial Scan View");
        }
    }


    public HomeViewSign clickOnSkipTutorialButton() {
        try {
            MyLogger.log.info("Trying to click on skip button to go to Home View");
//            if (tutorialScanLabel.isDisplayed()) {
            waiters.waitForElementVisibility(tutorialScanLabel);
            gestures.clickOnMobileElement(skipTutorialBtn);

        } catch (WebDriverException i) {
//            do nothing
        }
        return new HomeViewSign();
    }


}
