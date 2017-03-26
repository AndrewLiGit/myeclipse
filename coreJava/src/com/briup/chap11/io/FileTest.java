package com.briup.chap11.io;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) {
		File file = new File("src/hello.bat");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsoluteFile());
		try {
			System.out.println(file.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//file.mkdir();
		System.out.println(file.getName());
		//file.renameTo(new File("src/word.bat"));
		System.out.println(file.renameTo(new File("src/word.bat")));
		file.mkdir();
		File file2 = new File("src");
		String[] str = file2.list();
		for(String s:str){
			System.out.println(s);
		}
	}
}
