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
			log.info("���ݲɼ�");
			coll = gi.gather();
			log.info("��������");
			clientImpl.send(coll);
			log.info("���ݷ������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("���ݷ����쳣");
			e.printStackTrace();
		}
	}
}
