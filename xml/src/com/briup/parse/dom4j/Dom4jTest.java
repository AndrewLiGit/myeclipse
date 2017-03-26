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
		//��Ӹ�Ԫ��
		Element root = document.addElement("root");
		Element city = root.addElement("city");
		Element attribute = city.addAttribute("name", "�Ϻ�");
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
			//���һ��SAXReader����
			SAXReader reader = new SAXReader();
			File file = new File(filePath);
			//��ȡxml�ļ�
			Document document = reader.read(file);
			//����ĵ��еĸ��ڵ�
			Element rootElement = document.getRootElement();
			//��ø��ڵ��������е��ӽڵ�
			List<Element> elements = rootElement.elements();
			
			//������������õ�ÿһ���ӽڵ�
			for(Element e:elements){
				
				System.out.println("��ǰ�ڵ������:"+e.getName());
				String v = e.attributeValue("name");
				System.out.println("��ǰ�ڵ���name���Ե�ֵ:"+v);
				
				//��õ�ǰ����ӽڵ��������е��ӽڵ�
				List<Element> elements2 = e.elements();
				
				//����elements2���������ÿһ���ڵ�
				for(Element e2:elements2){
					String text = e2.getText();
					System.out.println("��ǰ�ڵ������:"+e2.getName()+"�Լ��ڵ���ı�ֵΪ"+text);
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
