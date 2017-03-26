package com.briup.chap12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = serverSocket.accept();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader bReader2 = new BufferedReader(new FileReader("F:\\apache-tomcat-6.0.29/" +
																"webapps/ROOT/index.html"));
		//BufferedWriter bWriter = new BufferedWriter(new FileWriter(bReader2)));
		String string;
		while(!(string=bReader.readLine()).equals("exit")){
			if(!(string=bReader.readLine()).equals("127.0.0.1:8888"))
				System.out.println("请输入正确的IP地址及端口号。。。");
			else{
				//bReader2.read();
			}
		}
		bReader.close();
		bReader2.close();
		serverSocket.close();
	}
}
