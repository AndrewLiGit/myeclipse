package org.apache.commons.lang3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

public class Temp {

    private static String url = "https://www.vibescm.com/api/subscription_campaigns/803631/send_alert.xml";

    private static String user = "ahaber@lakana.com";
    private static String pass = "0ffsh0r3";
    public static String getXmlToSend() {

    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
 		String sendTime = dateFormat.format(new Date());
    	StringBuilder scheduleSMSXML = new StringBuilder();
        scheduleSMSXML.append("<message><description>");
        scheduleSMSXML.append("This is a sample message description");
        scheduleSMSXML.append("</description><text>");
        scheduleSMSXML.append("test message");
        scheduleSMSXML.append("</text><send_at>");
        scheduleSMSXML.append(sendTime);
        scheduleSMSXML.append("</send_at>");
        scheduleSMSXML.append("<filters>");
    	scheduleSMSXML.append("<attribute_paths><attribute_path><all>");
    	scheduleSMSXML.append("location/1149");
    	scheduleSMSXML.append("</all></attribute_path></attribute_paths>");
        scheduleSMSXML.append("</filters></message>");
        
        return scheduleSMSXML.toString();
    }



    public static String getAuthorizationHeader() {
        String userPass = user + ":" + pass;
        return "Basic " + DatatypeConverter.printBase64Binary(userPass.getBytes());

    }
    
   
    public static void main(String[] args) throws Exception {


    	System.out.println(getAuthorizationHeader());
    	HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            post.setHeader("Content-type", "text/xml");

            post.setHeader("Authorization", getAuthorizationHeader());

            post.setEntity(new StringEntity(getXmlToSend()));

            HttpResponse response = client.execute(post);

            System.out.println("-------------------");

            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

            System.out.println("Response: " + response.getStatusLine() + "\n");

            HttpEntity entity = response.getEntity();

            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String xmlOut = br.readLine();

            System.out.println(xmlOut);

        } catch (Exception ex) {
            System.out.println("Something went wrong:" + ex.toString());
            ex.printStackTrace();
        }*/
    }
}