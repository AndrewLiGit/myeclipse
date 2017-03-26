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

//Hibernate基础知识的测试
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
			//第一步  创建Configuration对象
			Configuration cfg = new Configuration();
			
			//第二步  读取配置文件
			//会自动的读取src下面名字叫做hibernate.cfg.xml的配置文件
			//因为配置文件中的第三部分已经写上了映射文件的路径,所以映射文件也会被自动读取的
			cfg.configure();
			
			//第三步  创建Sessionfactory接口类型的对象
			SessionFactory sf = cfg.buildSessionFactory();
			
			//第四步  创建Session接口类型的对象
			Session session = sf.openSession();
			
			//第五步  开启事务 并且获得事务对象 以便之后的提交或者回滚
			Transaction tran = session.beginTransaction();
			
			//第六步  执行需要完成的操作(插入 删除 更新 查找)
			Car car = new Car(2,"BMW",600000);
			session.save(car);
			
			//第七步  提交事务
			tran.commit();
			
			//第八步  关闭资源
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
	
	
	
	
	
	@Test//get方法查询数据
	public void get(){
		
		Session session = sf.openSession();
		
		//查询Car这个类所对应的表中主键值为1的数据
		System.out.println("hello");
		Car car = (Car)session.get(Car.class,1L);
		System.out.println("world");
		
		System.out.println(car);
		
		session.close();
	}
	
	/*
	 * get方法和load方法的区别:
	 * 
	 * get方法:
	 *   1.调用session.get()方法的时候,就会发出select语句进行数据的查询
	 *   2.如果查询到了这个数据,那么就把数据封装成相应类型的对象返回
	 *   3.如果这个查询不到,返回null
	 * 
	 * load方法:
	 *   1.调用session.load()方法的时候,并不会发出select语句查询数据
	 *   2.在下面的代码中,如果有真的要用到所查询的数据的话,才会去执行select语句进行查询
	 *   例如:
	 *   Car car = (Car)session.load(Car.class,1L);//不执行select语句
	 *   String name = car.getName();//这时候会发出select进行查询,因为这时候真的要用这个数据了
	 *   3.如果数据查询不到,但是又使用了这个对象中的值,这时候会报错
	 *   4.load方法的这种情况,叫做 延迟加载
	 * 
	 */
	
	@Test//load方法查询数据
	public void load(){
		
		try {
			Session session = sf.openSession();
			
			//查询Car这个类所对应的表中主键值为1的数据
			Car car = (Car)session.load(Car.class,2L);
			
			System.out.println("hello");
			System.out.println(car);
			System.out.println("world");
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//session的缓存测试
	public void session_cache1(){
		
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			/*
			 * 1.把car这个对象放到session对象内部的缓存中
			 * 2.在缓存中,生成一条和这个save操作相对应的sql语句:insert ...
			 */
			session.save(car);
			
			/*
			 * car对象在缓存中,这里修改了car对象的name属性值,
			 * 那么缓存中就会针对这个操作生成一条对应的sql语句:update
			 * 
			 */
			car.setName("test");
			//可以认为是新生成的update语句把老的给覆盖了
			//最终只有一条update语句
			car.setName("test2");
			
			/*
			 * 默认情况下:
			 * commit方法做俩件事情:
			 * 1.把缓存中还没有执行的sql语句(insert/update/delete)拿到数据库中执行
			 * 2.把当前这个事务提交
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
			//tran.commit();
			//session关闭的时候,也是会提交事务的
//			session.close();
			
			/*
			 * 这个时候只执行了flush()方法
			 * 缓存中的sql语句已经拿到数据库执行了
			 * 
			 * 但是事务没有提交,因为tran.commit();和session.close();都注释了
			 * 最终的结果是:有sql语句打印出来,但是数据库中没有数据
			 * 
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
		 * 2.缓存中的sql语句清空
		 */
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",300000);
			/*
			 * save()方法：
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

	@Test//saveOrUpdate方法
	public void saveOrUpdate(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW123",400000);
			
			//如果car对象的id值在数据库表中存在,这个方法会执行update操作
			//如果car对象的id值在数据库表中不存在,这个方法会执行inesrt操作
			session.saveOrUpdate(car);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//merge方法
	public void merge(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car = new Car(1,"BMW",400000);
			
			//如果car对象的id值在数据库表中存在,这个方法会执行update操作
			//如果car对象的id值在数据库表中不存在,这个方法会执行inesrt操作
			session.merge(car);
			/*
			 * 一般情况下,merge方法和saveOrUpdate方法是一样的效果
			 * 但是在一些特殊情况下,俩个方法就有所不同了
			 * 下面一个方法中测试这个特殊情况
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//merge方法和saveOrUpdate方法的不同
	public void merge_saveOrUpdate(){
		try {
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			Car car1 = new Car(1,"BMW",400000);
			System.out.println(car1);
			//car1对象被放到缓存中
			session.save(car1);
			System.out.println(car1+"------");
			//car2和car1是俩个不同的对象,但是他们的id值相同
			Car car2 = new Car(1,"BMW123",400000);
			
			//car2对象被放到缓存中
//			session.update(car2);
//			session.saveOrUpdate(car2);
			System.out.println(car2);
			session.merge(car2);
			System.out.println(car2+"------");
			/*
			 * 出现的情况:
			 * 同一个session缓存中,
			 * 有俩个不同的对象,
			 * 但是他们的id值是相同的
			 * 
			 * 类映射成了表
			 * 那么一个类的对象就对应表中一行数据
			 * 俩个不同的对象就代表表中两行不同的数据
			 * 但是俩个对象的id值又相同,这就意味着表中俩行不同的数据
			 * 它们的id却是一样的,这种情况是绝对不允许的
			 * 
			 * 所以saveOrUpdate()方法就报错了
			 */
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//获得session对象的方法 openSession()
	public void session_open(){
		/*
		 *  openSession()可以获得Session接口的实现类对象
		 *  特点:
		 *    1.不管当前是否已经存在了一个可用的session,
		 *    openSession()都会创建一个新的session对象给我们
		 *    2.openSession()创建出来的session对象,在我们使用
		 *    完了之后,是需要手动关闭的,这个对象本身并不会自动关闭
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
	

	@Test//获得session对象的方法 getCurrentSession()
	public void session_current(){
		/*
		 *  getCurrentSession()可以获得Session接口的实现类对象
		 *  特点:
		 *    1.要使用这个方法获得session对象,那么必须要在hibernate.cfg.xml
		 *    文件中添加一个配置信息(属于配置文件内容中的第二部分):
		 *    <property name="current_session_context_class">thread</property>
		 *    这个配置的意思:hibernate可以把创建出现的session对象放到当前的线程里面,
		 *    以便于下次可以直接从线程中拿出这个session对象继续使用
		 *    
		 *    2.如果当前没有可以使用的session对象,getCurrentSession()方法
		 *    会创建一个新的session对象
		 *    3.如果当前有一个可以使用的session对象,那么getCurrentSession()方法
		 *    就会直接拿到这个session来使用,而不会重新创建新的session对象了
		 *    4. getCurrentSession()创建出来的session对象,在事务提交之后,
		 *    会自己自动的关闭,而不需要我们手动的关闭session对象
		 *    第四个特点我们在下面一个方法中测试
		 *    
		 */
		try {
			Session s1 = sf.getCurrentSession();
			Session s2 = sf.getCurrentSession();
			Session s3 = sf.getCurrentSession();
			
			System.out.println(s1==s2);//true
			System.out.println(s1==s3);//true
			System.out.println(s2==s3);//true
			
			//注意:这个时候是没有添加事务的,所以和第四个特点并不冲突
			//s1.close();没问题
			s1.close();
			//这个报错,s1 s2 s3是同一个对象
			//s1.close()之后,s2 s3就不能colse()
//			s2.close();
//			s3.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//获得session对象的方法 getCurrentSession()
	public void session_current2(){
		/*
		 *  getCurrentSession()可以获得Session接口的实现类对象
		 *  特点:
		 *    4. getCurrentSession()创建出来的session对象,在事务提交之后,
		 *    会自己自动的关闭,而不需要我们手动的关闭session对象
		 *    第四个特点我们在下面一个方法中测试
		 *    
		 */
		try {
			Session s1 = sf.getCurrentSession();
			
			Transaction tran = s1.beginTransaction();
			
			Car car1 = new Car(1,"BMW",400000);
			s1.save(car1);
			
			tran.commit();
			
			//报错 因为事务提交的时候,这个session会自动关闭
//			s1.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test//结合俩种获得session对象的方法测试
	public void session_open_current(){
		/*
		 * Session是一个接口
		 * openSession()方法返回的是这个接口的某一个实现类对象
		 *    假设这个实现类是Xxx
		 *    class org.hibernate.impl.SessionImpl
		 * 
		 * getCurrentSession()方法返回的是这个接口的某一个实现类对象
		 *    假设这个实现类是Yyy
		 *    class com.sun.proxy.$Proxy6
		 *  
		 * Xxx类的对象和Yyy类的对象肯定是不相等的
		 * 
		 *  相当于:
		 *  Object o1 = new Student();
		 *  Object o2 = new Teacher();
		 *  o1==o2 肯定是false
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

	@Test//hibernate中进行jdbc的操作
	public void session_jdbc(){
		try {
			
			Session session = sf.openSession();
			
			//这个方法已经过时,被下面的方式所代替
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
	
	
	@Test//hibernate中id值的自动增长策略
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
	
	
	
	
	
	
	@Test//实体类对象的 瞬时态/自由态 Transient
	public void e_transient(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * 1.session的缓存中有没有这个对象          没有
			 * 2.数据库中有没有这个对象所对应的记录  没有
			 * 
			 */
			Car car = new Car(1,"BMW1",100000);
			//hibernate不保证瞬时态对象的属性变化和数据库同步
			//所以也不会有update更新语句执行
			car.setName("test");
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//实体类对象的 持久态 Persistent
	public void e_persistent(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * 1.session的缓存中有没有这个对象          有
			 * 2.数据库中有没有这个对象所对应的记录  有
			 * 
			 */
			Car car = (Car)session.get(Car.class, 1L);
//			
//			Car car2 = new Car(2,"BMW1",100000);
//			session.save(car2);
			
			//hibernate会保持久态对象的属性变化和数据库同步
			//所以会有update更新语句执行
			car.setName("test");
			
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test//实体类对象的 脱管态/游离态 Detached
	public void e_detached(){
		
		try {
			
			Session session = sf.openSession();
			
			Transaction tran = session.beginTransaction();
			
			/*
			 * 特点:
			 * 1.session的缓存中有没有这个对象          没有
			 * 2.数据库中有没有这个对象所对应的记录    有
			 * 
			 */
			
			//这时候还是持久态
			Car car = (Car)session.get(Car.class, 1L);
			//clear后就变成了脱管态 
			//因为session的缓存中没了这个对象
			//但是数据库中有这个对象对应的记录
			session.clear();
			
			//hibernate不保证脱管态对象的属性变化和数据库同步
			//所以也不会有update更新语句执行
			car.setName("test123");
			
			tran.commit();
			
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
