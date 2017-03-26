package com.briup.parse.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	public void createDom(String file){
		Document document = DocumentHelper.createDocument();
		//添加根元素
		Element root = document.addElement("root");
		Element city = root.addElement("city");
		Element attribute = city.addAttribute("name", "上海");
		Element num = city.addElement("peopleNumber");
		num.addText("23343");
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xw=new XMLWriter(new FileOutputStream(new File(file)),format);
			xw.write(document);
			xw.flush();
			xw.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void readXML(String filePath){
		
		try {
			//获得一个SAXReader对象
			SAXReader reader = new SAXReader();
			File file = new File(filePath);
			//读取xml文件
			Document document = reader.read(file);
			//获得文档中的根节点
			Element rootElement = document.getRootElement();
			//获得根节点下面所有的子节点
			List<Element> elements = rootElement.elements();
			
			//遍历这个集合拿到每一个子节点
			for(Element e:elements){
				
				System.out.println("当前节点的名字:"+e.getName());
				String v = e.attributeValue("name");
				System.out.println("当前节点中name属性的值:"+v);
				
				//获得当前这个子节点下面所有的子节点
				List<Element> elements2 = e.elements();
				
				//遍历elements2这个集合中每一个节点
				for(Element e2:elements2){
					String text = e2.getText();
					System.out.println("当前节点的名字:"+e2.getName()+"以及节点的文本值为"+text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Dom4jTest t = new Dom4jTest();
		String filePath = "src/com/briup/parse/dom4j/test.xml";
//		t.createDom(filePath);
		t.readXML(filePath);
	}
}
