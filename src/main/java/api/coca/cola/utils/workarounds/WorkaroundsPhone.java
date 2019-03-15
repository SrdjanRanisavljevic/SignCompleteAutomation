package api.coca.cola.utils.workarounds;

import api.drivers.Drivers;
import core.helpers.MobileHelpers;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.time.Duration;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class WorkaroundsPhone implements  WorkaroundInterface{
    private String bundleId = runningSetup().getBundleId();

    public WorkaroundsPhone() throws FileNotFoundException {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

     // Method for putting the app in the background

    @Override
    public void putAppInBackground(int seconds){
        MyLogger.log.info("trying to put the app in background");
        Drivers.getMobileDriver().runAppInBackground(Duration.ofSeconds((long) seconds));
    }

    // Method for closing the app

    @Override
    public void closeApp() throws FileNotFoundException{
        if (runningSetup().getPlatformName().equals("ios")) {
            MyLogger.log.info("Trying to close the ios app");
            MobileHelpers.closeApp(bundleId);
        } else {
            MyLogger.log.info("Trying to close the Android app");
            Drivers.getMobileDriver().closeApp();

        }
    }

    // Method for launching the app

    @Override
    public void reviveApp() throws FileNotFoundException {
        if (runningSetup().getPlatformName().equals("ios")) {
            MyLogger.log.info("Trying to open the ios app");
            MobileHelpers.launchApp(bundleId);

        } else {
            MyLogger.log.info("Trying to open the Android app");
            Drivers.getMobileDriver().activateApp("com.cocacola.app.cee.dev");
        }
    }
}
