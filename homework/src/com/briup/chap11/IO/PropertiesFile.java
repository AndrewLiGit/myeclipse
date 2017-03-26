package com.briup.chap11.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PropertiesFile {
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(new File("src/message"));
			br = new BufferedReader(fr);
			
			System.out.println("Please input a variable:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			String temp;
	
			while((temp=br.readLine())!=null){
				String[] str = strToArr(temp);
				if(input.equals(str[0])){
					System.out.println(str[1]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String[] strToArr(String temp) {
		String[] strings = temp.split("=");
		return strings;
	}
}
