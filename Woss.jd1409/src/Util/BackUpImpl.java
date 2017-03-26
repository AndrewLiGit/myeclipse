package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import com.briup.util.BIDR;
import com.briup.util.BackUP;

public class BackUpImpl implements BackUP{

	String path = "src/File/data.bak";
	@Override
	public void init(Properties pro) {
		this.path = pro.getProperty("path");
	}

	@Override
	public Object load(String name, boolean boo) throws Exception {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(String name, Object obj, boolean boo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<BIDR> load(){
		
		ObjectInputStream ois = null;
		Collection<BIDR> bidrs = null;
		
		File file = new File(path);
		if(file.length()<1)
			clear();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Object obj = ois.readObject();
			
			if(obj!=null)
			bidrs = (Collection<BIDR>) obj;
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return bidrs;
	}

	public void store(Collection<BIDR> coll){
		
		ObjectOutputStream oos = null;
		
		File file = new File(path);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		//load()方法返回原来备份的数据（对象bidr）信息的集合
		Collection<BIDR> c = load();
		//System.out.println(c.isEmpty());
		if(!c.isEmpty()){
			//把这次要备份的数据集合(coll)添加到原来备份的数据的集合（c）中
			c.addAll(coll);
			//把原来备份的数据集合和本次要备份的数据集合（现在的c）赋给马上要写入备份文件的集合coll
			//这样就实现了把本次要备份的数据集合备份到备份文件中，而备份文件原本的数据又不会丢失
			coll = c;
		}
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(coll);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clear(){
		ObjectOutputStream oos = null;
		File file = new File(path);
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new Vector());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
