package com.briup.chap12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	public static <T> void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",8880);
		BufferedReader bIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter bOut=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println("===�������ͨ��======");
		Scanner scanner =new Scanner(System.in);
		String message;
		System.out.println("�뷢����Ϣ or exit��");
		while(!(message=scanner.nextLine()).equals("exit")){
			bOut.flush();
			bOut.write(message);
			bOut.newLine();
			bOut.flush();
			String receive=bIn.readLine();
			System.out.println("�ظ�\t"+receive);
			System.out.println("---------------");
			System.out.println("�뷢����Ϣ or exit:");
		}
		bOut.write(message);
		bOut.newLine();
		bOut.flush();
		bOut.close();
		bIn.close();
		socket.close();
		scanner.close();
		System.out.println("====ͨ������=====");
		
	}
}
