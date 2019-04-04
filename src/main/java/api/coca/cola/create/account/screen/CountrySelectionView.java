package api.coca.cola.create.account.screen;

import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.Swipe;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class CountrySelectionView extends ScreenView {

    public CountrySelectionView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private final Waiters waiters = new Waiters();
    private final AssertsUtils assertsUtils = new AssertsUtils();
    private final Gestures gestures = new Gestures();
    UtilView utilView = new UtilView();

    /**
     * TOP BAR ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "chevronRight")
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Navigate up\")")
    private MobileElement backBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeNavigationBar' AND name MATCHES[c] 'CREATE ACCOUNT'")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/title\")")
    private MobileElement registerPageTitle;

    /**
     * COUNTRY VIEW ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "Where are you from?")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/header\")")
    private MobileElement countryLabel;

    @iOSXCUITFindBy(accessibility = "Please select your country")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/sub_header\")")
    private MobileElement countryDescription;

    @iOSXCUITFindBy(accessibility = "Switzerland")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Switzerland\")")
    private MobileElement countrySelect;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/following-sibling::XCUIElementTypeOther[1]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/next_lottie_button\")")
    private MobileElement proceedBtn;

    //    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/country_list\")")
    private MobileElement countryList;


    public CountrySelectionView validateElementsCountrySelectionView() {
        try {
            MyLogger.log.info("Validating elements from Country Selection View");
            waiters.waitForElementVisibility(registerPageTitle);
            assertsUtils.isElementDisplayed(registerPageTitle);
//            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(countryLabel);
            assertsUtils.isElementDisplayed(countryDescription);
            assertsUtils.isElementDisplayed(countrySelect);
            assertsUtils.isElementDisplayed(proceedBtn);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Country Selection View");
        }
    }


    public CountrySelectionView chooseCountry() {
        try {
            MyLogger.log.info("Trying to click on preferred country");
            gestures.clickOnMobileElement(countrySelect);
            return new CountrySelectionView();
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot click on preferred country");
        }
    }


    public BirthdaySelectionView clickOnProceedButton() {
        MyLogger.log.info("Trying to click on proceed button to move to Birthday Selection View");
        ScreenView screenView = utilView.clickOnProceedButton(new BirthdaySelectionView(), gestures, proceedBtn);
        return (BirthdaySelectionView) screenView;
    }


    public EmailAddressView clickNavigateBackBtn() {
        MyLogger.log.info("Trying to click on navigate back button to move to Email Address View");
        ScreenView screenView = utilView.clickOnNavigateBackBtn(new EmailAddressView(), gestures, backBtn);
        return (EmailAddressView) screenView;
    }


    public CountrySelectionView selectDesiredCountry(String preferredCountry) throws IOException {

        MyLogger.log.info("Trying to select the desired country: " + preferredCountry);
        waiters.waitForElementVisibility(countryLabel);

        if (runningSetup().getPlatformName().equals("android")) {

            try {

                MobileElement countryAOS = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/country_list\").childSelector(new UiSelector().text(\"" + preferredCountry + "\"))"));
                isCountryDisplayed(countryAOS, countryLabel);
                gestures.clickOnMobileElement((MobileElement) Drivers.getMobileDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/country_list\").childSelector(new UiSelector().text(\"" + preferredCountry + "\"))")));

            } catch (WebDriverException e) {

                Swipe.swipeUpPress();
                waiters.waitForElementVisibility(countryLabel);
                gestures.clickOnMobileElement((MobileElement) Drivers.getMobileDriver().findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/country_list\").childSelector(new UiSelector().text(\"" + preferredCountry + "\"))")));
            }


        } else {

            MobileElement countryIOS = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] '" + preferredCountry + "'"));
            isCountryDisplayed(countryIOS, countryLabel);
            gestures.clickOnMobileElement((MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] '" + preferredCountry + "'")));

        }

        return this;
    }


    public void isCountryDisplayed(MobileElement element1, MobileElement element2) throws IOException {

        if (element1.isDisplayed()) {
            element1.click();
        } else {
            Swipe.swipeUpPress();
            waiters.waitForElementVisibility(element2);
        }
    }


}
