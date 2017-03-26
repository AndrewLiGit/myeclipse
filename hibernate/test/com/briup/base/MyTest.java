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
	
	@Test//session的缓存测试
	public void session_cache2(){
		
		try {
			Session session = sf.openSession();
			
			//不仅把数据查询出来放到对象里面,而且还把对象放到了缓存中
			Car car1 = (Car)session.get(Car.class, 1L);
			
			System.out.println(car1);
			
			System.out.println("-----------------");
			
			/*
			 * 这个操作是不会发出select语句查询的,因为要查询的对象在缓存已经存在
			 * 操作方式:
			 * 1.先看缓存中有没有要查询的对象,主要是根据查询的id值去比较
			 * 2.如果缓存中有这个对象,那么就拿出来用
			 * 3.如果缓存中没有这个对象,那么再发出select进行数据库查询
			 * 
			 */
			Car car2 = (Car)session.get(Car.class, 1L);
			System.out.println(car2);
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//session的缓存测试
	public void session_cache3(){
		
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.把car这个对象放到session对象内部的缓存中
			 * 2.在缓存中,生成一条和这个save操作相对应的sql语句:insert ...
			 */
			session.save(car);
			
			//不执行select语句,因为缓存中存在这个对象
			Car car1 = (Car)session.get(Car.class, 1L);
			System.out.println(car1);
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session的flush方法
	public void session_flush(){
		/*
		 * flush():清理缓存
		 * 清理缓存:强制把当前缓存中没有执行的sql语句(insert/update/delete),拿到数据库中执行
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			session.save(car);
			System.out.println("hello");
			/*
			 * 注意:
			 * flush方法只是把sql语句拿到数据库中执行
			 * 但是并没有提交事务
			 * 提交事务有俩种方式:
			 * 1.tran.commit();
			 * 2.session.close();
			 */
			session.flush();
			System.out.println("world");
			/*
			 * flush之后,
			 * commit()方法还是会尝试着把缓存中的sql语句拿到数据库中执行
			 * 但是这时候却发现缓存中语句没有sql语句,因为刚刚flush过,
			 * sql语句已经被强制拿到数据库执行了
			 * 但是commit()还是可以提交事务的
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session的flush方法
	public void session_flush2(){
		/*
		 * flush():清理缓存
		 * 清理缓存:强制把当前缓存中没有执行的sql语句(insert/update/delete),拿到数据库中执行
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			session.save(car);
			System.out.println("hello");
			/*
			 * 注意:
			 * flush方法只是把sql语句拿到数据库中执行
			 * 但是并没有提交事务
			 * 提交事务有俩种方式:
			 * 1.tran.commit();
			 * 2.session.close();
			 */
			session.flush();
			System.out.println("world");
			/*
			 * flush之后,
			 * commit()方法还是会尝试着把缓存中的sql语句拿到数据库中执行
			 * 但是这时候却发现缓存中语句没有sql语句,因为刚刚flush过,
			 * sql语句已经被强制拿到数据库执行了
			 * 但是commit()还是可以提交事务的
			 */
//			tran.commit();
			//session关闭的时候,也是会提交事务的
//			session.close();
			/*
			 * 这个时候只执行了flush()方法
			 * 缓存中的sql语句已经拿到数据库执行了
			 * 
			 * 但是事务没有提交,因为tran.commit();和session.close();都注释了
			 * 最终的结果是:有sql语句打印出来,但是数据库中没有数据
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session的flush方法
	public void session_flush3(){
		/*
		 * flush():清理缓存
		 * 清理缓存:强制把当前缓存中没有执行的sql语句(insert/update/delete),拿到数据库中执行
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.car对象放到缓存中
			 * 2.缓存中生成对应sql语句:insert
			 */
			session.save(car);
			System.out.println("hello");
			//强制把缓存中的insert语句拿到数据库中执行
			session.flush();
			System.out.println("world");
			//car对象在缓存中,改变了car的属性值,那么就会生成一个对应的update
			car.setName("test");
			/*
			 * 1.把缓存中刚刚生成的update语句拿到数据库中执行
			 * 2.提交事务
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//session的clear方法
	public void session_clear(){
		/*
		 * clear():清空缓存
		 * 1.缓存中的对象清空
		 * 2.缓存中是sql语句清空
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",100000);
			/*
			 * 1.car对象放到缓存中
			 * 2.缓存中生成对应sql语句:insert
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
