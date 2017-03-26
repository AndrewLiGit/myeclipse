package com.briup.chap11.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class StreamRWDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			//构建并初始化
			fis = new FileInputStream("src/com/briup/chap11/BufferedStreamDemo.java");
			fos = new FileOutputStream("src/com/briup/chap11/test.txt");
			//使用inputstreamreader（）将字节流转换为字符流
			br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
			//使用outputstreamwriter（）将字符流转换为字节流
			bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			//读写 操作
			String line = "";
			while((line = br.readLine())!=null){
				System.out.println(line);
				bw.write(line+"\n");
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
