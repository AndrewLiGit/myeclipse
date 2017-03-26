package com.briup.base;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.briup.bean.Car;

public class MyTest {
	private Configuration cfg;
	private SessionFactory sf;
	
	@Before
	public void before(){
		
		try {
			cfg = new Configuration();
			cfg.configure();
			sf = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After 
	public void after(){
		
		try {
			sf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void autoCreateTable(){
		
		try {
			SchemaExport se = new SchemaExport(cfg);
			se.create(false, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void firstHibernate (){
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Car car = new Car(1L, "BYD", 400000L);
			session.save(car);
			tran.commit();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void save(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Car car1 = new Car(1, "BYD", 400000);
			Car car2 = new Car(2, "QQ", 200000);
			session.save(car1);
			session.save(car2);
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Car car = new Car(2, "QQ", 100000);
//			Car car = new Car();
//			car.setId(2);
//			car.setPrice(50000);
//			session.save(car);
			session.update(car);
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Car car = new Car();
			car.setId(2);
			session.delete(car);
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void get(){
		
		try {
			Session session = sf.openSession();
//			System.out.println(session.get(Car.class, 1L));
			System.out.println("Hello");
			Car car = (Car)session.get(Car.class,1L);
			System.out.println("World");
			System.out.println(car);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void load(){
		
		try {
			Session session = sf.openSession();
			Car car = (Car)session.load(Car.class, 1L);
			System.out.println("Hello");
			System.out.println(car);
			System.out.println("World");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void session_cache1(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			Car car = new Car(1,"QQ",300000);
			session.save(car);
			car.setName("test1");
			car.setName("test2");
			System.out.println("Hello");
			tran.commit();
			System.out.println("World");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session�Ļ������
	public void session_cache2(){
		
		try {
			Session session = sf.openSession();
			
			//���������ݲ�ѯ�����ŵ���������,���һ��Ѷ���ŵ��˻�����
			Car car1 = (Car)session.get(Car.class, 1L);
			
			System.out.println(car1);
			
			System.out.println("-----------------");
			
			/*
			 * ��������ǲ��ᷢ��select����ѯ��,��ΪҪ��ѯ�Ķ����ڻ����Ѿ�����
			 * ������ʽ:
			 * 1.�ȿ���������û��Ҫ��ѯ�Ķ���,��Ҫ�Ǹ��ݲ�ѯ��idֵȥ�Ƚ�
			 * 2.������������������,��ô���ó�����
			 * 3.���������û���������,��ô�ٷ���select�������ݿ��ѯ
			 * 
			 */
			Car car2 = (Car)session.get(Car.class, 1L);
			System.out.println(car2);
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//session�Ļ������
	public void session_cache3(){
		
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.��car�������ŵ�session�����ڲ��Ļ�����
			 * 2.�ڻ�����,����һ�������save�������Ӧ��sql���:insert ...
			 */
			session.save(car);
			
			//��ִ��select���,��Ϊ�����д����������
			Car car1 = (Car)session.get(Car.class, 1L);
			System.out.println(car1);
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session��flush����
	public void session_flush(){
		/*
		 * flush():������
		 * ������:ǿ�ưѵ�ǰ������û��ִ�е�sql���(insert/update/delete),�õ����ݿ���ִ��
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			session.save(car);
			System.out.println("hello");
			/*
			 * ע��:
			 * flush����ֻ�ǰ�sql����õ����ݿ���ִ��
			 * ���ǲ�û���ύ����
			 * �ύ���������ַ�ʽ:
			 * 1.tran.commit();
			 * 2.session.close();
			 */
			session.flush();
			System.out.println("world");
			/*
			 * flush֮��,
			 * commit()�������ǻ᳢���Űѻ����е�sql����õ����ݿ���ִ��
			 * ������ʱ��ȴ���ֻ��������û��sql���,��Ϊ�ո�flush��,
			 * sql����Ѿ���ǿ���õ����ݿ�ִ����
			 * ����commit()���ǿ����ύ�����
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session��flush����
	public void session_flush2(){
		/*
		 * flush():������
		 * ������:ǿ�ưѵ�ǰ������û��ִ�е�sql���(insert/update/delete),�õ����ݿ���ִ��
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			session.save(car);
			System.out.println("hello");
			/*
			 * ע��:
			 * flush����ֻ�ǰ�sql����õ����ݿ���ִ��
			 * ���ǲ�û���ύ����
			 * �ύ���������ַ�ʽ:
			 * 1.tran.commit();
			 * 2.session.close();
			 */
			session.flush();
			System.out.println("world");
			/*
			 * flush֮��,
			 * commit()�������ǻ᳢���Űѻ����е�sql����õ����ݿ���ִ��
			 * ������ʱ��ȴ���ֻ��������û��sql���,��Ϊ�ո�flush��,
			 * sql����Ѿ���ǿ���õ����ݿ�ִ����
			 * ����commit()���ǿ����ύ�����
			 */
//			tran.commit();
			//session�رյ�ʱ��,Ҳ�ǻ��ύ�����
//			session.close();
			/*
			 * ���ʱ��ִֻ����flush()����
			 * �����е�sql����Ѿ��õ����ݿ�ִ����
			 * 
			 * ��������û���ύ,��Ϊtran.commit();��session.close();��ע����
			 * ���յĽ����:��sql����ӡ����,�������ݿ���û������
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session��flush����
	public void session_flush3(){
		/*
		 * flush():������
		 * ������:ǿ�ưѵ�ǰ������û��ִ�е�sql���(insert/update/delete),�õ����ݿ���ִ��
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.car����ŵ�������
			 * 2.���������ɶ�Ӧsql���:insert
			 */
			session.save(car);
			System.out.println("hello");
			//ǿ�ưѻ����е�insert����õ����ݿ���ִ��
			session.flush();
			System.out.println("world");
			//car�����ڻ�����,�ı���car������ֵ,��ô�ͻ�����һ����Ӧ��update
			car.setName("test");
			/*
			 * 1.�ѻ����иո����ɵ�update����õ����ݿ���ִ��
			 * 2.�ύ����
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session��clear����
	public void session_clear(){
		/*
		 * clear():��ջ���
		 * 1.�����еĶ������
		 * 2.��������sql������
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",100000);
			/*
			 * 1.car����ŵ�������
			 * 2.���������ɶ�Ӧsql���:insert
			 */
			session.save(car);
			
			session.clear();
//			session.save(car);
			System.out.println(car);
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
