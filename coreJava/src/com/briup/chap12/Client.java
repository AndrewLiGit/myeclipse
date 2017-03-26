package com.briup.chap12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",8989);
			OutputStream outputStream = socket.getOutputStream();
			FileInputStream fiStream = new FileInputStream("src/tt.txt");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader bReader = new BufferedReader(isr);
			FileWriter fos = new FileWriter("src/tt.txt");
			BufferedWriter bWriter = new BufferedWriter(fos);
			String string;
			while((string = bReader.readLine())!=null&&!(string.equals("over"))){
				bWriter.write(string);
				bWriter.newLine();
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
			byte[] buff = new byte[1024];
			int len;
			while((len=fiStream.read(buff))!=-1){
				outputStream.write(buff,0,len);
			}
			outputStream.close();
			fiStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
