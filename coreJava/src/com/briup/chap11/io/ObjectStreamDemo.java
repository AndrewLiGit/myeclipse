package com.briup.chap11.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ObjectStreamDemo {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//选择流
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		//初始化
		File file = new File("src/com/briup/chap11/Object.txt");
		
			try {
				if(!file.exists())
					file.createNewFile();
				oos = new ObjectOutputStream(new FileOutputStream(file));
				ois = new ObjectInputStream(new FileInputStream(file));
				Student stu1 = new Student(1,"tom",20,true);
				Student stu2 = new Student(2,"jack",10,true);
				Map<Integer,Student> map = new HashMap<Integer, Student>();
				map.put(stu1.getId(),stu1);
				map.put(stu2.getId(),stu2);
				//序列化操作
				oos.writeObject(map);
				oos.writeObject(stu1);
				oos.writeObject(stu2);
				
				//序列化中获取对象的顺序依赖于存放的顺序
				//反序列化
				map = (Map<Integer, Student>) ois.readObject();
				Student stu = map.get(2);
				Student s1 = (Student) ois.readObject();
				Student s2 = (Student) ois.readObject();
				System.out.println("name:"+stu.getName()+" age:"+stu.getAge());
				System.out.println("name:"+s1.getName()+" age:"+s1.getAge());
				System.out.println("name:"+s2.getName()+" age:"+s2.getAge());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
					try {
						if(oos!=null)oos.close();
						if(ois!=null)ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
	}
}