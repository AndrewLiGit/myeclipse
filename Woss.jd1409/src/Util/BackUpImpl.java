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
		//load()��������ԭ�����ݵ����ݣ�����bidr����Ϣ�ļ���
		Collection<BIDR> c = load();
		//System.out.println(c.isEmpty());
		if(!c.isEmpty()){
			//�����Ҫ���ݵ����ݼ���(coll)��ӵ�ԭ�����ݵ����ݵļ��ϣ�c����
			c.addAll(coll);
			//��ԭ�����ݵ����ݼ��Ϻͱ���Ҫ���ݵ����ݼ��ϣ����ڵ�c����������Ҫд�뱸���ļ��ļ���coll
			//������ʵ���˰ѱ���Ҫ���ݵ����ݼ��ϱ��ݵ������ļ��У��������ļ�ԭ���������ֲ��ᶪʧ
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
