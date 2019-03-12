package core.helpers;

import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NoSuchContextException;
import org.openqa.selenium.WebDriverException;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ContextHandler {
    private static final String NATIVE_APP = "NATIVE_APP";

    private static Set<String> getContextHandles() throws WebDriverException {
        return Drivers.getMobileDriver().getContextHandles();
    }

    private static void getAllContexts() {
        for (String context : getContextHandles()) {
            MyLogger.log.info("This are the available contexts : " + context);
        }
    }

    public String getCurrentContext() {
        return Drivers.getMobileDriver().getContext();
    }

    private static void switchToContext(String context) throws Exception {
        MyLogger.log.info("Trying to switch to : " + context);
        getAllContexts();
        if (Drivers.getMobileDriver().getContext().contains(context)) {
            MyLogger.log.info("Already on context " + context);
        } else {
            Drivers.getMobileDriver().getContextHandles();
            try {
                Drivers.getMobileDriver().context(context);
                MyLogger.log.info("Successfully switched to : " + context);
            } catch (NoSuchContextException e) {
                MyLogger.log.error("Could not switch to context " + context);
                throw e;
            }
        }

    }

    public static void switchToWebview() throws Exception {
        MyLogger.log.info("Trying to switch to first non NATIVE_APP view");
        boolean foundNonNativeContext = false;
        for (String context : getContextHandles()) {
            if ((!foundNonNativeContext) && !NATIVE_APP.equals(context)) {
                foundNonNativeContext = true;
                MyLogger.log.info("Found non native context: " + context);
                switchToContext(context);
            }
        }
        if (!foundNonNativeContext) {
            MyLogger.log.info("ONLY NATIVE_APP CONTEXT AVAILABLE");
        }
    }

    public static void switchToNative() {
        MyLogger.log.info("Trying to switch from WEBVIEW to NATIVE_APP context");
        if (Drivers.getMobileDriver().getContext().equalsIgnoreCase(NATIVE_APP)) {
            MyLogger.log.info("NATIVE_APP is already set as context");
        } else {
            Drivers.getMobileDriver().getContextHandles();
            try {
                Drivers.getMobileDriver().context(NATIVE_APP);
                MyLogger.log.info("Successfully switched to NATIVE_APP contexts");
            } catch (NoSuchContextException e) {
                MyLogger.log.error("Could not switch to NATIVE_APP context");
                throw e;
            }
        }

    }

    public static void switchContext(String context_name) {
        MyLogger.log.info("Before swtich context " + Drivers.getMobileDriver().getContext());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contextNames = Drivers.getMobileDriver().getContextHandles();
        if (!(contextNames.size() > 1)) {
            MyLogger.log.info("Can’t Switch as only one context is there");
            return;
        }
        MyLogger.log.info("size of handle " + contextNames.size());
        for (String contextName : contextNames) {

            MyLogger.log.info("Name of context is " + contextNames);
            Drivers.getMobileDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            if (contextName.contains(context_name)) {
                Drivers.getMobileDriver().context(contextName);
                return;
            }

        }

        MyLogger.log.info("after switch " + Drivers.getMobileDriver().getContext());
    }

    @Nullable
    private String getWebContext(AppiumDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }

    public void changeToWebView() {
        String webContext = getWebContext(Drivers.getMobileDriver());
        Drivers.getMobileDriver().context(webContext);
    }

}
