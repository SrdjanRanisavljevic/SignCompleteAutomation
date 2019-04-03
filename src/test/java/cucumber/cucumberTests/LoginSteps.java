package cucumber.cucumberTests;

import api.coca.cola.create.account.screen.EmailAddressView;
import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.login.screen.LoginView;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileNotFoundException;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class LoginSteps {
    private String unregisteredEmail = runningSetup().getUnregisteredEmail();
    private String usermail = runningSetup().getUsermail();

    public LoginSteps() throws FileNotFoundException {
    }

    @And("^User selects Log In$")
    public void userSelectsLogIn() {
        LauncherView launcherView = new LauncherView();
        launcherView.clickLoginCategory();
    }

    @Given("^The elements from Login screen are displayed$")
    public void theElementsFromLoginScreenAreDisplayed() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.validateElementsLoginScreen();
    }


    @Given("^User enters a registered e-mail address$")
    public void userEntersARegisteredEMailAddress() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.sendTextEmailAddressUsingJson();
    }

    @And("^User clicks on proceed with Login$")
    public void userClicksOnProceedWithLogin() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.clickProceedWithLogin();
    }

    @When("^User clicks on try again$")
    public void userClicksOnTryAgain() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.clickOnTryAgainBtn();
    }

    @Then("^User is able to retype the e-mail address$")
    public void userIsAbleToRetypeTheEMailAddress() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.sendTextEmailAddressUsingJson();
    }

    @Given("^User enters an unregistered e-mail address$")
    public void userEntersAnUnregisteredEMailAddress() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.sendTextEmailAddressUsingString(unregisteredEmail);
    }

    @Then("^Wrong email message is displayed to the user$")
    public void wrongEmailMessageIsDisplayedToTheUser() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.validateWrongEmailNotification();
    }

    @When("^User clicks on Register button from from Pop-up message$")
    public void userClicksOnRegister() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.clickOnRegisterBtn();
    }

    @Then("^User is in E-mail Address screen from Create Account$")
    public void userIsInEMailAddressScreenFromCreateAccount() {
        EmailAddressView emailAddressView = new EmailAddressView();
        emailAddressView.validateElementsEmailAddressView();
    }

    @Then("^User opens the app and checks the email address is kept$")
    public void userOpensTheAppAndChecksTheEmailAddressIsKept() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.dataKeptOnBackgroundInteraction(usermail);
    }

    @Then("^User checks the email address to not be kept$")
    public void userChecksTheEmailAddressToNotBeKept() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.dataNotKeptOnExitFromLogin(usermail);
    }

    @Given("^User enters an invalid email and cannot proceed further$")
        public void userEntersAnInvalidEmailInTheEmailField(DataTable table) throws FileNotFoundException {
            LoginView loginView = new LoginView();
            List<String> emails = table.asList(String.class);

            for (int i = 0; i <= emails.size() - 1; i++) {
                String email = emails.get(i);
                loginView.sendTextEmailAddressUsingString(email).clickOnLoginButtonForIncorrectEmailFormat();
                if (getPlatformUnderTest().getPlatformName().equalsIgnoreCase("android")) {
                    loginView.verifyInvalidEmailAddressError();
                } else {
                    loginView.validateElementsLoginScreen();
                }

            }
    }
}

