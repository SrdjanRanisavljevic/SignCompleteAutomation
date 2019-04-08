package api.coca.cola.create.account.screen;

import api.coca.cola.utils.screen.views.ScreenView;
import api.coca.cola.utils.screen.views.UtilView;
import api.drivers.Drivers;
import core.classic.methods.AssertsUtils;
import core.classic.methods.Gestures;
import core.classic.methods.MobileGestures;
import core.classic.methods.Waiters;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class BirthdaySelectionView extends ScreenView {

    public BirthdaySelectionView() {
        AppiumDriver driver = Drivers.getMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    private final Date date = new Date();
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
     * DATE OF BIRTH ELEMENTS
     */

    @iOSXCUITFindBy(accessibility = "Date of Birth")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Date of Birth\")")
    private MobileElement birthdayLabel;

    @iOSXCUITFindBy(accessibility = "Please select your date of birth")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Please select your date of birth\")")
    private MobileElement dateOfBirthDescription;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/send_magic_link_text\")")
    private MobileElement dateOfBirthInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Please')]/following-sibling::XCUIElementTypeOther[1]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cocacola.app.cee.dev:id/next_lottie_button\")")
    private MobileElement proceedButton;


    /**
     * DATE OF BIRTH PICKER WHEEL ELEMENTS
     */

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePickerWheel' AND name CONTAINS[c] '1.'")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[1]/android.widget.EditText")
    private MobileElement dayInput;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePickerWheel' AND value CONTAINS[c] 'January'")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[2]/android.widget.EditText")
    private MobileElement januaryMonth;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[2]")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[2]")
    private MobileElement selectMonth;


    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePickerWheel' AND name CONTAINS[c] '2000'")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[3]/android.widget.EditText")
    private MobileElement yearInput;

    @AndroidFindBy(xpath = "//android.widget.NumberPicker[3]")
    private MobileElement yearInputForTooYoungUser;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"2000\")")
    private MobileElement yearInputForTooYoungUser2;


    // SRDJAN ADDED SELECTORS
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePickerWheel' AND name CONTAINS[c] '1.'")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[1]")
    private MobileElement dayInputSrdjan;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePickerWheel' AND name CONTAINS[c] '2000'")
    @AndroidFindBy(xpath = "//android.widget.NumberPicker[3]")
    private MobileElement yearInputSrdjan;



    public BirthdaySelectionView validateElementsfromBirthdayScreen() {
        try {
            MyLogger.log.info("Validating elements from Birthday Selection View");
            waiters.waitForElementVisibility(registerPageTitle);
            assertsUtils.isElementDisplayed(registerPageTitle);
//            assertsUtils.isElementDisplayed(backBtn);
            assertsUtils.isElementDisplayed(birthdayLabel);
            assertsUtils.isElementDisplayed(dateOfBirthDescription);
            assertsUtils.isElementDisplayed(dateOfBirthInput);
            assertsUtils.isElementDisplayed(proceedButton);
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate elements from Birthday Selection View");
        }
    }


    public BirthdaySelectionView selectDecember() {
        try {
            MyLogger.log.info("Trying to select December month");
            waiters.waitForElementVisibility(januaryMonth);
            if (runningSetup().getPlatformName().equalsIgnoreCase("ios")) {
                MobileGestures.selectPickerWheelValue(januaryMonth, "previous");
            } else {
                if (januaryMonth.toString() != "Dec") {
                    List<MobileElement> columns = selectMonth.findElements(By.className("android.widget.Button"));
                    MobileElement year = columns.get(0);
                    year.click();
                }
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot select December month");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }


    public BirthdaySelectionView selectYear(int currentYear, int desiredYear) {
        try {
            waiters.waitForElementVisibility(selectMonth);
            MyLogger.log.info("Trying year of birth");
            if (runningSetup().getPlatformName().equals("ios")) {
                int i = currentYear;
                while (i < desiredYear) {
                    MobileElement year = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypePickerWheel' AND value MATCHES[c] '" + i + "'"));
                    MyLogger.log.info("Custom element: " + year);
                    MobileGestures.selectPickerWheelValue(year, "next");
                    i++;
                    MyLogger.log.info("New Year value: " + i);
                }
            } else {

                int i = Integer.parseInt(yearInput.getText());
                while (i < desiredYear) {
                    List<MobileElement> columns = yearInputForTooYoungUser.findElements(By.className("android.widget.Button"));
                    MobileElement year = columns.get(1);
                    year.click();
                    i++;
                }
            }

        } catch (WebDriverException e) {
            throw new AssertionError("Cannot select year of birth");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }


    public ConsentsView clickOnProceedBtn() {
        MyLogger.log.info("Trying to click on proceed button to move to Consents View");
        ScreenView screenView = utilView.clickOnProceedButton(new ConsentsView(), gestures, proceedButton);
        return (ConsentsView) screenView;
    }


    public BirthdaySelectionView validateDisplayedBirthday(int desiredYear) {
        try {
            MyLogger.log.info("Trying to validate the displayed Birthday");
            waiters.waitForElementVisibility(backBtn);
            MyLogger.log.info(dateOfBirthInput.getText());
            Assert.assertTrue("The displayed Birthday does not coincide with the expected Birthday ", dateOfBirthInput.getText().contains(String.valueOf(desiredYear)));
            return this;
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot validate the displayed Birthday");
        }
    }


    public CountrySelectionView clickOnNavigateBackBtn() {
        ScreenView screenView = utilView.clickOnNavigateBackBtn(new CountrySelectionView(), gestures, backBtn);
        return (CountrySelectionView) screenView;
    }


    // SRDJAN ADDED SELECT YEAR METHOD

    public int userYearOfBirth (int yearsOld) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int yearInt = localDate.getYear();
        return yearInt - yearsOld;
    }

    public BirthdaySelectionView selectYearSecondMethod (int yearsOld) {
        int userYearOfBirth = userYearOfBirth(yearsOld);

        try {
            waiters.waitForElementVisibility(yearInputSrdjan);
            MyLogger.log.info("Trying year of birth");
            if (runningSetup().getPlatformName().equals("ios")) {

                if (userYearOfBirth(yearsOld) < 2000) {

                    for (int i = 0; i < 2000 - userYearOfBirth; i++) {                                   // +++++++++++++++++++++++++++++++++ THAT " i " value should be edited ++++++++++++++++++ //
                        MobileElement year = (MobileElement) Drivers.getMobileDriver().findElement(MobileBy.iOSNsPredicateString("type == 'XCUIElementTypePickerWheel' AND value MATCHES[c] '" + i + "'"));
                        MobileGestures.selectPickerWheelValue(year, "previous");
                    }
                }

            } else {
                if (userYearOfBirth(yearsOld) < 2000) {
                    for (int i = 0; i< 2000 - userYearOfBirth; i++) {
                        List<MobileElement> columns = yearInputSrdjan.findElements(By.className("android.widget.Button"));
                        MobileElement year = columns.get(0);
                        year.click();
                    }
                }
                else {
                    for (int i = 0; i<userYearOfBirth-2000; i++) {
                        List<MobileElement> columns = yearInputSrdjan.findElements(By.className("android.widget.Button"));
                        MobileElement year = columns.get(1);
                        year.click();
                    }
                }
            }

        } catch (WebDriverException e) {
            throw new AssertionError("Cannot select year of birth");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    // SRDJAN ADDED SELECT DAY METHOD

    public BirthdaySelectionView selectDay() {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dayNumber = localDate.getDayOfMonth();


        try {
            MyLogger.log.info("Trying to select current day");
            waiters.waitForElementVisibility(dayInputSrdjan);
            if (runningSetup().getPlatformName().equalsIgnoreCase("ios")) {
                for(int i = 1; i<dayNumber; i++ ) {
                    MobileGestures.selectPickerWheelValue(dayInputSrdjan, "next");
                }
            } else {
                List<MobileElement> columns = dayInputSrdjan.findElements(By.className("android.widget.Button"));
                MobileElement day = columns.get(1);
                for (int i=1; i<dayNumber; i++) {
                    day.click();
                }
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot select current day");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    // SRDJAN ADDED SELECT MONTH METHOD

    public BirthdaySelectionView selectMonth() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthNumber = localDate.getMonthValue();

        try {
            MyLogger.log.info("Trying to select current month");
            waiters.waitForElementVisibility(januaryMonth);
            if (runningSetup().getPlatformName().equalsIgnoreCase("ios")) {
                for(int i = 1; i<monthNumber; i++ ) {
                    MobileGestures.selectPickerWheelValue(januaryMonth, "next");
                }
            } else {

                List<MobileElement> columns = selectMonth.findElements(By.className("android.widget.Button"));
                MobileElement month = columns.get(1);
                for (int i=1; i<monthNumber; i++) {
                    month.click();
                }
            }
        } catch (WebDriverException e) {
            throw new AssertionError("Cannot select current month");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

}