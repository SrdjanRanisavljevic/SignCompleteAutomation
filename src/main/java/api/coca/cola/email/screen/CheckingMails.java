package api.coca.cola.email.screen;

import core.cucumberhooks.Hooks;
import core.watchers.MyLogger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.mail.*;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;


/**
 * Created by SrdjanR on 3/14/2019.
 */


public class CheckingMails {
    private String host = "pop.gmail.com";// change accordingly
    private String mailStoreType = "pop3";
    private String usermail = runningSetup().getUsermail();
    private String password = runningSetup().getUserpassword();

    public CheckingMails() throws FileNotFoundException {
    }

    public Message fetch(String pop3Host, String storeType, String user, String password) {

        Message returnedMessage = null;
        String cs = "";

        try {

            // create properties field
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            // emailSession.setDebug(true);

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(pop3Host, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);


            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
//            System.out.println("messages.length---" + messages.length);

            returnedMessage = messages[(messages.length - 1)];
//            cs = writePart(messages[(messages.length - 1)]);


            // close the store and folder objects
//            emailFolder.close(false);
//            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return returnedMessage;
        }
    }


    public String writePart(Part p) throws Exception {
        if (p instanceof Message)
            //Call methods writeEnvelope
            writeEnvelope((Message) p);

        if (p.isMimeType("multipart/*")) {
            System.out.println("This is a Multipart");
            System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();

            String strMultiPart = (String) mp.getBodyPart(1).getContent();
            Document docMultiPart = Jsoup.parse(strMultiPart);
            Element magicLinkURL = docMultiPart.select("a").first();
            String parsedLink = magicLinkURL.toString();
            String codeSnippet = parsedLink.substring(72, 104); //106
            return codeSnippet;


        } else {
            String html = (String) p.getContent();
            Document doc = Jsoup.parse(html);
            Element magicLinkURL = doc.select("a").first();
            String parsedLink = magicLinkURL.toString();
            String codeSnippet = parsedLink.substring(72, 104);  //106
//            System.out.println(codeSnippet);
            return codeSnippet;
        }

    }


//      This method would print SENDDATE, FROM,TO and SUBJECT of the message

    public static void writeEnvelope(Message m) throws Exception {
        Address[] a;

        Date sendDate = m.getSentDate();
        System.out.println(sendDate);

        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());
    }


    // This method retrieves sendDate for needed for checking the email with valid magic link

    public Date retrieveSendDate(Message m) throws Exception {

        Date sendDate = m.getSentDate();
        MyLogger.log.info("Trying to retrieve the email send date: ");
        return sendDate;
    }


    public String getSender(Message m) throws Exception {
        Address[] a;
        String sender = "";
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                sender = a[j].toString();
        }
        return sender;

    }

    public boolean isRightMail() { //IF SENDER = noreply@janrain.com and mailTime<start time of the test returns TRUE!!!

        Date testStart = Hooks.testStartDate;
        MyLogger.log.info("The test started at: " + testStart);

        try {
            MyLogger.log.info("Trying to find the sender and the send date");
            String sender = getSender(fetch(host, mailStoreType, usermail, password));
            MyLogger.log.info("The sender is:" + sender);
            Date mailDate = retrieveSendDate(fetch(host, mailStoreType, usermail, password));
            MyLogger.log.info("The email date is:" + mailDate);
            if ((testStart.compareTo(mailDate) < 0)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean returnStatusOfEmail() throws FileNotFoundException {
        boolean status = false;
        int counter = 0; // NUMBER OF ATTEMPTS OF GETTING THE RIGHT EMAIL

        while ((!status) || (counter == 5)) {
            status = isRightMail();
            MyLogger.log.info("The status is " + status);
            counter++;

            if (status) {
                try {
                    MyLogger.log.info("The email arrived");
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (counter == 5) {
                break;                       // BREAKS THE SCRIPT AND RETURNS "Email didn't arrive";
            } else {
                try {
                    Thread.sleep(2000); // WAITING FOR 2 SECONDS, THAN TRYING TO GET THE SNIPPET AGAIN

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        MyLogger.log.info("The email did not arrive");
        return false;
    }
}



