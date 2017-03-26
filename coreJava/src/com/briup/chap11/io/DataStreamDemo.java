package com.briup.chap11.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamDemo {
	public static void main(String[] args) {
		//ѡ���ض���
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			//��ʼ��
			dos = new DataOutputStream(new FileOutputStream
					("src/com/briup/chap11/Data.bat"));
			dis = new DataInputStream(new FileInputStream
					("src/com/briup/chap11/Data.bat"));
			//ִ�ж� д ����
			dos.writeBoolean(true);
			dos.writeInt(10);
			dos.writeDouble(10.0);
			dos.writeUTF("Hello World!");
			dos.flush();
			
			boolean a = dis.readBoolean();
			int b = dis.readInt();
			double c = dis.readDouble();
			String d = dis.readUTF();
			System.out.println("a:"+a+" b:"+b
					+" c:"+c+" d:"+d);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(dos!=null)dos.close();
					if(dis!=null)dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
