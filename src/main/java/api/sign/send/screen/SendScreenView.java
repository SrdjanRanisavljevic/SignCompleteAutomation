package api.sign.send.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SendScreenView {




    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"userPassword\")")
    private MobileElement userPasswordField;
}
