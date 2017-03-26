package com.briup.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jStudent {
	
	public static Map<Integer, Student> map;
	
	public void createXml(String file){
		
		Document document = DocumentHelper.createDocument();
		Element students = document.addElement("students");

		Set<Integer> set = map.keySet();
		
		for(Integer i:set){
			Element student = students.addElement("student");
			student.addAttribute("id",i+"");
			Element name = student.addElement("name");
			name.addText(map.get(i).toString(1));
			Element age = student.addElement("age");
			age.addText(map.get(i).toString(2));
			Element city = student.addElement("city");
			city.addText(map.get(i).toString(3));
		}
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xw = new XMLWriter(new FileOutputStream(new File(file)),format);
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Student s1 = new Student(1,"tom",20,"上海");
		Student s2 = new Student(2,"jack",21,"北京");
		Student s3 = new Student(3,"lily",22,"南京");
		Student s4 = new Student(4,"jon",23,"苏州");
		
		map = new HashMap<Integer, Student>();
		map.put(1, s1);
		map.put(2, s2);
		map.put(3, s3);
		map.put(4, s4);
		
		Dom4jStudent d = new Dom4jStudent();
		String file = "src/com/briup/homework/student.xml";
		d.createXml(file);
	}
}
