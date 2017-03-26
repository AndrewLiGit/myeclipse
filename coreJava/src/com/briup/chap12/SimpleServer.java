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
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) {
		System.out.println("========服务端========");
		//构建serversocket(port)
		try {
			ServerSocket ss=new ServerSocket(10000);
			//建立socket链接
			Socket socket = ss.accept();
			//构建流对象
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			DataInputStream dis = new DataInputStream(bis);
			DataOutputStream dos = new DataOutputStream(bos);
			InputStreamReader ir= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(ir);
			String str;
			while(true){
				str= dis.readUTF();
				System.out.println("对方说："+str);
				if(str.equals("bye")){
					System.out.println("对方说再见，聊天结束");
					break;
				}
				str=br.readLine();
				dos.writeUTF(str);
				dos.flush();
				if(str.equals("bye")){
					System.out.println("自己说再见，聊天结束");
					break;
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
