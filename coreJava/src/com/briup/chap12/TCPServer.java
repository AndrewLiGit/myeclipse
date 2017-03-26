package com.briup.chap12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket(8880);
		Socket socket = serverSocket.accept();
		System.out.println("=====服务开启=====");
		BufferedReader in=null;
		BufferedWriter out=null;
		try {
			 in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 out =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			 Scanner scanner=new Scanner(System.in);
			 String message;
			 while(!(message=in.readLine()).equals("exit")){
				System.out.println("收到消息："+message);
				String reply=scanner.nextLine();
				out.write(reply);
				out.newLine();
				out.flush();
			}
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)
					in.close();
				if(out!=null)
					out.close();
				socket.close();
				System.out.println("======服务关闭======");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		serverSocket.close();
	}
}
