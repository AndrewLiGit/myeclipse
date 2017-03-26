package com.briup.chap12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1",8888);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println("请输入IP地址及端口号:");
		Scanner scanner = new Scanner(System.in);
		String string;
		while(!(string=scanner.nextLine()).equals("exit")){
			if((string=scanner.nextLine()).equals("127.0.0.1:8888"))
				bWriter.write(string);
			else{
				System.out.println("请输入正确的IP地址及端口号:");
			}
		}
		System.out.println("终止。。。");
		bReader.close();
		bWriter.close();
		socket.close();
		scanner.close();
	}
}
