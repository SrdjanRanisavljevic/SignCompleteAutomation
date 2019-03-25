package cucumber.cucumberTests;

import api.coca.cola.email.screen.EmailViewManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PrivateEmailSteps {

    @Then("^User unlocks the app from received e-mail$")
    public void userUnlocksTheAppFromReceivedEMail() throws IOException, ParseException {
        EmailViewManager emailViewManager=new EmailViewManager();
        emailViewManager.openReceivedEmail();
        emailViewManager.clickProceedFromEmail();
    }

    @When("^User tries to unlock the app from an old received e-mail$")
    public void userTriesToUnlockTheAppFromAnOldReceivedEMail() throws IOException, ParseException {
        EmailViewManager emailViewManager=new EmailViewManager();
        emailViewManager.openOldReceivedEmail();
        emailViewManager.clickProceedFromEmail();
    }
}
