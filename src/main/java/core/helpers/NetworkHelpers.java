package core.helpers;


import api.drivers.Drivers;
import core.watchers.MyLogger;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;

public class NetworkHelpers {

    public static ConnectionState getConnectionStatus() {
        return Drivers.androidDriver.getConnection();
    }

    public static void testWIFIConnection() {
        if (getConnectionStatus().isWiFiEnabled()) {
            MyLogger.log.info("WIFI ON ---> WE CAN CONTINUE TESTING");
        } else {
            MyLogger.log.info("WIFI OFF ---> NO POINT TO CONTINUE TESTING");
        }

    }

    public void turnOFFWifi() {
        MyLogger.log.info("Disabling wifi");
        ConnectionState connectionState = Drivers.androidDriver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
    }

    public void turnOnWifi() {
        MyLogger.log.info("Enabling wifi");
        ConnectionState connectionState = Drivers.androidDriver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
    }

    public void turnOnAirplaneMode() {
//        Drivers.androidDriver.setConnection(ConnectionState.AIRPLANE_MODE_MASK);
    }

    public enum Bitmask {

        AIRPLANE_MODE(1), ALL_NETWORK_ON(6), DATA_ONLY(4), WIFI_ONLY(2), NONE(0);

        private final int bitmask;

         Bitmask(int bitmask) {
            this.bitmask = bitmask;
        }

        public int getValue() {
            return bitmask;
        }
    }

}
