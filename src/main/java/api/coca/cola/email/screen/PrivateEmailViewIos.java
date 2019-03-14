package api.coca.cola.email.screen;

import api.drivers.Drivers;
import core.classic.methods.Gestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class PrivateEmailViewIos implements EmailView {

    public PrivateEmailViewIos() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Gestures gestures = new Gestures();
    private final Waiters waiters = new Waiters();


    /**
     * PRIVATE EMAIL VIEW ELEMENTS
     */

    @iOSXCUITFindBy(iOSNsPredicate = "type== 'XCUIElementTypeCell' AND name BEGINSWITH[c] 'Unread'")
    private MobileElement receivedEmail;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name CONTAINS[c] 'Inbox'")
    private MobileElement backFromEmailRead;

    @iOSXCUITFindBy(iOSNsPredicate = "type== 'XCUIElementTypeLink' AND name CONTAINS[c] 'PROCEED'")
    private MobileElement proceedFromEmail;


    @Override
    public void openEmail() throws IOException {
        try {
            MyLogger.log.info("Trying to open received e-mail from Magic Link activation");
            if (runningSetup().getPlatformName().equalsIgnoreCase("ios")) {
                try {
                    if (proceedFromEmail.isDisplayed()) {
                        gestures.clickOnMobileElement(backFromEmailRead);
                        Swipe.swipeDown();
                        Swipe.swipeDown();
                        gestures.clickOnMobileElement(receivedEmail);
                    }
                } catch (WebDriverException e) {
                    Swipe.swipeDown();
                    Swipe.swipeDown();
                    gestures.clickOnMobileElement(receivedEmail);
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
            try {
                gestures.clickOnMobileElement(proceedFromEmail);
            } catch (WebDriverException e) {
                gestures.clickOnMobileElement(proceedFromEmail);
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot activate app from received e-mail from Magic Link");
        }
    }
}
