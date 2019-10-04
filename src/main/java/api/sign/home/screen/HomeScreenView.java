package api.sign.home.screen;
import api.drivers.Drivers;
import api.sign.launcher.SignLauncherView;
import api.sign.send.screen.SendScreenView;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import api.sign.send.screen.SendScreenView;

public class HomeScreenView {

    public HomeScreenView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();
    private  final Swipe swipe = new Swipe();

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/waitingForYouText\")")
    private MobileElement waitingForYou;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/waiting_for_others_option\")")
    private MobileElement waitingForOthers;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/send_for_signature_option\")")
    private MobileElement sendForSignature;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/in_person_signing_option\")")
    private MobileElement inPersonSigning;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/completed_option\")")
    private MobileElement completed;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/cancelled_option\")")
    private MobileElement canceled;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/action_settings\")")
    private MobileElement settingsButton;



    public HomeScreenView clickSendForSignature() {
        try {
            MyLogger.log.info("Clicking on send for signature");
            gestures.clickOnMobileElement(sendForSignature);
            return this;
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on SEND FOR SIGNATURE button");
        }
    }

    public HomeScreenView clickOnSettingsButton() {
        try {
            MyLogger.log.info("Clicking on settings button");
            gestures.clickOnMobileElement(settingsButton);
            return this;
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on settings button");
        }
    }






}
