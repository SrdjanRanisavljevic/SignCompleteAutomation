package core.json.parsers;

public class AppiumReadJsonResults {

    /**
     * Desired Capabilities
     */
    private String DeviceID;
    private String platformVersion;
    private String DeviceName;
    private String appLocation;
    private String automationName;
    private String deviceUDID;
    private boolean unicodeKeyboard;
    private String appActivity;
    private String appPackage;
    private String deviceType;
    private String deviceOrientation;
    private String OSversion;
    private String name;

    /**
     * Server/App Capabilities - Android
     */
    private String BootstratPort;
    private String ChromePort;
    private int systemPort;

    /**
     * Server/App Capabilities - iOS
     */
    private String IosWebKit;
    private int wdaLocalPort;
    private String clearSystemFiles;
    private String appiumPort;
    private int wdaConnectionTimeout;
    private boolean useNewWDA;
    private int commandTimeouts;
    private boolean resetOnSessionStartOnly;
    private int wdaLaunchTimeout;
    private int wdaStartupRetries;
    private String bundleId;

    /**
     * Server Capabilities - General
     */
    private String ip;
    private int appiumServerPort;
    private String logLevel;
    private String nodeJS;
    private String appiumJS;
    private boolean sessionOverride;
    private int newCommandTimeout;
    private boolean fullReset;
    private boolean noReset;

    /**
     * User Data - accounts/passwords
     */
    private String usermail;
    private String userpassword;
    private String newpassword;

    /**
     * Parameter to see if we start setup for iOS or Android
     */
    private String platformName;

    public void setBundleId(String bundleID) {
        this.bundleId = bundleID;
    }

    public String getBundleId() {
        return bundleId;
    }

    public String getBootstratPort() {
        return BootstratPort;
    }

    public void setBootstratPort(String bootstratPort) {
        BootstratPort = bootstratPort;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public boolean isUnicodeKeyboard() {
        return unicodeKeyboard;
    }

    public void setUnicodeKeyboard(boolean unicodeKeyboard) {
        this.unicodeKeyboard = unicodeKeyboard;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }

    public String getAppLocation() {
        return appLocation;
    }

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getDeviceUDID() {
        return deviceUDID;
    }

    public void setDeviceUDID(String deviceUDID) {
        this.deviceUDID = deviceUDID;
    }

    public int getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public void setNewCommandTimeout(int newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    public String getDeviceOrientation() {
        return deviceOrientation;
    }

    public void setDeviceOrientation(String deviceOrientation) {
        this.deviceOrientation = deviceOrientation;
    }

    public boolean isFullReset() {
        return fullReset;
    }

    public void setFullReset(boolean fullReset) {
        this.fullReset = fullReset;
    }

    public boolean isNoReset() {
        return noReset;
    }

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getBootstrap_port() {
        return BootstratPort;
    }

    public void setBootstrap_port(String BootstratPort) {
        this.BootstratPort = BootstratPort;
    }

    public String getChrome_driver_port() {
        return ChromePort;
    }

    public void setChrome_driver_port(String ChromePort) {
        this.ChromePort = ChromePort;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAppiumServerPort() {
        return appiumServerPort;
    }

    public void setAppiumServerPort(int appiumServerPort) {
        this.appiumServerPort = appiumServerPort;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIosWebKit() {
        return IosWebKit;
    }

    public void setIosWebKit(String iosWebKit) {
        IosWebKit = iosWebKit;
    }

    public boolean isSessionOverride() {
        return sessionOverride;
    }

    public void setSessionOverride(boolean sessionOverride) {
        this.sessionOverride = sessionOverride;
    }

    public String getChromePort() {
        return ChromePort;
    }

    public void setChromePort(String chromePort) {
        ChromePort = chromePort;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public int getWdaLocalPort() {
        return wdaLocalPort;
    }

    public void setWdaLocalPort(int wdaLocalPort) {
        this.wdaLocalPort = wdaLocalPort;
    }

    public String getOSversion() {
        return OSversion;
    }

    public void setOSversion(String OSversion) {
        this.OSversion = OSversion;
    }

    public String getNodeJS() {
        return nodeJS;
    }

    public void setNodeJS(String nodeJS) {
        this.nodeJS = nodeJS;
    }

    public String getAppiumJS() {
        return appiumJS;
    }

    public void setAppiumJS(String appiumJS) {
        this.appiumJS = appiumJS;
    }

    public String getClearSystemFiles() {
        return clearSystemFiles;
    }

    public void setClearSystemFiles(String clearSystemFiles) {
        this.clearSystemFiles = clearSystemFiles;
    }

    public int getWdaConnectionTimeout() {
        return wdaConnectionTimeout;
    }

    public void setWdaConnectionTimeout(int wdaConnectionTimeout) {
        this.wdaConnectionTimeout = wdaConnectionTimeout;
    }

    public boolean getUseNewWDA() {
        return useNewWDA;
    }

    public void setUseNewWDA(boolean useNewWDA) {
        this.useNewWDA = useNewWDA;
    }

    public int getCommandTimeouts() {
        return commandTimeouts;
    }

    public void setCommandTimeouts(int commandTimeouts) {
        this.commandTimeouts = commandTimeouts;
    }

    public boolean getResetOnSessionStartOnly() {
        return resetOnSessionStartOnly;
    }

    public void setResetOnSessionStartOnly(boolean resetOnSessionStartOnly) {
        this.resetOnSessionStartOnly = resetOnSessionStartOnly;
    }

    public String getAppiumPort() {
        return appiumPort;
    }

    public void setAppiumPort(String appiumPort) {
        this.appiumPort = appiumPort;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public int getWdaStartupRetries() {
        return wdaStartupRetries;
    }

    public void setWdaStartupRetries(int wdaStartupRetries) {
        this.wdaStartupRetries = wdaStartupRetries;
    }

    public int getWdaLaunchTimeout() {
        return wdaLaunchTimeout;
    }

    public void setWdaLaunchTimeout(int wdaLaunchTimeout) {
        this.wdaLaunchTimeout = wdaLaunchTimeout;
    }

    public int getSystemPort() {
        return systemPort;
    }

    public void setSystemPort(int systemPort) {
        this.systemPort = systemPort;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
