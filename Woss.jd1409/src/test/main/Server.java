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
			log.info("���ݽ���");
			server.revicer1();
			log.info("���ݽ�����ϲ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("���ݽ����쳣������쳣");
			e.printStackTrace();
		}
	}
}
