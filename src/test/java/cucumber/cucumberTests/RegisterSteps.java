package cucumber.cucumberTests;

import api.coca.cola.create.account.screen.*;
import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.tutorial.screen.TutorialScanView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class RegisterSteps {

    private String name = runningSetup().getName();
    private String prefixEmail = "tccc.johndoe+";
    private int desiredYearForNormalUser = 1999;
    private int currentYear = 2000;

    public RegisterSteps() throws FileNotFoundException {

    }

    @Given("^User is in Launcher Screen$")
    public void userIsInLauncherScreen() {
        LauncherView launcherView = new LauncherView();
        launcherView.validateElementsLauncherScreem();
    }

    @And("^User selects Create Account$")
    public void userSelectsCreateAccount() {
        LauncherView launcherView = new LauncherView();
        launcherView.clickRegisterAccount();
    }

    @And("^User enters a valid e-mail address$")
    public void userEntersAValidEMailAddress() {
        EmailAddressView emailAddressView = new EmailAddressView();
        emailAddressView.sendTextEmailAddress(prefixEmail)
                .clickOnProceedButton();
    }

    @And("^User selects a country from the list$")
    public void userSelectACountryFromTheList() {
        CountrySelectionView countrySelectionView = new CountrySelectionView();
        countrySelectionView.chooseCountry()
                .clickOnProceedButton();
    }

    @And("^User enters birth date$")
    public void userEntersBirthDate() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView.
                selectDecember().
                selectYear(currentYear, desiredYearForNormalUser)
                .clickOnProceedBtn();
    }

        @And("^User proceeds with Consents$")
    public void userProceedsWithConsents() throws IOException, org.json.simple.parser.ParseException {
        ConsentsView consentsView = new ConsentsView();
        consentsView.tickThePromotionsToggle()
                .clickOnProceedBtn();
    }

    @And("^User enters a display name$")
    public void userEntersADisplayName() {
        SetUpProfileView setUpProfileView = new SetUpProfileView();
        setUpProfileView.sendTextDisplayName(name)
                .clickOnProceedButton();
    }

    @When("^User clicks on E-mail Me Magic Link$")
    public void userClicksOnEMailMeMagicLink() {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.clickOnEmailMeMagicLink();
    }

    @And("^User clicks Check E-mail from Magic Link$")
    public void userClicksCheckEMailFromMagicLink() {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.clickOnCheckEmailBtn();
    }

    @And("^User skips tutorial$")
    public void userSkipsTutorial() {
        TutorialScanView tutorialScanView = new TutorialScanView();
        tutorialScanView.clickOnSkipTutorialButton();
    }

}
