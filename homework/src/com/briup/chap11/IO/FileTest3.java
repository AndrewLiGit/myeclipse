package com.briup.chap11.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileTest3 {
	public static void main(String[] args) throws IOException {
		File a = new File("src/t.txt");
		if(!a.exists()){
			System.out.println("文件不存在。。。");
			System.exit(0);
		}
//		FileInputStream fis = new FileInputStream(a);//读取字节流
//		InputStreamReader isr = new InputStreamReader(fis);//字节流通向字符流的桥梁，读取字节并解码为字符。
//		BufferedReader br = new BufferedReader(isr);//缓冲池
//		FileOutputStream fos = new FileOutputStream(new File("a.txt"));
//		OutputStreamWriter osw = new OutputStreamWriter(fos,"GBK");
//		BufferedWriter bw = new BufferedWriter(osw);
		FileReader fr = new FileReader(a);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(new File("src/b.txt"));
		BufferedWriter bw = new BufferedWriter(fw);
		String string ;
		while((string=br.readLine())!=null){
			if(string.indexOf('*')!=-1){
				bw.write("      核心提示：第十二届台湾地区领导人选举今日举行，马萧（马英九、萧万长）竞选总部统计宣称\n已获得超过700万张选票，自行宣布当选\n");
				bw.flush();
			}else{
				bw.write(string);
				bw.write("\n");
				bw.flush();
			}
		}
		//bw.flush();
		bw.close();
		br.close();
	}
}
