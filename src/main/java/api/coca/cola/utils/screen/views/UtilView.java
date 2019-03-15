package api.coca.cola.utils.screen.views;

import api.coca.cola.utils.screen.views.ScreenView;
import core.classic.methods.Gestures;
import core.watchers.MyLogger;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriverException;

public class UtilView {

    public ScreenView clickOnProceedButton(ScreenView screenView, Gestures gestures, MobileElement proceedBtn) {
        try {
            MyLogger.log.info("Trying to click on proceed button");
            gestures.clickOnMobileElement(proceedBtn);
            return screenView;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on proceed button");
        }
    }

    public ScreenView clickOnNavigateBackBtn(ScreenView screenView, Gestures gestures, MobileElement backBtn) {
        try {
            MyLogger.log.info("Trying to click on navigate back button");
            gestures.clickOnMobileElement(backBtn);
            return screenView;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on navigate back button");
        }
    }

}
