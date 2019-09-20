package cucumber.cucumberTests;

import api.coca.cola.SignLauncher.SignLauncherView;
import api.coca.cola.sign.login.Screen.SignLoginView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;

public class SignLauncher {

    @Given("^User is in Sign launcher screen$")
    public void swipe3TimesToGetTheLoginButton() {
        SignLauncherView signLauncherView = new SignLauncherView();
        signLauncherView.SwipeLeftThreeTimes();
    }

    @And("^User clicks on Sign login button")
    public void clickOnLoginButton() throws FileNotFoundException {
        SignLauncherView signLauncherView = new SignLauncherView();
        signLauncherView.clickOnLoginButton();
    }

    @And("^User enters Sign userName")
    public void enterUserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName();
    }

    @And("^User enters Sign Password")
    public void enterPassword () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterPassword();
    }

    @And("^User clicks on Sign Sign in button")
    public void clickOnSignInButton () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.clickOnSignInButton();
    }

    @And ("^Random Waiter for Vesa to see what is happening")
     public void randomSleep () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.randomSleep();
    }


}
