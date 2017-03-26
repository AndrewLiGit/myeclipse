package com.briup.chap11.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			fis = new FileInputStream(new File("src/com/briup/chap11/BufferedStreamDemo.java"));
			fos = new FileOutputStream("src/com/briup/chap11/a.txt");
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			byte[] buff = new byte[128];
			int len = 0;
			while((len=bis.read(buff))!=-1){
				System.out.print(new String(buff,0,len));
				bos.write(buff, 0, len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null)fis.close();
				if(fos!=null)fos.close();
				if(bis!=null)bis.close();
				if(bos!=null)bos.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
