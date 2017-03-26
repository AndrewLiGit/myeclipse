package com.briup.chap11.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferedSRWDemo {
	public static void main(String[] args) {
		//选择流
		FileReader fr = null;
		FileWriter fw = null;
		FileOutputStream fos = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			//初始化操作
			fr = new FileReader
				("src/com/briup/chap11/BufferedSRWDemo.java");
			fw = new FileWriter
				("src/com/briup/chap11/BufferedSRWDemo.bat");
			fos = new FileOutputStream
				("src/com/briup/chap11/BufferedSRWDemo.bat");
			//封装 前两个都是字符封装 第三个字节和字符封装
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(fos);
			
			char[] buff = new char[128];
			String len;
			int len2;
			//readLine()逐行读取数据 返回值String类型
			/*while((len=br.readLine())!=null) {
				//bw.write(len);
				pw.print(len);
			}*/
			//read(buff)逐字符读取数据存放如buff中,返回值int类型
			while ((len2=br.read(buff))!=-1) {
				System.out.print(new String(buff,0,len2));
			}
			bw.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(br!=null)br.close();
					if(bw!=null)bw.close();
					if(pw!=null)pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
