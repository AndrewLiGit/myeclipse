package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import Util.ConfigurationImpl;
import Util.LoggerIm;

import com.briup.util.BIDR;
import com.briup.woss.server.Server;

public class ServerImpl implements Server{

	private int port = 4321;
	private int backlog = 50;
	private LoggerIm log = null;
	
	
	
	@Override
	public void init(Properties pro) {
		this.port = Integer.parseInt(pro.getProperty("port"));
		this.backlog = Integer.parseInt(pro.getProperty("backlog"));
	}
//使用单线程  下面有多线程的实现方法
	@Override
	public Collection<BIDR> revicer() throws Exception {
		// TODO Auto-generated method stub
//		ServerSocket serverSocket = new ServerSocket(port);
//		while(true){
//			Socket socket = serverSocket.accept();
//			InputStream is = socket.getInputStream();
//			ObjectInputStream ois = new ObjectInputStream(is);
//			@SuppressWarnings("unchecked")
//			Collection<BIDR> coll = (Collection<BIDR>) ois.readObject();
//			dbStoreImpl.saveToDB(coll);
//		}
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
//使用多线程
	public Collection<BIDR> revicer1() throws Exception {
		// TODO Auto-generated method stub
		 ConfigurationImpl con = new ConfigurationImpl();
		log = (LoggerIm) con.getLogger();
		ServerSocket serverSocket = new ServerSocket(port,backlog);
		while(true){
			Socket socket = serverSocket.accept();
			new NetServerSS(socket);
			log.info("本次数据接收完毕并入库");
		}
	}
}

class NetServerSS extends Thread{
	private Socket socket;
	private Collection<BIDR> bidrs ;
	private DBStoreImpl dbStoreImpl = new DBStoreImpl();
	
	public NetServerSS(Socket socket){
		this.socket = socket;
		start();
	}
		
	@SuppressWarnings("unchecked")
	public void run(){
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			bidrs = (Collection<BIDR>) ois.readObject();
			dbStoreImpl.saveToDB(bidrs);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
