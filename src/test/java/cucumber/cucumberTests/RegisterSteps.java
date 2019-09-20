package cucumber.cucumberTests;

import api.coca.cola.create.account.screen.*;
import api.coca.cola.email.screen.EmailViewManager;
import api.coca.cola.home.screen.HomeViewSign;
import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.profile.screen.settings.screen.CocaColaWebView;
import api.coca.cola.tutorial.screen.TutorialScanView;
import api.coca.cola.utils.workarounds.WorkaroundsPhone;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.getPlatformUnderTest;
import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class RegisterSteps {

    private String name = runningSetup().getName();
    private String prefixEmail = runningSetup().getPrefixEmail();
    private int desiredYearForNormalUser = 2001;
    private int currentYear = 2000;
    private int desiredYearForTooYoungUser = 2010;
    private String usermail = runningSetup().getUsermail();
    private int desiredYearForAMinimumAge = 2004;


    public RegisterSteps() throws FileNotFoundException {

    }

    @Given("^User is in Launcher Screen$")
    public void userIsInLauncherScreen() {
        LauncherView launcherView = new LauncherView();
        launcherView.clickOnDontAllowNotificationBtn()
                .validateElementsLauncherScreem();
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
    public void userClicksOnEMailMeMagicLink() throws FileNotFoundException {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.clickOnEmailMeMagicLink();
    }

    @And("^User clicks Check E-mail from Magic Link$")
    public void userClicksCheckEMailFromMagicLink() throws FileNotFoundException {
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
        HomeViewSign homeScreenView = new HomeViewSign();
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
    public void userIsInCheckEMailScreen() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateElementsAlreadyRegisteredUser();
    }

    @And("^User is recognized as to be already registered$")
    public void userIsRecognizedAsToBeAlreadyRegistered() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateMessageForAlreadyRegisteredUser();
    }

    @When("^User clicks back from E-mail Me Magic Link$")
    public void userClicksBackFromEMailMeMagicLink() {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.clickOnNavigateBackBtn();
    }

    @And("^Validates the name displayed in SetUpProfile screen$")
    public void validatesTheNameDisplayedInSetUpProfileScreen() throws FileNotFoundException {
        SetUpProfileView setUpProfileView = new SetUpProfileView();
        setUpProfileView.getDisplayedName(name);
    }

    @And("^User goes back from display name$")
    public void userGoesBackFromDisplayName() {
        SetUpProfileView setUpProfileView = new SetUpProfileView();
        setUpProfileView.clickOnNavigateBackBtn();
    }

    @And("^Validates the selected special offers from Consents screen$")
    public void validatesTheSelectedSpecialOffersFromConsentsScreen() throws FileNotFoundException {
        ConsentsView consentsView = new ConsentsView();
        consentsView.isPromotionsToggleTicked();
    }

    @And("^User goes back from Consents$")
    public void userGoesBackFromConsents() {
        ConsentsView consentsView = new ConsentsView();
        consentsView.clickOnNavigateBackBtn();
    }

    @And("^User validates the displayed date of birth$")
    public void userValidatesTheDisplayedDateOfBirth() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView.validateDisplayedBirthday(desiredYearForNormalUser);
    }

    @And("^User goes back from birth day screen$")
    public void userGoesBackFromBirthDayScreen() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView.clickOnNavigateBackBtn();
    }

    @And("^Validates that a country is still selected$")
    public void validatesThatACountryIsStillSelected() {
        CountrySelectionView countrySelectionView = new CountrySelectionView();
        BirthdaySelectionView birthdaySelectionView = countrySelectionView.clickOnProceedButton();
        birthdaySelectionView.validateElementsfromBirthdayScreen();
        birthdaySelectionView.clickOnNavigateBackBtn();
    }

    @When("^User goes back from Country Selection screen$")
    public void userGoesBackFromCountrySelectionScreen() {
        CountrySelectionView countrySelectionView = new CountrySelectionView();
        countrySelectionView.clickNavigateBackBtn();
    }

    @Then("^User validates the email displayed in Email Address screen$")
    public void userValidatesTheEmailDisplayedInEmailAddressScreen() throws FileNotFoundException {
        EmailAddressView emailAddressView = new EmailAddressView();
        emailAddressView.getDisplayedEmail(prefixEmail);
    }

    @When("^User clicks on Enter Code$")
    public void userClicksOnEnterCode() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.clickOnEnterCodeBtn();
    }

    @And("^User enters the verification code$")
    public void userEntersTheVerificationCode() throws Exception {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.sendTextVerificationCode();
    }


    @Given("^User is in E-mail Address view$")
    public void userIsInEMailAddressView() {
        EmailAddressView emailAddressView = new EmailAddressView();
        emailAddressView.validateElementsEmailAddressView();
    }

    @And("^Proceeds to Country Selection view$")
    public void proceedsToCountrySelectionView() {
        CountrySelectionView countrySelectionView = new CountrySelectionView();
        countrySelectionView.validateElementsCountrySelectionView();
    }

    @And("^User is in Birthday Selection view$")
    public void userIsInBirthdaySelectionView() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView.validateElementsfromBirthdayScreen();
    }

    @When("^User proceeds to Consents$")
    public void userProceedsToConsents() throws IOException, ParseException {
        ConsentsView consentsView = new ConsentsView();
        consentsView.validateElementsConsentsView()
                .clickOnProceedBtn();
    }

    @And("^User is in Set Up Profile view$")
    public void userIsInSetUpProfileView() {
        SetUpProfileView setUpProfileView = new SetUpProfileView();
        setUpProfileView.validateElementsSetUpProfileView();
    }

    @And("^User proceeds to Get Magic Link view$")
    public void userProceedsToGetMagicLinkView() {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.validateElementsGetMagicLinkView();
    }

    @Then("^User is in Check Email link$")
    public void userIsInCheckEmailLink() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateElementsCheckMagicLinkView();
    }

    @When("^User puts the app in the background$")
    public void userPutsTheAppInTheBackground() throws FileNotFoundException {
        WorkaroundsPhone workarounds = new WorkaroundsPhone();
        workarounds.putAppInBackground(3);
    }

    @When("^User closes the app$")
    public void userClosesTheApp() throws FileNotFoundException {
        WorkaroundsPhone workarounds = new WorkaroundsPhone();
        workarounds.closeApp();
    }

    @Given("^User enters invalid email in the email field$")
    public void userEntersInvalidEmailInTheEmailField(DataTable table) throws FileNotFoundException {
        EmailAddressView emailAddressView = new EmailAddressView();

        List<String> emails = table.asList(String.class);

        for (int i = 0; i <= emails.size() - 1; i++) {
            String email = emails.get(i);
            emailAddressView.sendTextAlreadyRegisteredEmail(email)
                    .clickRegisterEmailForWrongEnteredEmail();
            if (getPlatformUnderTest().getPlatformName().equals("android")) {
                emailAddressView.validateWrongEmailInput();
            } else {
                emailAddressView.validateElementsEmailAddressView();
            }
        }
    }

    @When("^User is in Consents View$")
    public void userIsInConsentsView() {
        ConsentsView consentsView = new ConsentsView();
        consentsView.validateElementsConsentsView();
    }

    @And("^User clicks on Privacy Notice link$")
    public void userClicksOnPrivacyNoticeLink() {
        ConsentsView consentsView = new ConsentsView();
        consentsView.clickOnPrivacyNotice();
    }

    @Then("^User is in Privacy Policy View$")
    public void userIsInPrivacyPolicyView() {
        CocaColaWebView cocaColaWebView = new CocaColaWebView();
        cocaColaWebView.validateElementsFromCocaColaWebView();
    }


    @And("^User is in GetMagicLink View$")
    public void userIsInGetMagicLinkView() {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.validateElementsGetMagicLinkView();
    }

    @And("^User clicks on Privacy Policy link$")
    public void userClicksOnPrivacyPolicyLink() {
        GetMagicLinkView getMagicLinkVie = new GetMagicLinkView();
        getMagicLinkVie.clickOnPrivacyPolicy();
    }

    @But("^User clicks on X button$")
    public void userClicksOnXButton() {
        CocaColaWebView cocaColaWebView = new CocaColaWebView();
        cocaColaWebView.clickOnCloseBtn();
    }

    @When("^User clicks on Terms of Service$")
    public void userClicksOnTermsOfService() {
        GetMagicLinkView getMagicLinkView = new GetMagicLinkView();
        getMagicLinkView.clickOnTermsOfService();
    }

    @Then("^User is in Terms of Service View$")
    public void userIsInTermsOfServiceView() {
        TermsOfService termsOfService = new TermsOfService();
        termsOfService.validateElementsPrivacyInformationView()
                .clickOnCloseBtn();
    }

    @And("^User enters birth date for minimum age$")
    public void userEntersBirthDateForMinimumAge() {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView.selectYear(currentYear, desiredYearForAMinimumAge)
                .clickOnProceedBtn();
    }

    @And("^User clicks on Send Link Again$")
    public void userClicksOnSendLinkAgain() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.clickOnSendLinkAgainBtn();
    }

    @And("^User receives a new magic link notification$")
    public void userReceivesANewMagicLinkNotification() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateElementsFromSendLinkAgainPopUp()
                .clickOnCheckEmailBtnFromSendLinkAgain();
    }

    @Then("^An error message is displayed when unlocking the app from an old received e-mail$")
    public void anErrorMessageIsDisplayedWhenUnlockingTheAppFromAnOldReceivedEMail() throws FileNotFoundException {
        CheckMagicLinkView checkMagicLinkView = new CheckMagicLinkView();
        checkMagicLinkView.validateErrorMessageReceivedFromOldEmail();
    }


    @And("^User selects a \"([^\"]*)\" in the \"([^\"]*)\" field$")
    public void userSelectsAInTheField(String arg0, String arg1) throws Throwable {
        CountrySelectionView countrySelectionView = new CountrySelectionView();
        if (arg1.equals("country")) {
            countrySelectionView.selectDesiredCountry(arg0)
                    .clickOnProceedButton();
        }
    }


    @And("^User enters a \"([^\"]*)\" from \"([^\"]*)\"$")
    public void userEntersAFrom(int arg0, String arg1) {
        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        if (arg1.equals("birth date year")) {
            birthdaySelectionView
                    .selectYear(currentYear, arg0)
                    .clickOnProceedBtn();
        }
    }


    @And("^User validates that the email was received$")
    public void userValidatesThatTheEmailWasReceived() throws Exception {
        EmailViewManager emailView = new EmailViewManager();
        emailView.validateThatAnEmailWasReceived();
    }

    // SRDJAN ADDED STEP DEFINITIONS

    @And("^User selects that he is \"([^\"]*)\" years old - user is in the \"([^\"]*)\"$")
    public void userEntersAFrom(String arg0, String arg1) {
        int parsedYear = Integer.parseInt(arg0);

        BirthdaySelectionView birthdaySelectionView = new BirthdaySelectionView();
        birthdaySelectionView
                .selectDay()
                .selectMonth()
                .selectYearSecondMethod(parsedYear)
                .clickOnProceedBtn();

    }
    @And("^User clicks back from Set Up Profile view$")
    public void userClicksBackFromSetUpProfileView() {
        SetUpProfileView setUpProfileView = new SetUpProfileView();
        setUpProfileView.clickOnNavigateBackBtn();
    }


}

