package test.main;

import Server.ServerImpl;
import Util.ConfigurationImpl;
import Util.LoggerIm;

public class Server {

	public static void main(String[] args) {
		ConfigurationImpl con = new ConfigurationImpl();
		LoggerIm log = null;
		ServerImpl server = null;
		try {
			log = (LoggerIm) con.getLogger();
			server = (ServerImpl) con.getServer();
			log.info("数据接收");
			server.revicer1();
			log.info("数据接收完毕并入库");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("数据接收异常或入库异常");
			e.printStackTrace();
		}
	}
}
