package cucumber.cucumberTests;

import api.CocaCola.createAccountScreen.CreateAccount;
import api.CocaCola.launcherScreen.LauncherScreen;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class MyStepdefs {
    @Given("^User is in Launcher Screen$")
    public void userIsInLauncherScreen() {
        LauncherScreen launcherScreen = new LauncherScreen();
        launcherScreen.validateElementsLauncherScreem();
    }

    @And("^User selects Create Account$")
    public void userSelectsCreateAccount() {
        LauncherScreen launcherScreen = new LauncherScreen();
        launcherScreen.clickRegisterAccount();
    }

    @And("^User enters a valid e-mail address$")
    public void userEntersAValidEMailAddress() {
        CreateAccount createAccount = new CreateAccount();
        createAccount.validateElementsLauncherScreem()
                .sendTextEmailAddress("test");
    }
}
