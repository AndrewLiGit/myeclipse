package com.briup.chap12;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8989);
			Socket socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream("src/test.txt");
			byte[] b = new byte[1024];
			int len;
			while((len=in.read(b))!=-1){
				fos.write(b,0,len);
			}
			fos.close();
			in.close();
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
