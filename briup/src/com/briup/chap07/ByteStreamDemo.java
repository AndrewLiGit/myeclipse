package com.briup.chap07;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo {
	public static void main(String[] args) throws IOException {
		demoRead();
		//System.out.println("abcdefg".getBytes());
		//demoWrite();
	}
	public static void demoRead() throws IOException{
		FileInputStream fis = new FileInputStream("src/com/briup/chap07/ByteStreamDemo.java");
		System.out.println(fis.available());
		
//		byte[] buf = new byte[fis.available()];
//		fis.read(buf);
//		System.out.println(new String(buf));
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=fis.read(buf))!=-1){
			System.out.println(new String(buf,0,len));
		}
		
		
	}
//	public static void demoWrite() throws IOException{
//		FileOutputStream fos = new FileOutputStream("src/com/briup/chap07/B.java");
//		fos.write("fdtrsxcf".getBytes());
//		fos.close();
//	}
}
