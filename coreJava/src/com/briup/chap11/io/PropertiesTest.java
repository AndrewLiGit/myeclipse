package com.briup.chap11.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties pro = new Properties();
		
		pro.setProperty("key","value");
		pro.load(new FileInputStream(new File("src/properties")));
		
		System.out.println("请输入key:");
		Scanner scanner  = new Scanner(System.in);
		String key;
		while(!(key=scanner.nextLine()).equals("bye")){
			System.out.println("对应的value:");
			System.out.println(pro.getProperty(key));
			System.out.println("请输入key:");
		}
		return;
	}
}
