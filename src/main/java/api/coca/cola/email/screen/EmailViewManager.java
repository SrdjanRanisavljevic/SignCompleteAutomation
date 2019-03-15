package api.coca.cola.email.screen;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class EmailViewManager {

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

}
