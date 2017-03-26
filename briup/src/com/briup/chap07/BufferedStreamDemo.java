package com.briup.chap07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamDemo {
	public static void main(String[] args) {
		//�������ѡ��
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			//���ĳ�ʼ��
			fis = new FileInputStream
					("src/com/briup/chap07/BufferedStreamDemo.java");
			fos = new FileOutputStream
					("src/com/briup/chap07/BufferedStreamDemo.bat");
		
			//���ķ�װ
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			byte[] buff = new byte[128];
			int len = 0;
			while((len=bis.read(buff))!=-1) {
				
				System.out.print(new String(buff,0,len));
			}
			while ((len=bis.read())!=-1) {
				bos.write(len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(bis!=null)bis.close();
					if(bos!=null)bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
