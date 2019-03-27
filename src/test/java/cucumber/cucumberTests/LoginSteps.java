package cucumber.cucumberTests;

import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.login.screen.LoginView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.FileNotFoundException;

public class LoginSteps {

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



}
