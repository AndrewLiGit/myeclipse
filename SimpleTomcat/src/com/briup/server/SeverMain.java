package com.briup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

public class SeverMain {
	//�����߳�
	public void startServer(){
		Logger log = Logger.getLogger("mylogger");
		//����serversocket  ����socket  ����socket�߳�
		
		try {
			ServerSocket ss = new ServerSocket(7878);
			log.info("serverSocket port message:"+ss.getLocalPort());
			while(true){
				Socket s = ss.accept();
				log.info("new accept socket address:"+s.getInetAddress()+":"+s.getPort());
				MyServer myServer = new MyServer(s);
				myServer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
