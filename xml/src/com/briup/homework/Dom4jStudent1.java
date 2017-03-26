package com.briup.homework;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jStudent1 {

	public static void main(String[] args) {
		String name=null;
		int age=0;
		String city=null;
		String ageString=null;
		
		Student student = new Student();
		String filePath = "src/com/briup/homework/student.xml";
		
		try {
			SAXReader reader = new SAXReader();
			File file = new File(filePath);
			Document document = reader.read(file);
			Element rootElement = document.getRootElement();
			List<Element> elements = rootElement.elements();
			
			for(Element e:elements){
				
				
				String idString = e.attributeValue("id");
				int id = Integer.valueOf(idString);
//				System.out.println(id);
				student.setId(id);
//				System.out.println(e.getName());
				List<Element> elements2 = e.elements();
				
				
				for(Element e2:elements2){
//					System.out.println("---");
//					System.out.println(e2.getName());
//					System.out.println(e2.getText());
//					System.out.println("++++");
					if(e2.getName().equals("name"))
						name = e2.getText();
					else if(e2.getName().equals("age")){
//						System.out.println(e2.getName());
						ageString = e2.getText();
//						System.out.println(ageString);
						age = Integer.valueOf(ageString);
					}
						else
							city = e2.getText();
					
					student.setName(name);
					student.setAge(age);
					student.setCity(city);
				}
				System.out.println(student);
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
