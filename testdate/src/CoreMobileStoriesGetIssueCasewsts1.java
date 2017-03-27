import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class CoreMobileStoriesGetIssueCasewsts1 {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		getOBFeedURL();
		
		timerFlag = Boolean.parseBoolean(PropertyUtil.getProperty("enableTimer"));
        if (timerFlag) {
            Timer startTimer = new Timer();

            startTimer.schedule(new TimerTask() {
            	int num = 0;
                public void run() {
                    try {
                    	parseXML();
						num ++;
						System.out.println("This is NO。" + num + " times, " + " guid = " + guid);
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }, 0, 1000 * 10);
        } else {
        	parseXML();
        }
	}

	private static void getOBFeedURL() {
		// TODO Auto-generated method stub
		try {
            urlString = PropertyUtil.getProperty("url3");
            if (urlString == "" && urlString == null) {
                urlString = "http://www.wcpo.com/content-syndication-portlet/feedServlet?obfType=CORE_MOBILE_STORIES&siteId=10015&categoryId=10018&startTime=2016-11-04-09:50:01&endTime=2016-11-04-10:00:00";
            }
        } catch (Exception e) {
            System.out.println("failed to read the file");
            e.printStackTrace();
        }
	}
	
	private static void parseXML() throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		getXMLDoc();
		String paid = processItemXML();
		Date currentDate = new Date();
		if (paid.equals("0")){
//			outPutIssuecase();
			String message = "  --------------------------------------------------Get issue case. current time: " + currentDate + "  ----------------------------------" + "guid = " + guid;
			outPutMessage(message);
			
		} else if (paid.equals("1")) {
			String message = "   success. current time: " + currentDate + "    guid = " + guid;
			outPutMessage(message);
		}
		
	}

	private static void outPutMessage(String message) {
		// TODO Auto-generated method stub
		BufferedWriter writer=null;
		try{
//			File file = new File(PropertyUtil.getProperty("messageUrl"));
//			fos=new FileOutputStream(file,true);
//			fos.write(message.getBytes());
			
			 try {
			    writer=new BufferedWriter(new  FileWriter(PropertyUtil.getProperty("messageUrlwsts1"), true));
			 } catch (IOException e1) {
			    e1.printStackTrace();
			 }
			 writer.append(message);
			 writer.newLine();
			 writer.flush();
		}catch(IOException e){
			   System.out.println("--------IO异常----------");
		}finally {
			 try{
				 writer.close();
			 }catch(IOException e){
				 System.out.println("------被要关闭的文件不存在-------");
			 }
		}
	}

	private static void outPutIssuecase() {
		// TODO Auto-generated method stub
		BufferedInputStream in = null;  
        BufferedOutputStream out = null;          
        try {  
            in = new BufferedInputStream(is);
            long currentTime = System.currentTimeMillis();
            out = new BufferedOutputStream(new FileOutputStream(PropertyUtil.getProperty("filePath") + currentTime + ".xml"));  
              
            byte[] b = new byte[1024];  
            while(in.read(b) != -1) {  
                out.write(b);  
            }  
        }catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }finally {  
            if(in != null) {  
                try {  
                    in.close();  
                }catch (Exception e) {  
                    // TODO: handle exception  
                    e.printStackTrace();  
                }  
            }  
            if(out != null) {  
                try {  
                    out.close();  
                }catch (Exception e) {  
                    // TODO: handle exception  
                    e.printStackTrace();  
                }  
            }  
        }  
	}

	private static String processItemXML() throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);
        Element element = document.getDocumentElement();
        NodeList itemList = element.getElementsByTagName("item");
        Element itemElement = (Element) itemList.item(0);

        NodeList paidNode = itemElement.getElementsByTagName("paid");
        String paid = paidNode.item(0).getTextContent();
        NodeList guidNode = itemElement.getElementsByTagName("guid");
        guid = guidNode.item(0).getTextContent();
        
        return paid;
	}

	private static void getXMLDoc() {
		// TODO Auto-generated method stub
		URL url;
        try {
            url = new URL(urlString);
            
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PropertyUtil.getProperty("proxyhost"),
                    Integer.parseInt(PropertyUtil.getProperty("proxypost"))));

            HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
            uc.connect();

            is = uc.getInputStream();
           
        } catch (Exception e) {
        	String message = "Trying to get network error, Please check your network connection and try again";
            outPutMessage(message);
            return;
        }
	}

    private static InputStream is = null;
    private static boolean timerFlag;
    private static String urlString = "";
    private static String guid = "";
}
