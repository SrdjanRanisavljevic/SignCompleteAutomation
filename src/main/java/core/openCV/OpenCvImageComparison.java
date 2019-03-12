package core.openCV;

import api.drivers.Drivers;
import core.imageHelper.Screenshot;
import core.watchers.MyLogger;
import io.appium.java_client.imagecomparison.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

class OpenCvImageComparison {

    /**
     * Image comparison method with between a defined image and current screen
     *
     * @param imageName - your image
     * @throws IOException
     */
    public void compareImageWithcurrentScreen(String imageName) throws IOException {
        String originalImg = String.format("target/test-classes/" + imageName + ".png");
        File screenshot = Screenshot.getScreenshot();
        MyLogger.log.info("Starting comparison between " + imageName + " and current screen");
        SimilarityMatchingResult result = Drivers.getMobileDriver()
                .getImagesSimilarity(new File(originalImg), screenshot, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        MyLogger.log.info("Printing comparison results between " + imageName + " and current screen:");
        if (result.getScore() > 0.959) {
            MyLogger.log.info("+++++++++++ " + result.getScore() + " Comparison PASSED +++++++++++");
        } else if (result.getScore() < 0.959) {
            MyLogger.log.info("+++++++++++ " + result.getScore() + " Comparison FAILED +++++++++++");

            Date date = new Date();
            long getMilis = date.getTime();
            GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
            calendar.setTimeInMillis(getMilis);

            File snapshotDir = new File("target/storedVisualization");
            if (!snapshotDir.exists()) {
                FileUtils.forceMkdir(snapshotDir);
            }
            MyLogger.log.info("Creating local image to display differences between " + imageName + " and current screen");
            result.storeVisualization(new File("target/storedVisualization/" + calendar.getTime() + "_" + imageName + "_leftActual_rightExpected.png"));
        } else {

        }
    }

    /**
     * Template recognition - find a partial image from a full image
     *
     * @param imageNamePartial
     * @param imageNameFull
     * @throws IOException
     */
    public void partialImageRecognitionWithTwoGiveImages(String imageNamePartial, String imageNameFull) throws IOException {
        String partialImage = String.format("target/test-classes/" + imageNamePartial + ".png");
        String fullImage = String.format("target/test-classes/" + imageNameFull + ".png");
        try {
            OccurrenceMatchingResult result = Drivers.getMobileDriver()
                    .findImageOccurrence(new File(fullImage), new File(partialImage), new OccurrenceMatchingOptions()
                            .withEnabledVisualization());
            Date date = new Date();
            long getMilis = date.getTime();
            GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
            calendar.setTimeInMillis(getMilis);

            File snapshotDir = new File("target/storedVisualization");
            if (!snapshotDir.exists()) {
                FileUtils.forceMkdir(snapshotDir);
            }

            MyLogger.log.info("Creating local image to display differences between " + imageNameFull + " and partial image " + partialImage);
            result.storeVisualization(new File("target/storedVisualization/" + calendar.getTime() + "_MatchingBetween_" + imageNamePartial + "_&_" + imageNameFull + ".png"));
        } catch (Exception e) {
            MyLogger.log.info("+++++++++++ Cannot find any occurences of the partial image in the full image  +++++++++++");
        }

    }

    /**
     * Template recognition - find a partial image from current screen
     *
     * @param imageName
     * @throws IOException
     */
    public void partialImageRecognitionWithCurrentScreen(String imageName) throws IOException {
        String partialImage = String.format("target/test-classes/" + imageName + ".png");
        File screenshot = Screenshot.getScreenshot();
        try {
            OccurrenceMatchingResult result = Drivers.getMobileDriver()
                    .findImageOccurrence(screenshot, new File(partialImage), new OccurrenceMatchingOptions()
                            .withEnabledVisualization());
            Date date = new Date();
            long getMilis = date.getTime();
            GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
            calendar.setTimeInMillis(getMilis);

            File snapshotDir = new File("target/storedVisualization");
            if (!snapshotDir.exists()) {
                FileUtils.forceMkdir(snapshotDir);
            }

            MyLogger.log.info("Creating local image to display differences between current screen and partial image " + imageName);
            result.storeVisualization(new File("target/storedVisualization/" + calendar.getTime() + "_MatchingBetween_CurrentSreen_&_" + imageName + ".png"));
        } catch (Exception e) {
            MyLogger.log.info("+++++++++++ Cannot find any occurences of the partial image in the full image  +++++++++++");
        }

    }


    /**
     * Display similarities between and image and current screen
     *
     * @param imageName
     * @throws IOException
     */
    public void displaySimilaritiesGivenImageAndCurrentScreen(String imageName) throws IOException {
        String originalImg = String.format("target/test-classes/" + imageName + ".png");
        File screenshot = Screenshot.getScreenshot();

        FeaturesMatchingResult result = Drivers.getMobileDriver()
                .matchImagesFeatures(screenshot, new File(originalImg), new FeaturesMatchingOptions()
                        .withDetectorName(FeatureDetector.ORB)
                        .withGoodMatchesFactor(40)
                        .withMatchFunc(MatchingFunction.BRUTE_FORCE_HAMMING)
                        .withEnabledVisualization());

        Date date = new Date();
        long getMilis = date.getTime();
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
        calendar.setTimeInMillis(getMilis);

        File snapshotDir = new File("target/storedVisualization");
        if (!snapshotDir.exists()) {
            FileUtils.forceMkdir(snapshotDir);
        }

        MyLogger.log.info("Creating local image to display matchings between " + imageName + " and current screen");
        result.storeVisualization(new File("target/storedVisualization/" + calendar.getTime() + "_MatchingBetween_" + imageName + "_&_currentScreen.png"));
    }

    /**
     * Display similarities between two give images
     *
     * @param firstImageName
     * @param secondImageName
     * @throws IOException
     */
    public void displaySimilaritiesBetweenTwoGiveImages(String firstImageName, String secondImageName) throws IOException {
        String firstImage = String.format("target/test-classes/" + firstImageName + ".png");
        String secondImage = String.format("target/test-classes/" + secondImageName + ".png");

        FeaturesMatchingResult result = Drivers.getMobileDriver()
                .matchImagesFeatures(new File(firstImage), new File(secondImage), new FeaturesMatchingOptions()
                        .withDetectorName(FeatureDetector.ORB)
                        .withGoodMatchesFactor(40)
                        .withMatchFunc(MatchingFunction.BRUTE_FORCE_HAMMING)
                        .withEnabledVisualization());

        Date date = new Date();
        long getMilis = date.getTime();
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
        calendar.setTimeInMillis(getMilis);

        File snapshotDir = new File("target/storedVisualization");
        if (!snapshotDir.exists()) {
            FileUtils.forceMkdir(snapshotDir);
        }

        MyLogger.log.info("Creating local image to display similarities between " + firstImageName + "_&_" + secondImageName);
        result.storeVisualization(new File("target/storedVisualization/" + calendar.getTime() + "_SimilaritiesBetween_" + firstImageName + "_&_" + secondImageName + ".png"));
    }
}
