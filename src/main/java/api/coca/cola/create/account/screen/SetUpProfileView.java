package api.coca.cola.create.account.screen;

import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
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

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;

public class SetUpProfileView extends ScreenView {

    public SetUpProfileView() {
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

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'SET UP PROFILE'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement setUpProfilePageTitle;


    /**
     * DISPLAY NAME ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "Display name")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/displayNameSubtitle\")")
    private MobileElement displayNameLabel;

    @iOSXCUITFindBy(accessibility = "Your display name is going to be visible to other users")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/displayNameText\")")
    private MobileElement displayNameDescription;

    @iOSXCUITFindBy(accessibility = "LoginInputTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/auto_generated_edit_text\")")
    private MobileElement displayNameInput;

    @iOSXCUITFindBy(accessibility = "LoginInputConfirmButton")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/next_lottie_button\")")
    private MobileElement proceedBtn;


    public SetUpProfileView validateElementsSetUpProfileView() {
        try {
            MyLogger.log.info("Validating elements from Set Up Profile View");
            waiters.waitForElementVisibility(setUpProfilePageTitle);
            assertsUtils.isElementDisplayed(setUpProfilePageTitle);
            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(displayNameLabel);
            assertsUtils.isElementDisplayed(displayNameDescription);
            assertsUtils.isElementDisplayed(displayNameInput);
            assertsUtils.isElementDisplayed(proceedBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Set Up Profile View");
        }
    }


    public SetUpProfileView sendTextDisplayName(String name) {
        try {
            MyLogger.log.info("Trying to send text: " + name + " to display name input");
            gestures.sendText(displayNameInput, name);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot send text: " + name + " to display name input");
        }
    }


    public GetMagicLinkView clickOnProceedButton() {
        MyLogger.log.info("Trying to click on proceed button to move to Get The Magic Link View");
        ScreenView screenView = utilView.clickOnProceedButton(new GetMagicLinkView(), gestures, proceedBtn);
        return (GetMagicLinkView) screenView;
    }


    public SetUpProfileView getDisplayedName(String name) throws FileNotFoundException {
        try {
            MyLogger.log.info("Trying to get the displayed input name ");
            waiters.waitForElementVisibility(backBtn);
            if (getPlatformUnderTest().getPlatformName().equals("ios")) {
                Assert.assertTrue("The displayed name does not coincide with the expected name ", displayNameInput.getAttribute("value").equals(name));
            } else {
                Assert.assertTrue("The displayed name does not coincide with the expected name ", displayNameInput.getAttribute("name").equals(name));
            }
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate if the displayed name coincide with the expected name");
        }
    }


    public ConsentsView clickOnNavigateBackBtn() {
        MyLogger.log.info("Trying to click on navigate back button to move to Privacy Information View");
        ScreenView screenView = utilView.clickOnNavigateBackBtn(new ConsentsView(), gestures, backBtn);
        return (ConsentsView) screenView;
    }





}
