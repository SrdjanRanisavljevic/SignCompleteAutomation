package core.jsonParsers;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ConfigJasonFileReading {

    public static AppiumReadJsonResults getAndroidJasonResults() throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(getAndroidConfig(), AppiumReadJsonResults.class);
    }

    public static AppiumReadJsonResults getIOSJasonResults() throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(getIosConfig(), AppiumReadJsonResults.class);
    }

    public static AppiumReadJsonResults getPlatformUnderTest() throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(getPlatformConfig(), AppiumReadJsonResults.class);
    }

    private static Reader getAndroidConfig() throws FileNotFoundException {
        Reader json = null;
        try {
            json = new FileReader("src/test/resources/LocalJsonAndroid.json");
        } catch (Throwable e) {
            json = new FileReader("src\\test\\resources\\LocalJsonAndroid.json");
        }
        return json;
    }

    private static Reader getIosConfig() throws FileNotFoundException {
        Reader json = null;
        json = new FileReader("src/test/resources/LocalJsonIOS.json");
        return json;
    }

    private static Reader getPlatformConfig() throws FileNotFoundException {
        Reader json = null;
        try {
            json = new FileReader("src/test/resources/PlatfomVersion.json");
        } catch (Throwable e) {
            json = new FileReader("src\\test\\resources\\PlatfomVersion.json");
        }
        return json;
    }

    public static AppiumReadJsonResults runningSetup() throws FileNotFoundException {
        AppiumReadJsonResults appiumReadJsonResults = null;
        Gson gson = new Gson();
        if (getPlatformUnderTest().getPlatformName().equals("ios")) {
            appiumReadJsonResults = gson.fromJson(getIosConfig(), AppiumReadJsonResults.class);
        } else if (getPlatformUnderTest().getPlatformName().equals("android")) {
            appiumReadJsonResults = gson.fromJson(getAndroidConfig(), AppiumReadJsonResults.class);
        } else {

        }
        return appiumReadJsonResults;
    }

}
