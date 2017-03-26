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
			System.out.println("�ļ������ڡ�����");
			System.exit(0);
		}
//		FileInputStream fis = new FileInputStream(a);//��ȡ�ֽ���
//		InputStreamReader isr = new InputStreamReader(fis);//�ֽ���ͨ���ַ�������������ȡ�ֽڲ�����Ϊ�ַ���
//		BufferedReader br = new BufferedReader(isr);//�����
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
				bw.write("      ������ʾ����ʮ����̨������쵼��ѡ�ٽ��վ��У���������Ӣ�š����򳤣���ѡ�ܲ�ͳ������\n�ѻ�ó���700����ѡƱ������������ѡ\n");
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
