package core.imageHelper;

import api.drivers.Drivers;
import core.helpers.Coordinates;
import core.watchers.MyLogger;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ScreenOrientation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.jsonParsers.ConfigJasonFileReading.getAndroidJasonResults;
import static core.jsonParsers.ConfigJasonFileReading.getIOSJasonResults;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ScreenshotComparison {

//    public static void compareImageSectionToCurrentScreen(String imageName) throws Exception {
//        compareImageSectionToCurrentScreen(imageName);
//    }

    private static void compareImageSectionToCurrentScreen(String imageName) throws Exception {

        // change below variable to false if you need to take all the
        // screenshots for a test. If false, will not crash the test if images
        // do not match, but still harvest screenshots and comparison results.
        // !!!NEVER COMMIT WITH VALUE false!!!!

        boolean crashTestIfNoMatch = true;

        int currentRetry = 0;
        boolean found = false;
        double percentageFound = 0.0;
        Coordinates coordinates = new Coordinates();

        String imagePath = String.format("target/test-classes/%s_%s.png", imageName, getImageDeviceSuffix());
        BufferedImage sectionBI;
        try {
            sectionBI = ImageUtil.getBufferedImageFromFile(imagePath);
            BufferedImage screenshotBI = null;
            while ((currentRetry < 1) && !found) {
                File screenshot = Screenshot.getScreenshot();
                screenshotBI = ImageIO.read(screenshot);

                percentageFound = ImageSectionSikuli.findSubImage(screenshotBI, imagePath, coordinates);

                if (percentageFound > 0.999) { // changed to 0.97 from 0.999
                    // because ipad has time in
                    // screens Kai-Felix Braun
                    // 21.04.2016
                    found = true;
                } else {
                    currentRetry++;
                    MyLogger.log.info(String.format("ImageUtil %s not found in screenshot. Try %d/%d", imagePath, currentRetry,
                            1));
                }
            }

            if (!found) {
                BufferedImage combined = new BufferedImage(2 * screenshotBI.getWidth(), screenshotBI.getHeight(),
                        BufferedImage.TYPE_INT_ARGB);

                // paint both images, preserving the alpha channels
                Graphics g = combined.getGraphics();
                g.drawImage(sectionBI, 0, 0, null);
                g.drawImage(screenshotBI, screenshotBI.getWidth(), 0, null);

                // draw differences
                Graphics2D gc = combined.createGraphics();
                gc.setColor(Color.RED);

                int cX = coordinates.getX();
                int cY = coordinates.getY();
                int sW = sectionBI.getWidth();
                int sH = sectionBI.getHeight();

                for (int i = 0; i < sW; i++) {
                    for (int j = 0; j < sH; j++) {

                        if (!ImageUtil.isPixelTheSame(i, j, i + cX, j + cY, sectionBI, screenshotBI, 1)) {
                            // draw an indicator on the change image to show
                            // where change was detected.
                            int x1 = i / ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            x1 = x1 * ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int w = ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int y1 = j / ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            y1 = y1 * ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int h = ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            gc.drawRect(x1, y1, w, h);
                            gc.drawRect(x1 + cX + screenshotBI.getWidth(), y1 + cY, w, h);
                        }
                    }
                }
                ImageUtil.saveImageAsArtefact(combined, "LeftExpectedRightActual");
                // also save screenshot with the not found name, it may help
                // testers regenerate the missing sections easily
                ImageUtil.saveImageAsArtefact(screenshotBI, String.format("%s_%s", imageName, getImageDeviceSuffix()));
            }
            if (crashTestIfNoMatch) {
                assertTrue("Screenshot does not contain expected section", found);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                File screenshot = Screenshot.getScreenshot();
                BufferedImage screenshotBI = ImageIO.read(screenshot);
                ImageUtil.saveImageAsArtefact(screenshotBI, String.format("%s_%s", imageName, getImageDeviceSuffix()));
            } catch (IOException ee) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (crashTestIfNoMatch) {
                fail(String.format("Cannot read image from file %s for comparison", imagePath));
            }
        }
    }

    private static String getImageDeviceSuffix() throws Exception {
        String imageDeviceSuffix = null;

        if (getIOSJasonResults().getOSversion().startsWith("10")) {
            imageDeviceSuffix = "iOS10";
        } else if (getIOSJasonResults().getOSversion().startsWith("11")) {
            imageDeviceSuffix = "iOS11";
        } else if (getAndroidJasonResults().getOSversion().contains("Android8")) {
            imageDeviceSuffix = "Android8";
        } else {
            MyLogger.log.info("Add os version to if condition");
        }

        if (Drivers.driverIos.getOrientation() == ScreenOrientation.LANDSCAPE) {
            imageDeviceSuffix = imageDeviceSuffix + "_L";
        } else {
            imageDeviceSuffix = imageDeviceSuffix + "_P";
        }

        if ((getAndroidJasonResults().getDeviceName().toUpperCase().contains("6P"))) {
            imageDeviceSuffix = imageDeviceSuffix + "_iPhone_6P";
        } else if (getIOSJasonResults().getDeviceName().toUpperCase().contains(" iPhone7")) {
            imageDeviceSuffix = imageDeviceSuffix + "_iPhone_7";
        } else {
            MyLogger.log.info("Device not recognised. Consider updating EmailApplicationHelpers.setImageDeviceSuffix()");
        }

        return imageDeviceSuffix;
    }

    public static void saveScreenshotForFutureUnitTests(String newFilePathAndName) throws Exception {

        try {
            File screenshot = Screenshot.getScreenshot();

            BufferedImage screenshotBI;

            screenshotBI = ImageIO.read(screenshot);

            screenshotBI = ImageUtil.cropBufferedImageForTesting(screenshotBI);

            // extract folder using regex
            Pattern p = Pattern.compile("(.*)/.*");
            Matcher m = p.matcher(newFilePathAndName);
            if (m.find()) {
                String folder = m.group(1);
                File folderFile = new File(folder);
                if (!folderFile.exists()) {
                    FileUtils.forceMkdir(folderFile);
                }
            }

            newFilePathAndName = String.format("%s_%s.png", newFilePathAndName, getImageDeviceSuffix());
            File newFile = new File(newFilePathAndName);
            newFile.createNewFile();

            ImageIO.write(screenshotBI, "png", newFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void tapImageSection(String imageName) throws Exception {

        // change below variable to false if you need to take all the
        // screenshots for a test. If false, will not crash the test if images
        // do not match, but still harvest screenshots and comparison results.
        // !!!NEVER COMMIT WITH VALUE false!!!!

        boolean crashTestIfNoMatch = true;

        boolean found = false;
        double percentageFound = 0.0;
        Coordinates coordinates = new Coordinates();

        String imagePath = String.format("target/test-classes/%s_%s.png", imageName, getImageDeviceSuffix());
        BufferedImage sectionBI;
        try {
            sectionBI = ImageUtil.getBufferedImageFromFile(imagePath);
            BufferedImage screenshotBI = null;

            File screenshot = Screenshot.getScreenshot();
            screenshotBI = ImageIO.read(screenshot);

            percentageFound = ImageSectionSikuli.findSubImage(screenshotBI, imagePath, coordinates);

            int cX = coordinates.getX();
            int cY = coordinates.getY();
            int sW = sectionBI.getWidth();
            int sH = sectionBI.getHeight();

            if (percentageFound > 0.999) { // changed to 0.97 from 0.999 because
                // ipad has time in screens
                // Kai-Felix Braun 21.04.2016
                found = true;
            } else {
                MyLogger.log.info(String.format("ImageUtil %s not found in screenshot for tap.", imagePath));
            }

            if (!found) {
                BufferedImage combined = new BufferedImage(2 * screenshotBI.getWidth(), screenshotBI.getHeight(),
                        BufferedImage.TYPE_INT_ARGB);

                // paint both images, preserving the alpha channels
                Graphics g = combined.getGraphics();
                g.drawImage(sectionBI, 0, 0, null);
                g.drawImage(screenshotBI, screenshotBI.getWidth(), 0, null);

                // draw differences
                Graphics2D gc = combined.createGraphics();
                gc.setColor(Color.RED);

                for (int i = 0; i < sW; i++) {
                    for (int j = 0; j < sH; j++) {

                        if (!ImageUtil.isPixelTheSame(i, j, i + cX, j + cY, sectionBI, screenshotBI, 1)) {
                            // draw an indicator on the change image to show
                            // where change was detected.
                            int x1 = i / ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            x1 = x1 * ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int w = ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int y1 = j / ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            y1 = y1 * ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            int h = ImageUtil.DIFFERENCE_SQUARE_SIZE;
                            gc.drawRect(x1, y1, w, h);
                            gc.drawRect(x1 + cX + screenshotBI.getWidth(), y1 + cY, w, h);
                        }
                    }
                }
                ImageUtil.saveImageAsArtefact(combined, "LeftExpectedRightActual");
                // also save screenshot with the not found name, it may help
                // testers regenerate the missing sections easily
                ImageUtil.saveImageAsArtefact(screenshotBI, String.format("%s_%s", imageName, getImageDeviceSuffix()));
            }
            if (crashTestIfNoMatch) {
                assertTrue("Screenshot does not contain expected section for tapping", found);
            }

            // tap on coordinates
            new TouchAction<>(Drivers.getMobileDriver()).longPress(PointOption.point(cX + sW / 2, cY + sH / 2)).release().perform();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                File screenshot = Screenshot.getScreenshot();
                BufferedImage screenshotBI = ImageIO.read(screenshot);
                ImageUtil.saveImageAsArtefact(screenshotBI, String.format("%s_%s", imageName, getImageDeviceSuffix()));
            } catch (IOException ee) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (crashTestIfNoMatch) {
                fail(String.format("Cannot read image from file %s for comparison", imagePath));
            }
        }
    }
}
