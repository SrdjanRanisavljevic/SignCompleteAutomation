package api.coca.cola.email.screen;

import core.watchers.MyLogger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class EmailViewManager {
    private final CheckingMails cm = new CheckingMails();

    public EmailViewManager() throws FileNotFoundException {
    }


    public EmailView getOs() throws FileNotFoundException {

        EmailView emailView = null;

//        if (runningSetup().getPlatformName().equalsIgnoreCase("android") && (runningSetup().getDeviceID().equalsIgnoreCase("HT8281A03687//"))) {
//            emailView = new EmailViewPixel();
//        } else

        if ((runningSetup().getPlatformName().equalsIgnoreCase("android"))) {
            emailView = new PrivateEmailViewAos();
        } else if (runningSetup().getPlatformName().equalsIgnoreCase("ios")) {
            emailView = new PrivateEmailViewIos();
        }
        return emailView;
    }

    public void openReceivedEmail() throws IOException, ParseException {
        getOs().openEmail();
    }

    public void clickProceedFromEmail() throws FileNotFoundException {
        getOs().clickActivateAppFromEmail();
    }

    public void openOldReceivedEmail() throws IOException, ParseException {
        getOs().openOldEmail();
    }

    public void validateThatAnEmailWasReceived() throws Exception {
        try {
            MyLogger.log.info("Trying to check if the email was received");

            if (cm.returnStatusOfEmail()) {
            } else {
                MyLogger.log.info("No new mail from ");

            }
        } catch (WebDriverException i) {
            throw new AssertionError("Could not check if the email was received");
        }

    }

}
