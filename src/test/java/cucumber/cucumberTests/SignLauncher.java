package cucumber.cucumberTests;

import api.sign.home.screen.HomeScreenView;
import api.sign.launcher.SignLauncherView;
import api.sign.login.Screen.SignLoginView;
import api.sign.settings.screen.SettingsView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.FileNotFoundException;

public class SignLauncher {

    @Given("^User is in Sign launcher screenS10$")
    public void swipe3TimesToGetTheLoginButtonS10() {
        SignLauncherView signLauncherView = new SignLauncherView();
        signLauncherView.swipeLeftThreeTimes();
    }

    @Given("^User is in Sign launcher screen$")
    public void swipe3TimesToGetTheLoginButton() {
        SignLauncherView signLauncherView = new SignLauncherView();
        signLauncherView.swipeLeftThreeTimes();
    }

    @And("^User clicks on Sign login button")
    public void clickOnLoginButton() throws FileNotFoundException {
        SignLauncherView signLauncherView = new SignLauncherView();
        signLauncherView.clickOnLoginButton();
    }

    @Given("^Sign in with production user and change server to Stage")
    public void changeServerToStage() throws FileNotFoundException {
        SignLauncherView signLauncherView = new SignLauncherView();

        signLauncherView
                .swipeLeftThreeTimes()
                .clickOnLoginButton();

        SignLoginView signLoginView = new SignLoginView();
        signLoginView
                .enterUserName(signLoginView.prodUser)
                .enterPassword();
                signLoginView.clickOnSignInButton();

        HomeScreenView homeScreenView = new HomeScreenView();
        homeScreenView.clickOnSettingsButton();

        SettingsView settingsView = new SettingsView();
        settingsView.clickOnAboutButton();
        settingsView.clickOnVersionButton();
        settingsView.clickYesOrOk();
        settingsView.chooseStageServer();
        settingsView.clickYesOrOk();


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

  // D I F F E R E N T      S H A R D S


    @And ("^User signs with NA1 user")
    public void enterNa1UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.na1UserName);
    }


    // NA 2

    @And ("^User signs with NA2 user")
    public void enterNa2UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.na2UserName);
    }

    @And ("^User signs with NA2 user B")
    public void enterNa2UserNameB () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.na2UserNameB);
    }

    // NA 3

    @And ("^User signs with NA3 user")
    public void enterNa3UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.na3UserName);
    }

    @And ("^User signs with NA3 user B")
    public void enterNa3UserNameB () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.na3UserNameB);
    }



    // E U 1

    @And ("^User signs with EU1 user")
    public void enterEu1UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.eu1UserName);
    }

    @And ("^User signs with EU1 user B")
    public void enteraEu1UserNameB () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.eu1UserNameB);
    }

// EU 2

    @And ("^User signs with EU2 user")
    public void enterEu2UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.eu2UserName);
    }

    @And ("^User signs with EU2 user B")
    public void enterEu2UserNameB () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.eu2UserNameB);
    }

// JP 1     AU 1     IN 1


    @And ("^User signs with JP1 user")
    public void enterJp1UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.jp1UserName);
    }

    @And ("^User signs with AU1 user")
    public void enteraAu1UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.au1UserName);
    }

    @And ("^User signs with IN1 user")
    public void enteraIn1UserName () throws FileNotFoundException {
        SignLoginView signLoginView = new SignLoginView();
        signLoginView.enterUserName(signLoginView.in1UserName);
    }








}
