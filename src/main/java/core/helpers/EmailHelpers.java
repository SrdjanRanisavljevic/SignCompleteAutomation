package core.helpers;

import api.drivers.Drivers;
import core.classic.methods.Swipe;
import core.watchers.MyLogger;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;

import static core.classic.methods.Swipe.refreshEmailListUntilEmailIsDisplayed;
import static org.junit.Assert.fail;

public class EmailHelpers {

    private final static Logger log = Logger.getLogger(EmailHelpers.class);
    private static long TOTALTIMEWAITINGFOREMAIL = 0;
    private static ScreenOrientation devicScreenOrientation = ScreenOrientation.PORTRAIT;

    public static ScreenOrientation getDevicScreenOrientation() {
        return devicScreenOrientation;
    }

    public static void setDevicScreenOrientation(ScreenOrientation devicScreenOrientation) {
        EmailHelpers.devicScreenOrientation = devicScreenOrientation;
    }

    /**
     * /** Sends another email to the mentioned address, and returns it's
     * subject. App needs to be in Inbox for this to work
     *
     * @param myEmailAddress - the email address to send the email to
     * @return Subject of email
     */
    public static String testMailSubject(String myEmailAddress) throws FileNotFoundException {

        String subject = String.format("Subject %f", Math.random());
        return testMailSubject(myEmailAddress, subject, getCurrentTestMethod());
    }

    private static String getCurrentTestMethod() {
        return "";
    }

    /**
     * /** Sends a number of email to the mentioned address, and returns their
     * subject. App needs to be in Inbox for this to work
     *
     * @param myEmailAddress - the email address to send the email to
     * @return Subjects of emails sent
     */
    public static String[] testMailSubject(String myEmailAddress, int numberOfEmails) throws FileNotFoundException {

        String[] subjects = new String[numberOfEmails];
        for (int i = 0; i < numberOfEmails; i++) {
            subjects[i] = String.format("Subject %f", Math.random());
        }
        return testMailSubject(myEmailAddress, subjects, getCurrentTestMethod());
    }

    /**
     * Send another email to myEmailAddress, using a predefined sender. App
     * needs to be in Inbox for this to work
     *
     * @param myEmailAddress
     * @param subject
     * @param body
     * @return Subject of email
     */


    /**
     * Send a set of emails to myEmailAddress, using a predefined sender. App
     * needs to be in Inbox for this to work
     *
     * @param myEmailAddress
     * @param subjects
     * @param body
     * @return Subjects of email
     */
    private static String[] testMailSubject(String myEmailAddress, String[] subjects, String body) throws FileNotFoundException {
        return testMailSubject(myEmailAddress, "paket_qs_05@ver.sul.t-online.de", "1234test", subjects, body);
    }

    /**
     * Send a set of emails to myEmailAddress, using a predefined sender. App
     * needs to be in Inbox for this to work
     *
     * @param myEmailAddress
     * @param subjects
     * @param body
     * @return Subjects of email
     */
    public static String[] testMailSubject(String myEmailAddress, String fromEmailAddress, String[] subjects,
                                           String body) throws FileNotFoundException {
        return testMailSubject(myEmailAddress, fromEmailAddress, "1234test", subjects, body);
    }

    /**
     * Send another email to myEmailAddress, using the provided sender. App
     * needs to be in Inbox for this to work
     *
     * @param myEmailAddress
     * @param subject
     * @param body
     * @return
     */
    private static String testMailSubject(String myEmailAddress,
                                          String subject, String body) throws FileNotFoundException {
        String[] subjects = new String[1];
        subjects[0] = subject;
        String[] returnSubject = testMailSubject(myEmailAddress, "paket_qs_05@ver.sul.t-online.de", "1234test", subjects, body);
        return returnSubject[0];
    }

    /**
     * Send a set of emails to myEmailAddress, using the provided sender. App
     * needs to be in Inbox for this to work
     *
     * @param myEmailAddress
     * @param fromEmailAddress
     * @param fromEmailPassword
     * @param subjects
     * @param body
     * @return
     */
    private static String[] testMailSubject(String myEmailAddress, String fromEmailAddress, String fromEmailPassword,
                                            String[] subjects, String body) throws FileNotFoundException {
        try {
            for (String subject : subjects) {
                SmtpEmail.putMailInInbox(fromEmailAddress, fromEmailPassword, myEmailAddress, subject, body);
            }
        } catch (AddressException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with address exception");
        } catch (MessagingException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with messaging exception");
        }

        Swipe.swipeDown();
        waitForEmail(subjects);

        return subjects;
    }

    /**
     * Send another email to myEmailAddress, with one attachment from camera
     * roll
     *
     * @param myEmailAddress
     * @return Subject of email
     */
    public static String testMailSubjectWithAttachment(String myEmailAddress) throws FileNotFoundException {

        String subject = String.format("Subject %f", Math.random());
        try {
            SmtpEmail.putMailInInboxWithAttachment("paket_qs_05@ver.sul.t-online.de", "1234test", myEmailAddress,
                    subject, getCurrentTestMethod(), "target/test-classes/TestImage.jpg");
        } catch (AddressException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with address exception");
        } catch (MessagingException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with messaging exception");
        }
        waitForEmail(subject);
        return subject;
    }

