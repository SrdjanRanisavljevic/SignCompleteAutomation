package api.coca.cola.email.screen;

import api.drivers.Drivers;
import core.classic.methods.Gestures;
import core.classic.methods.MobileGestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class PrivateEmailViewAos implements EmailView {

    public PrivateEmailViewAos() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final Gestures gestures = new Gestures();


    /**
     * TOP BAR ELEMENTS
     */

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Open navigation drawer\")")
    private MobileElement navigationDrawer;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\").childSelector(new UiSelector().text(\"Promotions\"))")
    private MobileElement promotionsLabel;


    /**
     * PRIVATE EMAIL VIEW ELEMENTS
     */


    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView/android.view.View[1]")
    private MobileElement receivedEmail;

    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView/android.view.View[2]")
    private MobileElement oldReceivedEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Navigate up\")")
    private MobileElement backFromEmailRead;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"PROCEED\")")
    private MobileElement proceedFromEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Show quoted text\")")
    private MobileElement expandTextFromEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Open with\")")
    private MobileElement openAppLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Complete action using\")")
    private MobileElement openAppLabelV2;

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

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gm:id/conversation_list_folder_name\")")
    private MobileElement promotionsText;


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
    public void clickActivateAppFromEmail() {
        try {
            MyLogger.log.info("Trying to activate app from received e-mail from Magic Link");
            gestures.clickOnMobileElement(proceedFromEmail);
            try {
                if (openAppLabel.isDisplayed()) {
                    gestures.clickOnMobileElement(openCocaColaDev);
                    try {
                        if (alwaysBtn.isDisplayed()) {
                            gestures.clickOnMobileElement(alwaysBtn);
                        }
                    } catch (WebDriverException e) {
////                        do nothing
                    }
//
//                }

                }
            } catch (WebDriverException e) {
                if (openAppLabelV2.isDisplayed()) {
                    gestures.clickOnMobileElement(openCocaColaDev);
//                    if (alwaysBtn.isDisplayed()) {
//                        gestures.clickOnMobileElement(alwaysBtn);
//                    }
                }
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot activate app from received e-mail from Magic Link");
        }
    }


    public void navigationDrawer() throws FileNotFoundException {

        MyLogger.log.info("Trying to click on navigation drawer");
        try {
            if (emailList.isDisplayed()) {
                MobileGestures.tapOutsideTheEmailListBox(emailList);
                gestures.clickOnMobileElement(navigationDrawer);
                gestures.clickOnMobileElement(promotionsLabel);
                waiters.waitForElementVisibility(promotionsText);
                Swipe.swipeDown();
                Swipe.swipeDown();
                gestures.clickOnMobileElement(receivedEmail);
            } else {
                gestures.clickOnMobileElement(navigationDrawer);
                gestures.clickOnMobileElement(promotionsLabel);
                waiters.waitForElementVisibility(promotionsText);
                Swipe.swipeDown();
                Swipe.swipeDown();
                gestures.clickOnMobileElement(receivedEmail);

            }
        } catch (WebDriverException i) {
            gestures.clickOnMobileElement(navigationDrawer);
            gestures.clickOnMobileElement(promotionsLabel);
            waiters.waitForElementVisibility(promotionsText);
            Swipe.swipeDown();
            Swipe.swipeDown();
            gestures.clickOnMobileElement(receivedEmail);

        }
    }


    public void expandTextFromEmail() throws IOException, ParseException {
        try {
            MyLogger.log.info("Trying to expand text from Received email");
            if (expandTextFromEmail.isDisplayed()) {
                gestures.clickOnMobileElement(expandTextFromEmail);
                waiters.waitForElementVisibility(magicLinkEmail);
                Swipe.swipeUp();
            }

        } catch (WebDriverException e) {
            waiters.waitForElementVisibility(magicLinkEmail);
            Swipe.swipeUp();
        }
    }

    @Override
    public void openOldEmail() throws IOException, ParseException {
        try {
            MyLogger.log.info("Trying to open received e-mail from Magic Link activation");
            if (runningSetup().getPlatformName().equalsIgnoreCase("android")) {

                try {
                    if (backFromEmailRead.isDisplayed()) {
                        gestures.clickOnMobileElement(backFromEmailRead);
                        navigationDrawerOldEmail();
                        expandTextFromEmail();
                    }
                } catch (WebDriverException e) {
                    navigationDrawerOldEmail();
                    expandTextFromEmail();
                }

            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot open received e-mail from Magic Link activation");
        }
    }

    public void navigationDrawerOldEmail() throws IOException {
        MyLogger.log.info("Trying to click on navigation drawer");
        try {
            if (emailList.isDisplayed()) {
                MobileGestures.tapOutsideTheEmailListBox(emailList);
                gestures.clickOnMobileElement(navigationDrawer);
                gestures.clickOnMobileElement(promotionsLabel);
                waiters.waitForElementVisibility(promotionsText);
                Swipe.swipeDown();
                Swipe.swipeDown();
                gestures.clickOnMobileElement(oldReceivedEmail);
            } else {
                gestures.clickOnMobileElement(navigationDrawer);
                gestures.clickOnMobileElement(promotionsLabel);
                waiters.waitForElementVisibility(promotionsText);
                Swipe.swipeDown();
                Swipe.swipeDown();
                gestures.clickOnMobileElement(oldReceivedEmail);

            }
        } catch (WebDriverException i) {
            gestures.clickOnMobileElement(navigationDrawer);
            gestures.clickOnMobileElement(promotionsLabel);
            waiters.waitForElementVisibility(promotionsText);
            Swipe.swipeDown();
            Swipe.swipeDown();
            gestures.clickOnMobileElement(oldReceivedEmail);

        }
    }
}
