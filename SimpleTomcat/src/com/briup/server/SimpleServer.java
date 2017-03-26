package com.briup.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.briup.logger.Log;


public class SimpleServer {
	private Log log;
	private ServerSocketChannel serverSocketChannel = null;
	private SocketChannel socketChannel = null;
	//����Ĭ�ϵı����ʽGBK
	private Charset charset = Charset.forName("GBK");
	//����Ĭ�ϳر���
	private final static int POOL_SIZE=4;
	//����
	public ByteBuffer encode(String str){
		return charset.encode(str);
	}
	//����
	public String decode(ByteBuffer buff){
		return charset.decode(buff).toString();
	}
	//��ʼ��ServerSocketChannel�������û���ش�С
	public void server(){
		//��ʼ������ش�С
		//����ش�С���ڵ�ǰ���еĴ�������С��4��
		//Executors
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
		try {
			serverSocketChannel = ServerSocketChannel.open();
			//�ٴ�����ʹ��ԭ�ȵĵ�ַip
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(9999));
			log.info("��������ʼ���˿�9999");
			//System.out.println("��������ʼ���˿�9999");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void server(String string){
		while(true){
			socketChannel = serverSocketChannel.accept();
			executorService.execute(new Handler(socketChannel));
			
		}
	}*/
	//ͨ���ڲ���Handler��ʵ�־������
	class Handler implements Runnable{
		public void run(){
			
		}
		public Handler(){
			
		}
	}
}
