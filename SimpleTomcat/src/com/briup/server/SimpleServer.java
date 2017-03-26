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
	//构建默认的编码格式GBK
	private Charset charset = Charset.forName("GBK");
	//构建默认池倍数
	private final static int POOL_SIZE=4;
	//编码
	public ByteBuffer encode(String str){
		return charset.encode(str);
	}
	//解码
	public String decode(ByteBuffer buff){
		return charset.decode(buff).toString();
	}
	//初始化ServerSocketChannel并且设置缓存池大小
	public void server(){
		//初始化缓存池大小
		//缓存池大小等于当前运行的处理器大小的4倍
		//Executors
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
		try {
			serverSocketChannel = ServerSocketChannel.open();
			//再次连接使用原先的地址ip
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(9999));
			log.info("服务器开始，端口9999");
			//System.out.println("服务器开始，端口9999");
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
	//通过内部类Handler来实现具体操作
	class Handler implements Runnable{
		public void run(){
			
		}
		public Handler(){
			
		}
	}
}
