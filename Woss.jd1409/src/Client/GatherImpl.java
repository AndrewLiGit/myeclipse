package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.briup.util.BIDR;
import com.briup.woss.client.Gather;

public class GatherImpl implements Gather{
	
	Map<String, BIDR> map = new HashMap<String, BIDR>();
	Map<String, Long> num = new HashMap<String, Long>();
	List<BIDR> list = new ArrayList<BIDR>();
	public String path="src/File/radwtmp";
	public String path2 = "src/File/data";
	public String path3 = "src/File/pointer";
	Map<Map<String, BIDR>, Map<String, Long>> map2 = new HashMap<Map<String,BIDR>, Map<String,Long>>();
	File file = new File(path2);
	File file1 = new File(path3);
	@Override
	public void init(Properties pro) {
		this.path = pro.getProperty("path");
		this.path2 = pro.getProperty("path2");
		this.path3 = pro.getProperty("path3");
	}
	
	public void save(){
		Map<String, BIDR> mapGi = getMap();
		Map<String, Long> numGi = getNum();
		map2.put(mapGi, numGi);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(map2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void get(){
		ObjectInputStream ois = null;
		if(file.length()>0){
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				@SuppressWarnings("unchecked")
				Map<Map<String, BIDR>, Map<String, Long>> map = (Map<Map<String, BIDR>, Map<String, Long>>) ois.readObject();
				Set<Entry<Map<String, BIDR>, Map<String, Long>>> set = map.entrySet();
				Iterator<Entry<Map<String, BIDR>, Map<String, Long>>> iter = set.iterator();
				while(iter.hasNext()){
					Map.Entry<Map<String, BIDR>, Map<String, Long>> entry = iter.next();
					setMap(entry.getKey());
					setNum(entry.getValue());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				if(ois!=null)
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	@Override
	public Collection<BIDR> gather(){
		
		File file = new File(path);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			BufferedReader br = new BufferedReader(new FileReader(file1));
			BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
			//读取上次读取radwtmp
			long pointer = 0;
			String string = null;
			while((string = br.readLine())!= null){
				pointer = Long.parseLong(string);
				raf.seek(pointer);
			}
			br.close();
			get();
			String line = null;
			while((line=raf.readLine())!=null){
				//分割读取的数据
				String[] arr = line.split("[|]");
				BIDR bidr = new BIDR();
				String name = arr[0];
				String state = arr[2];
				long time =Long.parseLong(arr[3]);
				String ip = arr[4];
				//7是上线   8是下线   判断是上线还是 下线
				if(state.equals("7")){
					//判断map集合中是否有此IP的信息
					if(!map.containsKey(ip)){
						
						bidr.setAAA_login_name(name);
						bidr.setLogin_date(new Timestamp(time*1000));
						bidr.setLogin_ip(ip);
						bidr.setNAS_ip(ip);
						map.put(ip, bidr);
						num.put(ip, 1l);
					}else{
						Long value = num.get(ip);
						value = value+1;
						num.put(ip, value);
					}
					
				}
				if(state.equals("8")){
					long value = num.get(ip);
					value--;
					num.put(ip, value);
					Long l = num.get(ip);
					if(l==0){
						bidr = map.get(ip);
						bidr.setLogout_date(new Timestamp(time*1000));
						Long allTime = bidr.getLogout_date().getTime()-bidr.getLogin_date().getTime();
						int deration = (int) (allTime/60000);
						bidr.setTime_deration(deration);
						//把bidr对象加入到list集合中
						list.add(bidr);
						//把完整数据的信息从map，num集合中remove掉
						map.remove(ip);
						num.remove(ip);
					}
				}
			}
			//把不完整的数据信息从map，num集合中保存到data文件中
			save();
			//把此次读取到的位置（偏移量）记录到pointer文件中
			pointer = raf.getFilePointer();
			bw.write(pointer+" ");
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(raf!=null) raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void setMap(Map<String, BIDR> map){
		this.map = map;
	}
	
	public Map<String, BIDR> getMap(){
		return map;
	}

	public void setNum(Map<String, Long> num){
		this.num = num;
	}
	
	public Map<String, Long> getNum(){
		return num;
	}
	
}
