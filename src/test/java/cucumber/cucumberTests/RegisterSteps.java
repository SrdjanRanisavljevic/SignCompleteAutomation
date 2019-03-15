package cucumber.cucumberTests;

import api.coca.cola.create.account.screen.*;
import api.coca.cola.home.screen.HomeView;
import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.tutorial.screen.TutorialScanView;
import api.coca.cola.utils.workarounds.WorkaroundsPhone;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class RegisterSteps {

    private String name = runningSetup().getName();
    private String prefixEmail = runningSetup().getPrefixEmail();
    private int desiredYearForNormalUser = 2001;
    private int currentYear = 2000;
    private int desiredYearForTooYoungUser = 2010;
    private String usermail = runningSetup().getUsermail();


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

    @And("^User \"([^\"]*)\"$")
    public void user(String arg1) throws Throwable {
        WorkaroundsPhone workaroundsPhone = new WorkaroundsPhone();
        if (arg1.equals("puts the app in the background")) {
            workaroundsPhone.putAppInBackground(5);
        } else {
            workaroundsPhone.closeApp();
        }

    }


    @And("^User open the app$")
    public void userOpenTheApp() throws FileNotFoundException {
        WorkaroundsPhone workaroundsPhone = new WorkaroundsPhone();
        workaroundsPhone.reviveApp();
    }

    @Then("^User is in \"([^\"]*)\"$")
    public void userIsIn(String arg0) {
        HomeView homeScreenView = new HomeView();
        if (arg0.equalsIgnoreCase("Home Screen")) {
            homeScreenView.validateElementsFromHomeView();
        }
    }

    @And("^User enters a birth date from a recent date$")
    public void userEntersABirthDateFromARecentDate() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView
                .selectYear(currentYear, desiredYearForTooYoungUser)
                .clickOnProceedBtn();
    }

    @Then("^User is notified that he is too young to register$")
    public void userIsNotifiedThatHeIsTooYoungToRegister() throws FileNotFoundException {
        TooYoungToJoinView tooYoungToJoinView = new TooYoungToJoinView();
        tooYoungToJoinView.validateElementsFromTooYoungToJoinScreen();
    }

    @Given("^User enters an already registered e-mail address$")
    public void userEntersAnAlreadyRegisteredEMailAddress() {
        EmailAddressView emailAddressView = new EmailAddressView();
        emailAddressView.sendTextAlreadyRegisteredEmail(usermail).clickOnProceedButton();

    }


    @Then("^User is in Check E-mail Screen$")
    public void userIsInCheckEMailScreen() {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateElementsAlreadyRegisteredUser();
    }


    @And("^User is recognized as to be already registered$")
    public void userIsRecognizedAsToBeAlreadyRegistered() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateMessageForAlreadyRegisteredUser();
    }





}
