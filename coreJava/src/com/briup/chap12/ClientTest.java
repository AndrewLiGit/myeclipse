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
		System.out.println("������IP��ַ���˿ں�:");
		Scanner scanner = new Scanner(System.in);
		String string;
		while(!(string=scanner.nextLine()).equals("exit")){
			if((string=scanner.nextLine()).equals("127.0.0.1:8888"))
				bWriter.write(string);
			else{
				System.out.println("��������ȷ��IP��ַ���˿ں�:");
			}
		}
		System.out.println("��ֹ������");
		bReader.close();
		bWriter.close();
		socket.close();
		scanner.close();
	}
}
