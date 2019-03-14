package api.coca.cola.email.screen;

import api.drivers.Drivers;
import core.classic.methods.Gestures;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;


import java.io.FileNotFoundException;
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class EmailViewPixel extends PrivateEmailViewAos implements EmailView {

    public EmailViewPixel() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final Gestures gestures = new Gestures();

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Open navigation drawer\")")
    private MobileElement navigationDrawer;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[5]/android.widget.LinearLayout/android.widget.TextView[1]")
    private MobileElement promotionsLabel;


    /**
     * PRIVATE EMAIL VIEW ELEMENTS
     */

    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView/android.view.View[1]")
    private MobileElement receivedEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Navigate up\")")
    private MobileElement backFromEmailRead;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"PROCEED\")")
    private MobileElement proceedFromEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Show quoted text\")")
    private MobileElement expandTextFromEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Open with\")")
    private MobileElement openAppLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Open with Coca-Cola-DEV\")")
    private MobileElement openAppWithCocaCola;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button_once\")")
    private MobileElement justOnceBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button_always\")")
    private MobileElement alwaysBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Coca-Cola-DEV\")")
    private MobileElement openCocaColaDev;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gm:id/subject_and_folder_view\")")
    private MobileElement magicLinkEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\")")
    private MobileElement emailList;


    @Override
    public void openEmail() throws IOException, ParseException {
        try {
            MyLogger.log.info("Trying to open received e-mail from Magic Link activation");
            if (runningSetup().getPlatformName().equalsIgnoreCase("android")) {

                try {
                    if (backFromEmailRead.isDisplayed()) {
                        gestures.clickOnMobileElement(backFromEmailRead);
                        navigationDrawer();
                        expandTextFromEmail();
                    }
                } catch (WebDriverException e) {
                    navigationDrawer();
                    expandTextFromEmail();
                }

            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot open received e-mail from Magic Link activation");
        }
    }


    @Override
    public void expandTextFromEmail() throws IOException, ParseException {
        super.expandTextFromEmail();
    }


    @Override
    public void navigationDrawer() throws FileNotFoundException {
        super.navigationDrawer();
    }


    @Override
    public void clickActivateAppFromEmail() {

        try {
            MyLogger.log.info("Trying to activate app from received e-mail from Magic Link");
            gestures.clickOnMobileElement(proceedFromEmail);
            try {
                if (openAppWithCocaCola.isDisplayed()) {
                    gestures.clickOnMobileElement(alwaysBtn);
                }

            } catch (WebDriverException e) {
//                do nothing
            }

        } catch (
                WebDriverException e) {
            throw new AssertionError("Cannot activate app from received e-mail from Magic Link");
        }
    }


}
