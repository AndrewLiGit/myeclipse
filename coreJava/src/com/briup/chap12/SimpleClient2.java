package com.briup.chap12;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient2 {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",10000);
			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(iStream));
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(oStream));
			dos.writeUTF("你好，我是Tom");
			dos.flush();
			String string = dis.readUTF();
			System.out.println("服务器说："+string);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
