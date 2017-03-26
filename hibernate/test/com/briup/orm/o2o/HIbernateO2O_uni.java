package com.briup.orm.o2o;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.briup.orm.o2o.uni.Husband;
import com.briup.orm.o2o.uni.Wife;

//一对一单向关联测试
public class HIbernateO2O_uni {
	
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
			if(sf!=null)sf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//自动建表
	public void autoCreateTable(){
		
		try {
			SchemaExport se = new SchemaExport(cfg);
			se.create(false, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//级联保存
	public void save(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = new Husband();
			h.setName("tom");
			
			Wife w = new Wife();
			w.setName("lily");
//			session.save(w);
			//内存中建立起来对象之间的关系h--->w
			h.setWife(w);
			
			session.save(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//级联保存  一对一关系 保持外键的唯一性
	public void save2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setName("zs");
			
			Wife w = (Wife)session.get(Wife.class, 1L);
			
			h.setWife(w);
			
			session.save(h);
			
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//级联查询
	public void query(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//级联查询   设置是否使用延迟加载
			System.out.println("hello");
			Husband h = (Husband)session.get(Husband.class, 1L);
			System.out.println("world");
			
			System.out.println(h.getWife().getName());
			
			/*
			 * load方法的延迟加载和映射文件中设置的延迟加载的区别
			 * 
			 * 以Husband和Wife为例说明:
			 * 
			 * session.load(Husband.class, 1L);
			 * load方法的延迟:调用load方法的时候不查询Husband,
			 * 使用到Husband对象的属性的时候才会真正的去查询
			 * 这个延迟控制的是调用load方法的时候会不会立马发出select语句查询Husband对象
			 * 
			 * 映射文件中设置的延迟加载:
			 * 查询Husband对象的时候,并不会把Husband关联的Wife对象查询出来
			 * 等到使用到这个Wife对象的属性的时候才会真正的查询出现
			 * 这延迟控制的是查询Husband的时候会不会立马发出select语句把Wife也相应的查询出来
			 * 
			 */
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//设置级联查询的时候使用select还是join连接查询
	public void fetch_select_join(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//级联查询   join连接查询会影响到延迟加载的设置 因为join连接查询就只有一条select连接查询的语句，没有办法做延迟加载
			System.out.println("hello");
			Husband h = (Husband)session.get(Husband.class, 1L);
			System.out.println("world");
			
			System.out.println(h.getWife().getName());
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test//级联删除
	public void delete(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband)session.get(Husband.class, 1L);
			
			//级联查询出来之后对象h和对象w在内存中已经存在关系(1:1),因为在文件中做了映射
			//所以可以进行级联删除(同时也是设置了允许级联删除的)
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//删除 对象h没有关联到w
	public void delete2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setId(1L);
			
			
			//对象h和对象w在内存没有关联 所以没有级联删除
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//删除 对象h关联到w 都是拖管状态的对象
	public void delete3(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setId(1L);
			
			Wife w = new Wife();
			w.setId(1L);
			
			h.setWife(w);
			
			//hibernate中也是可以这样进行级联删除的
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//删除 
	public void delete4(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband) session.get(Husband.class, 1L);
			
			//在session的缓存中解除h和w直接的管理关系
			h.setWife(null);
			session.delete(h);
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//级联更新
	public void update(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband)session.get(Husband.class, 1L);
			
			//wife对象这时候也是持久化状态
			//因为查询Husband的对象的时候做了级联查询把wife对象也查询出来
			//wife的属性改变了会保持数据库的同步
			h.getWife().setName("lily");
			
			session.update(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test//查询wife对象
	public void query_wife(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//查询wife对象  不会级联的查询到对应的丈夫 这只是单向关联的映射
			//只能通过一个方级联查询到另外一方,反之则不可以,除非改成双向关联的配置
			Wife w = (Wife)session.get(Wife.class, 1L);
			
			System.out.println(w.getName());
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
