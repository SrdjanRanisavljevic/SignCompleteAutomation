package api.coca.cola.create.account.screen;

import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class TooYoungToJoinView {
    public TooYoungToJoinView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();


    /**
     * TooYoungToJoinView ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "WelcomeScreenLogo")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.ImageView[1]")
    private MobileElement logo;

    @iOSXCUITFindBy(accessibility = "Ohh, we’re sorry…")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Ohh, we’re sorry…\")")
    private MobileElement sorryMessageLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'You are too young to join.'")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement sorryMessageFullText;

    @iOSXCUITFindBy(accessibility = "Until then, have fun!")
    private MobileElement haveFunLabel;

    public TooYoungToJoinView validateElementsFromTooYoungToJoinScreen() throws FileNotFoundException {
        try {
            MyLogger.log.info("Validating elements from Too Young To Join View ");
            if (runningSetup().getPlatformName().equals("ios")) {
                waiters.waitForElementVisibility(sorryMessageLabel);
                assertsUtils.isElementDisplayed(sorryMessageLabel);
                assertsUtils.isElementDisplayed(sorryMessageFullText);
                assertsUtils.isElementDisplayed(haveFunLabel);
                assertsUtils.isElementEnabled(logo);
            } else {
                waiters.waitForElementVisibility(sorryMessageLabel);
                assertsUtils.isElementDisplayed(sorryMessageLabel);
                assertsUtils.isElementDisplayed(sorryMessageFullText);
                assertsUtils.isElementEnabled(logo);
            }
            return this;
        } catch (
                WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Too Young To Join View");
        }
    }
}
