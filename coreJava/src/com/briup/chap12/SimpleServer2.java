package com.briup.chap12;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(10000);
			while(true){
				Socket socket = serverSocket.accept();
				new ServerThread(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread{
	private Socket socket;

	public ServerThread(Socket socket) {
		//super();
		this.socket = socket;
	}
	
	public void run(){
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));
			String str = dis.readUTF();
			System.out.println("客户端说："+str);
			dos.writeUTF("你好。。。");
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}