    /**
     * Send another email to myEmailAddress, with one attachment from camera
     * roll
     *
     * @param myEmailAddress
     * @param pathToAttachment
     * @return Subject of email
     */
    public static String testMailSubjectWithAttachment(String myEmailAddress, String pathToAttachment) throws FileNotFoundException {

        String subject = String.format("Subject %f", Math.random());
        try {
            SmtpEmail.putMailInInboxWithAttachment("vtu.capgemini1@ver.sul.t-online.de", "1234test", myEmailAddress,
                    subject, getCurrentTestMethod(), pathToAttachment);
        } catch (AddressException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with address exception");
        } catch (MessagingException e) {
            e.printStackTrace();
            fail("Smtp email sending failed with messaging exception");
        }
        waitForEmail(subject);
        return subject;
    }

    /**
     * Opens an email, while in a folder. Needed because of how email element is
     * identified in different iOS version
     *
     * @param subject
     */
    public static void openEmail(String subject) throws FileNotFoundException {
        // 99.9% of the time the email is visible.
        try {
            log.info("Clicking email with subject: " + subject);
            clickEmail(subject);
        } catch (RuntimeException e) {
            log.info("clickEmail exception caught:");
            e.printStackTrace();
            if (e.getCause() instanceof TimeoutException) {
                refreshEmailListUntilEmailIsDisplayed(subject);
                clickEmail(subject);
            } else {
                throw e;
            }
        }
    }

    private static void clickEmail(String subject) {
        WebElement we = Drivers.getMobileDriver().findElement(By.id(subject));
        int heigt = we.getLocation().getY();
        int width = we.getLocation().getX();
        new TouchAction<>(Drivers.getMobileDriver()).tap(PointOption.point(width, heigt)).release().perform();
    }


    /**
     * Waits for mail with 'subject' to be present in folder, on first position.
     * Has a retry mechanism implemented. At start, the app has to be in the
     * mentioned folder
     *
     * @param subjects
     */
    private static void waitForEmail(String... subjects) throws FileNotFoundException {
        // Funtion has to be in folder to work ! ! !

        String folderName = "";
        String listOfSubjects = "";
        for (String subject : subjects) {
            listOfSubjects = String.format("%s, %s", subject, listOfSubjects);
        }

        switch (Folder.POSTEINGANG) {
            case POSTEINGANG:
                folderName = "Posteingang";
                break;
            case GESENDET:
                folderName = "Gesendet";
                break;
            case PAPIERKORB:
                folderName = "Papierkorb";
                break;
            case ENTWURFE:
                folderName = "Entwürfe";
                break;
            case SPAM:
                folderName = "Spam";
                break;
            default:
                fail("Not implemented foder in waitForEmail function: " + Folder.POSTEINGANG);
                break;
        }

        long startTime = System.currentTimeMillis();
        Swipe.swipeDown();

        boolean found = false;
        boolean[] individualMailFound = new boolean[subjects.length];
        int TIMEOUT = 10;
        int retry = 1;
        do {
            try {
                for (int i = 0; i < subjects.length; i++) {
                    if (!individualMailFound[i]) {
                        Assert.assertTrue(subjects[i], true);
                        log.info("mail still not found");
                    }
                    individualMailFound[i] = true;
                }
                found = true;
                for (boolean individualFound : individualMailFound) {
                    found = found & individualFound;
                }
            } catch (Throwable e) {
                retry++;
                Swipe.swipeDown();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
        } while ((retry <= TIMEOUT) && (!found));

        long endTime = System.currentTimeMillis();
        long waitTime = (endTime - startTime) / 1000;
        TOTALTIMEWAITINGFOREMAIL = TOTALTIMEWAITINGFOREMAIL + waitTime;

        System.out.println(String.format(
                "Waiting for emails %s in folder %s took %d seconds. Total wait time this run: %d seconds (i.e. %d minutes)",
                listOfSubjects, Folder.POSTEINGANG, (endTime - startTime) / 1000, TOTALTIMEWAITINGFOREMAIL,
                (TOTALTIMEWAITINGFOREMAIL / 60)));
        if (!found) {
            fail(String.format("One of emails  %s not found in %s.", listOfSubjects, Folder.POSTEINGANG));
        } else {
            // mail was found, now just wait for the Aktualisert message at the
            // bottom of the screen, so that the page is completely loaded.
            // iOsAssertExists(By.id(FOLDER_AKTUALISIERT_NAME),
            // "Folder not refreshed, since Aktualisiert message not found");
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // // do nothing
            // }
        }
    }

    public static void send201Mails(String usermail) {
        try {
            for (int i = 0; i < 201; i++) {
                SmtpEmail.sendEmail("vtu.test1@ver.sul.t-online.de", "1234test", usermail, "Test" + i, "");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());// nothing
        }
    }

    public enum Folder {
        POSTEINGANG, GESENDET, PAPIERKORB, ENTWURFE, SPAM
    }

    public static void sendEmail3(String fromEmail, String password, String toEmail) {

        MyLogger.log.info("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendEmail(session, fromEmail, toEmail);

    }

    private static void sendEmail(Session session, String fromEmail, String toEmail) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Lucian testing smtp"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject("Test Push", "UTF-8");

            msg.setText("Test Push", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            MyLogger.log.info("Message is ready");
            Transport.send(msg);

            MyLogger.log.info("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}