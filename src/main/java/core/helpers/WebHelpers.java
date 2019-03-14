package core.helpers;

import core.watchers.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static core.helpers.MacTerminalCmd.runCommand;
import static core.json.parsers.ConfigJasonFileReading.getIOSJasonResults;

public class WebHelpers {

    private static final ConcurrentHashMap<Long, Integer> iosWebKitRecordProcess =
            new ConcurrentHashMap<>();

    public static void startIosWebKit() throws Exception {
//        String cmd = "ios_webkit_debug_proxy -c " + ServerManager.DEVICE_ID() + ":" + gen();
        String cmd = "ios_webkit_debug_proxy -c " + getIOSJasonResults().getDeviceID() + ":" + getIOSJasonResults().getIosWebKit();
        MyLogger.log.info("++++++++ RUNNING IOS_WEBKIT_DEBUG_PROXY++++++++ :" + cmd);

        Process startWebkitProxy = Runtime.getRuntime().exec(cmd);
        MyLogger.log.info("++++++++Starting IOS_WEBKIT_DEBUG_PROXY++++++++ :" + startWebkitProxy);
        iosWebKitRecordProcess
                .put(Thread.currentThread().getId(), getPid(startWebkitProxy));
        MyLogger.log.info("++++++++GET ID + PID of IOS_WEBKIT_DEBUG_PROXY++++++++ :" + startWebkitProxy);
    }

    public static void stopIosWebKit() throws IOException {
        Integer processId = iosWebKitRecordProcess.get(Thread.currentThread().getId());
        stopRunningProcess(processId);
    }

    private static void stopRunningProcess(Integer processId) throws IOException {
        if (processId != -1) {
            String process = "pgrep -P " + processId;
            MyLogger.log.info(process);
            Process p2 = Runtime.getRuntime().exec(process);
            BufferedReader r = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String command = "kill -2 " + processId;
            MyLogger.log.info("Stopping Running Process");
            MyLogger.log.info("******************" + command);
            try {
                runCommand(command);
                Thread.sleep(2000);
                MyLogger.log.info("Killed running ios_web_kit_proxy with exit code :" + command);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getPid(Process process) {
        try {
            Class<?> cProcessImpl = process.getClass();
            Field fPid = cProcessImpl.getDeclaredField("pid");
            if (!fPid.isAccessible()) {
                fPid.setAccessible(true);
            }
            return fPid.getInt(process);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
