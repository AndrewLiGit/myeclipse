package com.briup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.briup.util.PropFinder;

public class ServerMain {
	private Integer port;
	public ServerMain(){
		this.port = Integer.parseInt(PropFinder.getProp("port"));
	}
	public void receive(){
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("我的"+port+"服务器开启");
			while(true){
				Socket socket = server.accept();
				new ServerHandler(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ServerMain serverMain = new ServerMain();
		serverMain.receive();
	}
}
