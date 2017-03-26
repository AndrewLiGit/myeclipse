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
	//������·���൱��Tomcat��webapps
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
			//parse(in)���url��ַ
			String str = parse(in);
			//��url ��ַ����sendMessage()��������file����չʾҳ��
			sendMessage(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�������������  ��ȡfile�ĵ�ַurl
	public String parse(InputStream in) throws Exception{
		//��������ֽ���ת�����ַ���
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//str  method url version 
		String str = br.readLine();
		logger.info("request resource:"+str);
		//��������·�����û���ļ���β��Ĭ��login.html
		String[] s = str.split(" ");
		String method = s[0];
		String url = s[1];
		String version = s[2];
		//substring���ĳ���ֶ�
		if((url.substring(url.length()-1,url.length())).endsWith("/")){
			url = "/index.html";
		}
		return url;
	}
	//��������  ҳ����ʾ����
	public void sendMessage(String url) throws Exception{
		//ͨ��url����file ������� ����200 ��ʾ����
		//��������ڣ� ����404����ش�����Ϣ
		//path��·��  url��·��
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
