package Util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.WossModule;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;

public class ConfigurationImpl implements Configuration{
	
	private Map<String, WossModule>  map = null;
	private Properties pro;
	
	public ConfigurationImpl(){
		this("src/File/conf.xml");
	}
	
	public ConfigurationImpl(String path){
		SAXReader sax = new SAXReader();
		File file = new File(path);
		map = new HashMap<String, WossModule>();
		pro = new Properties();
		Document doc = null;
		Element root = null;
		try {
			doc = sax.read(file);
			root = doc.getRootElement();
			for(Object ele:root.elements()){
				Element el = (Element) ele;
				String name = el.getName();
				String classname = el.attributeValue("class");
//				System.out.println(name+"="+classname);
				//woss是新创建的实例对象
				WossModule woss = (WossModule) Class.forName(classname).newInstance();
//				System.out.println(Class.forName(classname));
//				System.out.println(woss);
				map.put(name, woss);
				for(Object ele2:el.elements()){
					Element e2 = (Element) ele2;
					String key = e2.getName();
					String value = e2.getText();
//					System.out.println(key+" "+value);
					pro.put(key, value);
				}
				woss.init(pro);
				pro.clear();
			}
			for(Object o:map.values()){
				if(o instanceof ConfigurationAWare){
					((ConfigurationAWare) o).setConfiguration(this);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public BackUP getBackup() throws Exception {
		return (BackUP) map.get("BackUp");
	}

	@Override
	public Client getClient() throws Exception {
		return (Client) map.get("Client");
	}

	@Override
	public DBStore getDBStore() throws Exception {
		return (DBStore) map.get("DBStore");
	}

	@Override
	public Gather getGather() throws Exception {
		return (Gather) map.get("Gather");
	}

	@Override
	public Logger getLogger() throws Exception {
		return (Logger) map.get("Logger");
	}

	@Override
	public Server getServer() throws Exception {
		return (Server) map.get("Server");
	}

//	public static void main(String[] args) {
//		ConfigurationImpl con = new ConfigurationImpl();
//		try {
//			System.out.println(con.getDBStore());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		GatherImpl gather = null;
////		try {
////			gather = (GatherImpl) con.getGather();
////			System.out.println(gather);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
}
