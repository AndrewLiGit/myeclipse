package test.main;

import java.util.Collection;

import com.briup.util.BIDR;

import Client.ClientImpl;
import Client.GatherImpl;
import Util.ConfigurationImpl;
import Util.LoggerIm;

public class Client {

	public static void main(String[] args) {
		ConfigurationImpl  con = new ConfigurationImpl();
		LoggerIm log= null;
		GatherImpl gi = null;
		ClientImpl clientImpl = null;
		Collection<BIDR> coll = null;
		try {
			log = (LoggerIm) con.getLogger();
			
			gi = (GatherImpl) con.getGather();
			clientImpl = (ClientImpl) con.getClient();
			log.info("数据采集");
			coll = gi.gather();
			log.info("发送数据");
			clientImpl.send(coll);
			log.info("数据发送完毕");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("数据发送异常");
			e.printStackTrace();
		}
	}
}
