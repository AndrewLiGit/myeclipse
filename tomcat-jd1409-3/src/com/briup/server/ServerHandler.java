package com.briup.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import com.briup.util.PropFinder;

public class ServerHandler implements Runnable{
	private Socket socket;
	//服务器目录
	private String path;
	private String errorURL;
	private String welcomeURL;
	
	public ServerHandler (Socket socket){
		this.socket = socket;
		path = PropFinder.getProp("path");
		errorURL = PropFinder.getProp("errorURL");
		welcomeURL = PropFinder.getProp("welcomeURL");
		new Thread(this).start();
	}
	@Override
	public void run() {
		//分析请求
		try {
			//socket 输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//
			PrintStream out = new PrintStream(socket.getOutputStream());
			//GET /index.html HTTP/1.1
			String head = br.readLine();
			if(head!= null){
				String[] arr = head.split(" ");
				//获取用户的请求
				String url = arr[1];
				if(url.equals("/")){
					url = welcomeURL;
				}
				//给出回应
				File file = new File(path,url);
				if(file.exists()){
					out.println("HTTP/1.1 200 ok");
					out.println();
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					byte[] buff = new byte[1024];
					int len;
					while((len = bis.read(buff))!=-1){
						out.write(buff, 0, len);
						out.flush();
					}
					out.close();
					bis.close();
				}else{
					out.println("HTTP/1.1 404 Not Found");
					out.println();
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path,errorURL)));
					byte[] buff = new byte[1024];
					int len;
					while((len = bis.read(buff))!=-1){
						out.write(buff, 0, len);
						out.flush();
					}
					out.close();
					bis.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
