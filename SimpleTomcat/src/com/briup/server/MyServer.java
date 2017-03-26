package com.briup.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.log4j.Logger;

public class MyServer extends Thread{
	private Socket client;
	private InputStream in;
	private PrintStream out;
	//构建根路径相当于Tomcat的webapps
	String path = "F:\\apache-tomcat-6.0.29/webapps/ROOT";
	Logger logger = Logger.getLogger("mylogger");
	public MyServer(){}
	public MyServer(Socket client){
		this.client = client;
		try {
			in = client.getInputStream();
			out = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void  run() {
		try {
			//parse(in)获得url地址
			String str = parse(in);
			//将url 地址传入sendMessage()方法，将file内容展示页面
			sendMessage(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//解析传入的内容  获取file的地址url
	public String parse(InputStream in) throws Exception{
		//将传入的字节流转换成字符流
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//str  method url version 
		String str = br.readLine();
		logger.info("request resource:"+str);
		//如果请求的路径最后没有文件结尾，默认login.html
		String[] s = str.split(" ");
		String method = s[0];
		String url = s[1];
		String version = s[2];
		//substring获得某个字段
		if((url.substring(url.length()-1,url.length())).endsWith("/")){
			url = "/index.html";
		}
		return url;
	}
	//发送数据  页面显示内容
	public void sendMessage(String url) throws Exception{
		//通过url构建file 如果存在 返回200 显示内容
		//如果不存在， 返回404及相关错误信息
		//path父路径  url子路径
		File file = new File(path,url);
		if(!file.exists()){
			out.println("http/1.1 404");
			out.println();
			out.println("<center><h1>404</h1>");
			out.println("File Not Found</center>");
			out.flush();
			out.close();
		}
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] buff = new byte[1024];
		int len = 0;
		while((len = bis.read(buff))!=-1){
			out.println("http/1.1 200 ok");
			out.println();
			out.write(buff);
		}
		out.flush();
		out.close();
	}
}
