package core.helpers;

import core.watchers.MyLogger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JavaHelpers {

    /**
     * GENERATES A FIXED NUMBERS OF CHARS USING RANDOM CHARACTERS
     *
     * @param size length of random string
     * @return
     */
    public static String typeRandomCharacters(int size) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
//        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }


    public static String typeRandomNumbers(int size) {
        char[] chars = {1,2,3,4,5,6,7,8,9,0};
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * CONVERT A STRING OF TYPE "123" TO AN INT
     *
     * @param string string that needs to be converted to int
     * @return
     */
    public static int converStringToInt(String string) {
        try {
            Integer x = Integer.valueOf(string);
        } catch (NumberFormatException e) {
            MyLogger.log.info("In String :" + string + "string was a combination of letters+ints or other special characters");
        }
        Integer x = Integer.valueOf(string);
        MyLogger.log.info("Conversion was made successfully :" + x);
        return x;
    }

    /**
     * PARSE AN ARRAY OF STRINGS
     *
     * @param array array of strings
     * @param index desired position
     * @return
     */
    public String parceListForString(String[] array, int index) {
        List<String> lList = Arrays.asList(array);
        return lList.get(index);
    }


    /**
     * PARSE A XML FILE AND GET TEXT VALUES FOR DESIRED ELEMENTS
     *
     * @param pathToXML path to you xml
     * @param category  which contains desired elements ex: <company>
     * @param attribute value of desired sub category ex: <salary>100000</salary>
     */
    public String getAttributesFromXml(String pathToXML, String category, String attribute) throws ParserConfigurationException, IOException, SAXException {
        String xlmAttribute = null;
        File fXmlFile = new File(pathToXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(category);

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                xlmAttribute = eElement.getElementsByTagName(attribute)
                        .item(0).getTextContent();
            }
        }

        return xlmAttribute;
    }

    /**
     * Get the response for a certain url
     *
     * @param myURL - desired url to get the response
     * @return
     */
    private static String getUrlResponse(String myURL) {
        MyLogger.log.info("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

    /**
     * PARSE A HTML RESPONSE AND GET ALL HEADERS
     *
     * @param url - DESIRED URL TO BE PARSED
     */
    public static void parseHTMLResponseGetHeaders(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            MyLogger.log.info("Printing Response Header...\n");
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                MyLogger.log.info("Key : " + header.getName()
                        + " ,Value : " + header.getValue());
            }
            MyLogger.log.info("\nGet Response Header By Key ...\n");
            String server = response.getFirstHeader("Server").getValue();

            if (server == null) {
                MyLogger.log.info("Key 'Server' is not found!");
            } else {
                MyLogger.log.info("Server - " + server);
            }
            MyLogger.log.info("\n Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DOWNLOAD HTML PAGE
     *
     * @param url         - DESIRED URL
     * @param desiredpath - PATH TO SAVE FILE; EX: "/Users/admin/Desktop/testHtml.html"
     * @return
     * @throws Exception
     */
    private static File downloadPage(String url, String desiredpath) throws Exception {
        final Connection.Response response = Jsoup.connect(url).execute();
        final org.jsoup.nodes.Document doc = response.parse();
        final File f = new File(desiredpath);
        FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
        return f;
    }

    /**
     * PARSE HTML FILE AND SAVE IT LOCALLY WITH DESIRED EXTENSTION
     *
     * @param url
     * @param desiredpath
     * @param filename
     * @param extension
     * @return
     */
    public static Document parseHtmlFile(String url, String desiredpath, String filename, String extension) {
        org.jsoup.nodes.Document htmlFile = null;
        try {
            htmlFile = Jsoup.parse(JavaHelpers.downloadPage(url, desiredpath + "/" + filename + "." + extension), "ISO-8859-1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // right
        catch (Exception e) {
            e.printStackTrace();
        }
        return htmlFile;

    }

    /**
     * WRITE HTML TO FILE
     *
     * @param url
     * @param desiredpath
     * @param filename
     * @param extension
     */
    public static void writeHTMLToFile(String url, String desiredpath, String filename, String extension) {
        String html = getUrlResponse(url);
        File f = new File(desiredpath + "/" + filename + "." + extension);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(html);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deteleNewTypeOfVouchers(String customerID) {
        OkHttpClient client = new OkHttpClient();

        Request request3 = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/gs_lhi-2nx6p9y/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "39b59ceb-6c0c-dc26-3d94-4063374b264a")
                .build();

        Request request = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/vouchers/gs_lhi-2nx6p9y/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "788e3598-80c0-b8a5-27a5-c70260826357")
                .build();

        Request request2 = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/vouchers/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d3d74bae-61f0-f432-56c7-71f23ad5c415")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Response response2 = client.newCall(request2).execute();
            Response response3 = client.newCall(request3).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOldTypeVoucher(String customerID) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/InflightTest%2AInflightTest/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "00fa6347-27ee-ffc1-5c57-113aa89cdc5a")
                .build();

        Request request2 = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/vouchers/InflightTest%2AInflightTest/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "22b3e207-7d60-3ff4-5549-55aef013df4d")
                .build();

        Request request3 = new Request.Builder()
                .url("https://global.telekom.com/gcp-web-api/10000381/customers/" + customerID + "/attributes/vouchers/")
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic cGZMNFppVXJ6djphOWY5NDBhZS1kYzA1LTRlZjEtYWQ3YS1kYzkxNTYxM2Y5ZmE=")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d3d74bae-61f0-f432-56c7-71f23ad5c415")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Response response2 = client.newCall(request2).execute();
            Response response3 = client.newCall(request3).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

