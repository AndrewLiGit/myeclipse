package com.briup.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {

//	public float tom;
//	public float jack;
//	public float terry;
//	public float marry;
//	public float ody;
	Map<String, Float> map; 
	
	public void readXml(String filepath){
		try {
			SAXReader reader = new SAXReader();
			File file = new File(filepath);
			Document document = reader.read(file);
			Element rootElement = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elements = rootElement.elements();
			
			map = new HashMap<String, Float>();
			for(Element e:elements){
				if(map.get(e.attributeValue("name"))==null){
					map.put(e.attributeValue("name"), Float.valueOf(e.getText()));
				}else{
					map.put(e.attributeValue("name"),(map.get(e.attributeValue("name"))+Float.valueOf(e.getText())));
				}
			}
			
//			for(Element e:elements){
//				String test = e.getText();
//				float a = Float.parseFloat(test);
//				if(e.attributeValue("name").equals("tom"))
//					tom+=a;
//				else if(e.attributeValue("name").equals("jack"))
//					jack+=a;
//				else if(e.attributeValue("name").equals("terry"))
//					terry+=a;
//				else if(e.attributeValue("name").equals("marry"))
//					marry+=a;
//				else if(e.attributeValue("name").equals("ody"))
//					ody+=a;
//			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void createDom(String file){
		Document document = DocumentHelper.createDocument();
		Element users = document.addElement("users");
		
		Set<String> set = map.keySet();
		for(String s:set){
			Element user = users.addElement("user");
			user.addAttribute("name", s);
			user.addText(map.get(s)+"");
		}
//		Element user1 = users.addElement("user");
//		user1.addAttribute("name", "tom");
//		user1.addText(tom+"");
//		
//		Element user2 = users.addElement("user");
//		user2.addAttribute("name", "jack");
//		user2.addText(jack+"");
//		
//		Element user3 = users.addElement("user");
//		user3.addAttribute("name", "terry");
//		user3.addText(terry+"");
//		
//		Element user4 = users.addElement("user");
//		user4.addAttribute("name", "marry");
//		user4.addText(marry+"");
//		
//		Element user5 = users.addElement("user");
//		user5.addAttribute("name", "ody");
//		user5.addText(ody+"");
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xw = new XMLWriter(new FileOutputStream(new File(file)),format);
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Dom4jTest d = new Dom4jTest();
		String file = "src/com/briup/homework/user.xml";
		String filepath = "src/com/briup/homework/user.bar.xml";
		d.readXml(file);
		d.createDom(filepath);
	}
}
