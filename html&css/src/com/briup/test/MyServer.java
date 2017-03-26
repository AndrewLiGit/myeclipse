package com.briup.test;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8888);
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
		PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
		byte[] buf = new byte[1024];
		int len = is.read(buf);
		System.out.println(new String(buf,0,len));
		pw.println("success");
		pw.close();
		is.close();
	}
}
