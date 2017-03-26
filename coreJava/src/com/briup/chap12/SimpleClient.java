package com.briup.chap12;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main(String[] args) {
		System.out.println("========�ͻ���========");
		//����socket
		try {
			Socket socket = new Socket("127.0.0.1",10000);
			//ͨ��socket��ö�Ӧ��ѡ����
			//getInputStream
			//getOutputStream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			DataOutputStream dos = new DataOutputStream(bos);
			DataInputStream dis = new DataInputStream(bis);
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(ir);
			String str;
			while(true){
				str=br.readLine();
				dos.writeUTF(str);
				dos.flush();
				if(str.equals("bye")){
					System.out.println("�Լ�˵�ټ����������");
					break;
				}
				str=dis.readUTF();
				System.out.println("�Է�˵��"+str);
				if(str.equals("bye")){
					System.out.println("�Է�˵�ټ����������");
					break;
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

