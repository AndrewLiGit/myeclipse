package Client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import Util.BackUpImpl;

import com.briup.util.BIDR;
import com.briup.woss.client.Client;

public class ClientImpl implements Client{

	private String address ="127.0.0.1";
	private Integer port = 4321;
	BackUpImpl backUpImpl = new BackUpImpl();
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
		this.address = pro.getProperty("address");
		this.port = Integer.parseInt(pro.getProperty("port"));
	}

	@Override
	public void send(Collection<BIDR> coll) throws Exception {
		// TODO Auto-generated method stub
		//备份数据
		backUpImpl.store(coll);
		
		Socket socket = new Socket(address, port);
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(coll);
		oos.flush();
		oos.close();
		os.close();
		socket.close();
	}

}
