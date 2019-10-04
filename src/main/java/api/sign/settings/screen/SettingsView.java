package api.sign.settings.screen;

import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class SettingsView {


    public SettingsView() throws FileNotFoundException {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private String usermail = runningSetup().getUsermail();
    private String userPassword = runningSetup().getUserpassword();

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/ae_grey_arrow\")")
    private MobileElement aboutButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.adobe.echosign:id/rl_about_version\")")
    private MobileElement versionButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
    private MobileElement yesOKButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
    private List<MobileElement> listaServera;
    private MobileElement stageServer = listaServera.get(3);


    public void clickOnAboutButton() {
        try {
            MyLogger.log.info("Clicking on about button");
            waiters.waitForElementVisibility(aboutButton);
            gestures.clickOnMobileElement(aboutButton);
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on about button");
        }
    }

    public void clickOnVersionButton() {
        try {
            MyLogger.log.info("Clicking on version button");
            waiters.waitForElementVisibility(versionButton);
            gestures.clickOnMobileElement(versionButton);
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on version button");
        }
    }

    public void clickYesOrOk() {
        try {
            MyLogger.log.info("Clicking on yes button on set server dialog");
            gestures.clickOnMobileElement(yesOKButton);
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on yes button on set server dialog");
        }
    }

    public void chooseStageServer() {
        try {
            MyLogger.log.info("Trying to click on STAGE SERVER radio button");
            gestures.clickOnMobileElement(stageServer);
        }catch (WebDriverException e) {
            throw new AssertionError("Cannot click on stage server radio button");
        }
    }




}
