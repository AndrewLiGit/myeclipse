package com.briup.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.briup.bean.Car;

//Hibernate����֪ʶ�Ĳ���
public class HibernateBase {
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
	public void firstHibernate(){
		
		try {
			//��һ��  ����Configuration����
			Configuration cfg = new Configuration();
			
			//�ڶ���  ��ȡ�����ļ�
			//���Զ��Ķ�ȡsrc�������ֽ���hibernate.cfg.xml�������ļ�
			//��Ϊ�����ļ��еĵ��������Ѿ�д����ӳ���ļ���·��,����ӳ���ļ�Ҳ�ᱻ�Զ���ȡ��
			cfg.configure();
			
			//������  ����Sessionfactory�ӿ����͵Ķ���
			SessionFactory sf = cfg.buildSessionFactory();
			
			//���Ĳ�  ����Session�ӿ����͵Ķ���
			Session session = sf.openSession();
			
			//���岽  �������� ���һ��������� �Ա�֮����ύ���߻ع�
			Transaction tran = session.beginTransaction();
			
			//������  ִ����Ҫ��ɵĲ���(���� ɾ�� ���� ����)
			Car car = new Car(2,"BMW",600000);
			session.save(car);
			
			//���߲�  �ύ����
			tran.commit();
			
			//�ڰ˲�  �ر���Դ
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
			
			Car car = new Car(1,"BMW",400000);
			session.save(car);
			
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
			
			Car car = new Car(1,"BMW123",320000);
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
			car.setId(1);
			
			session.delete(car);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Test//get������ѯ����
	public void get(){
		
		Session session = sf.openSession();
		
		//��ѯCar���������Ӧ�ı�������ֵΪ1������
		System.out.println("hello");
		Car car = (Car)session.get(Car.class,1L);
		System.out.println("world");
		
		System.out.println(car);
		
		session.close();
	}
	
	/*
	 * get������load����������:
	 * 
	 * get����:
	 *   1.����session.get()������ʱ��,�ͻᷢ��select���������ݵĲ�ѯ
	 *   2.�����ѯ�����������,��ô�Ͱ����ݷ�װ����Ӧ���͵Ķ��󷵻�
	 *   3.��������ѯ����,����null
	 * 
	 * load����:
	 *   1.����session.load()������ʱ��,�����ᷢ��select����ѯ����
	 *   2.������Ĵ�����,��������Ҫ�õ�����ѯ�����ݵĻ�,�Ż�ȥִ��select�����в�ѯ
	 *   ����:
	 *   Car car = (Car)session.load(Car.class,1L);//��ִ��select���
	 *   String name = car.getName();//��ʱ��ᷢ��select���в�ѯ,��Ϊ��ʱ�����Ҫ�����������
	 *   3.������ݲ�ѯ����,������ʹ������������е�ֵ,��ʱ��ᱨ��
	 *   4.load�������������,���� �ӳټ���
	 * 
	 */
	
	@Test//load������ѯ����
	public void load(){
		
		try {
			Session session = sf.openSession();
			
			//��ѯCar���������Ӧ�ı�������ֵΪ1������
			Car car = (Car)session.load(Car.class,2L);
			
			System.out.println("hello");
			System.out.println(car);
			System.out.println("world");
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//session�Ļ������
	public void session_cache1(){
		
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.��car�������ŵ�session�����ڲ��Ļ�����
			 * 2.�ڻ�����,����һ�������save�������Ӧ��sql���:insert ...
			 */
			session.save(car);
			
			/*
			 * car�����ڻ�����,�����޸���car�����name����ֵ,
			 * ��ô�����оͻ���������������һ����Ӧ��sql���:update
			 * 
			 */
			car.setName("test");
			//������Ϊ�������ɵ�update�����ϵĸ�������
			//����ֻ��һ��update���
			car.setName("test2");
			
			/*
			 * Ĭ�������:
			 * commit��������������:
			 * 1.�ѻ����л�û��ִ�е�sql���(insert/update/delete)�õ����ݿ���ִ��
			 * 2.�ѵ�ǰ��������ύ
			 * 
			 */
			System.out.println("hello");
			tran.commit();
			System.out.println("world");
			
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
			//tran.commit();
			//session�رյ�ʱ��,Ҳ�ǻ��ύ�����
//			session.close();
			
			/*
			 * ���ʱ��ִֻ����flush()����
			 * �����е�sql����Ѿ��õ����ݿ�ִ����
			 * 
			 * ��������û���ύ,��Ϊtran.commit();��session.close();��ע����
			 * ���յĽ����:��sql����ӡ����,�������ݿ���û������
			 * 
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
		 * 2.�����е�sql������
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",300000);
			/*
			 * save()������
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

	@Test//saveOrUpdate����
	public void saveOrUpdate(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW123",400000);
			
			//���car�����idֵ�����ݿ���д���,���������ִ��update����
			//���car�����idֵ�����ݿ���в�����,���������ִ��inesrt����
			session.saveOrUpdate(car);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//merge����
	public void merge(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			
			//���car�����idֵ�����ݿ���д���,���������ִ��update����
			//���car�����idֵ�����ݿ���в�����,���������ִ��inesrt����
			session.merge(car);
			/*
			 * һ�������,merge������saveOrUpdate������һ����Ч��
			 * ������һЩ���������,����������������ͬ��
			 * ����һ�������в�������������
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//merge������saveOrUpdate�����Ĳ�ͬ
	public void merge_saveOrUpdate(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car1 = new Car(1,"BMW",400000);
			System.out.println(car1);
			//car1���󱻷ŵ�������
			session.save(car1);
			System.out.println(car1+"------");
			//car2��car1��������ͬ�Ķ���,�������ǵ�idֵ��ͬ
			Car car2 = new Car(1,"BMW123",400000);
			
			//car2���󱻷ŵ�������
//			session.update(car2);
//			session.saveOrUpdate(car2);
			System.out.println(car2);
			session.merge(car2);
			System.out.println(car2+"------");
			/*
			 * ���ֵ����:
			 * ͬһ��session������,
			 * ��������ͬ�Ķ���,
			 * �������ǵ�idֵ����ͬ��
			 * 
			 * ��ӳ����˱�
			 * ��ôһ����Ķ���Ͷ�Ӧ����һ������
			 * ������ͬ�Ķ���ʹ���������в�ͬ������
			 * �������������idֵ����ͬ,�����ζ�ű������в�ͬ������
			 * ���ǵ�idȴ��һ����,��������Ǿ��Բ������
			 * 
			 * ����saveOrUpdate()�����ͱ�����
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//���session����ķ��� openSession()
	public void session_open(){
		/*
		 *  openSession()���Ի��Session�ӿڵ�ʵ�������
		 *  �ص�:
		 *    1.���ܵ�ǰ�Ƿ��Ѿ�������һ�����õ�session,
		 *    openSession()���ᴴ��һ���µ�session���������
		 *    2.openSession()����������session����,������ʹ��
		 *    ����֮��,����Ҫ�ֶ��رյ�,��������������Զ��ر�
		 * 
		 */
		try {
			Session s1 = sf.openSession();
			Session s2 = sf.openSession();
			Session s3 = sf.openSession();
			
			System.out.println(s1==s2);//false
			System.out.println(s1==s3);//false
			System.out.println(s2==s3);//false
			
			s1.close();
			s2.close();
			s3.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	

	@Test//���session����ķ��� getCurrentSession()
	public void session_current(){
		/*
		 *  getCurrentSession()���Ի��Session�ӿڵ�ʵ�������
		 *  �ص�:
		 *    1.Ҫʹ������������session����,��ô����Ҫ��hibernate.cfg.xml
		 *    �ļ������һ��������Ϣ(���������ļ������еĵڶ�����):
		 *    <property name="current_session_context_class">thread</property>
		 *    ������õ���˼:hibernate���԰Ѵ������ֵ�session����ŵ���ǰ���߳�����,
		 *    �Ա����´ο���ֱ�Ӵ��߳����ó����session�������ʹ��
		 *    
		 *    2.�����ǰû�п���ʹ�õ�session����,getCurrentSession()����
		 *    �ᴴ��һ���µ�session����
		 *    3.�����ǰ��һ������ʹ�õ�session����,��ôgetCurrentSession()����
		 *    �ͻ�ֱ���õ����session��ʹ��,���������´����µ�session������
		 *    4. getCurrentSession()����������session����,�������ύ֮��,
		 *    ���Լ��Զ��Ĺر�,������Ҫ�����ֶ��Ĺر�session����
		 *    ���ĸ��ص�����������һ�������в���
		 *    
		 */
		try {
			Session s1 = sf.getCurrentSession();
			Session s2 = sf.getCurrentSession();
			Session s3 = sf.getCurrentSession();
			
			System.out.println(s1==s2);//true
			System.out.println(s1==s3);//true
			System.out.println(s2==s3);//true
			
			//ע��:���ʱ����û����������,���Ժ͵��ĸ��ص㲢����ͻ
			//s1.close();û����
			s1.close();
			//�������,s1 s2 s3��ͬһ������
			//s1.close()֮��,s2 s3�Ͳ���colse()
//			s2.close();
//			s3.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//���session����ķ��� getCurrentSession()
	public void session_current2(){
		/*
		 *  getCurrentSession()���Ի��Session�ӿڵ�ʵ�������
		 *  �ص�:
		 *    4. getCurrentSession()����������session����,�������ύ֮��,
		 *    ���Լ��Զ��Ĺر�,������Ҫ�����ֶ��Ĺر�session����
		 *    ���ĸ��ص�����������һ�������в���
		 *    
		 */
		try {
			Session s1 = sf.getCurrentSession();
			
			Transaction tran = s1.beginTransaction();
			
			Car car1 = new Car(1,"BMW",400000);
			s1.save(car1);
			
			tran.commit();
			
			//���� ��Ϊ�����ύ��ʱ��,���session���Զ��ر�
//			s1.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test//������ֻ��session����ķ�������
	public void session_open_current(){
		/*
		 * Session��һ���ӿ�
		 * openSession()�������ص�������ӿڵ�ĳһ��ʵ�������
		 *    �������ʵ������Xxx
		 *    class org.hibernate.impl.SessionImpl
		 * 
		 * getCurrentSession()�������ص�������ӿڵ�ĳһ��ʵ�������
		 *    �������ʵ������Yyy
		 *    class com.sun.proxy.$Proxy6
		 *  
		 * Xxx��Ķ����Yyy��Ķ���϶��ǲ���ȵ�
		 * 
		 *  �൱��:
		 *  Object o1 = new Student();
		 *  Object o2 = new Teacher();
		 *  o1==o2 �϶���false
		 *    
		 */
		try {
			
			Session s1 = sf.openSession();
			Session s2 = sf.getCurrentSession();
			System.out.println(s1.getClass());
			System.out.println(s2.getClass());
			
			System.out.println(s1==s2);//false
			
			s1.close();
//			s2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//hibernate�н���jdbc�Ĳ���
	public void session_jdbc(){
		try {
			
			Session session = sf.openSession();
			
			//��������Ѿ���ʱ,������ķ�ʽ������
			//session.connection();
			
			session.doWork(new Work(){

				@Override
				public void execute(Connection conn) throws SQLException {
					Statement stmt = conn.createStatement();
					
					String sql = "insert into t_car(id,car_name,car_price) values(1,'BWM',300000)";
					stmt.execute(sql);
					
				}
			});
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test//hibernate��idֵ���Զ���������
	public void idTest(){
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",300000);
			Car car2 = new Car(1,"BMW",300000);
			Car car3 = new Car(1,"BMW",300000);
			session.save(car);
			session.save(car2);
			session.save(car3);
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@Test//ʵ�������� ˲ʱ̬/����̬ Transient
	public void e_transient(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * 1.session�Ļ�������û���������          û��
			 * 2.���ݿ�����û�������������Ӧ�ļ�¼  û��
			 * 
			 */
			Car car = new Car(1,"BMW1",100000);
			//hibernate����֤˲ʱ̬��������Ա仯�����ݿ�ͬ��
			//����Ҳ������update�������ִ��
			car.setName("test");
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//ʵ�������� �־�̬ Persistent
	public void e_persistent(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * 1.session�Ļ�������û���������          ��
			 * 2.���ݿ�����û�������������Ӧ�ļ�¼  ��
			 * 
			 */
			Car car = (Car)session.get(Car.class, 1L);
//			
//			Car car2 = new Car(2,"BMW1",100000);
//			session.save(car2);
			
			//hibernate�ᱣ�־�̬��������Ա仯�����ݿ�ͬ��
			//���Ի���update�������ִ��
			car.setName("test");
			
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test//ʵ�������� �ѹ�̬/����̬ Detached
	public void e_detached(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * �ص�:
			 * 1.session�Ļ�������û���������          û��
			 * 2.���ݿ�����û�������������Ӧ�ļ�¼    ��
			 * 
			 */
			
			//��ʱ���ǳ־�̬
			Car car = (Car)session.get(Car.class, 1L);
			//clear��ͱ�����ѹ�̬ 
			//��Ϊsession�Ļ�����û���������
			//�������ݿ�������������Ӧ�ļ�¼
			session.clear();
			
			//hibernate����֤�ѹ�̬��������Ա仯�����ݿ�ͬ��
			//����Ҳ������update�������ִ��
			car.setName("test123");
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
