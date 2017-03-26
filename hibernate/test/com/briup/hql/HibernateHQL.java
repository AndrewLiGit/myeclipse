package com.briup.hql;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateHQL {
	private SessionFactory sf;
	private Configuration cfg;
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
			se.create(false,true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//hql语句不能执行insert语句
	public void save(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Group g = new Group();
			g.setName("g1");
			
			User u1 = new User();
			u1.setName("zs");
			
			User u2 = new User();
			u2.setName("lisi");
			
			User u3 = new User();
			u3.setName("wangwu");
			
			g.getUsers().add(u1);
			g.getUsers().add(u2);
			g.getUsers().add(u3);
			
			u1.setGroup(g);
			u2.setGroup(g);
			u3.setGroup(g);
			
			session.save(g);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hql_first(){
		//hql是hibernate提供的一种查询语句
		//需要使用org.hibernate.Query接口的实现类对象去执行hql
		//通过session获得Query接口的实现类对象
		//hql语句关注的是类和类中的属性
		//hql语句中不可能出现表的名字或者列的名字
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			/*
			 * 相当于执行sql语句:select * from t_group
			 */
			String hql = "from Group";
			Query query = session.createQuery(hql);
			//查询结果放到list中并返回
			List<Group> list = query.list();
			
			for(Group g:list){
				System.out.println(g.getName());
				System.out.println("------------------");
				for(User u :g.getUsers()){
					System.out.println(u.getName());
				}
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	@Test
	public void hql_select(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			//sql = "select id,name from t_group";
			String hql = "select g.id,g.name from Group g";
			Query query = session.createQuery(hql);
			//这时候list里面放的是Object类型的数组
			//数组里面放的是我们查询到的每一条的值
			//每一条包括id  name
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.list();
			
			for(Object[] obj:list){
				System.out.println(Arrays.toString(obj));
			}
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//hql语句中使用类的构造器
	public void hql_select_new(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			//from User
			String hql = "select new User(u.id,u.name) from User u";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for(User u:list){
				System.out.println("u.id = "+u.getId());
				System.out.println("u.name = "+u.getName());
				System.out.println("u.group = "+u.getGroup());
				System.out.println("-------------------");
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//hql语句中使用类的构造器
	public void hql_select_new1(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			//from User
			String hql = "select u.id,u.name from User u";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.list();

			for(Object[] u:list){
				System.out.println(Arrays.toString(u));
				System.out.println("-------------------");
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//hql中的延迟加载
	public void test(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			String hql = "from User";
			Query query = session.createQuery(hql);
			List<User> list = query.list();
			
			for(User u:list){
				System.out.println("u.id = "+u.getId());
				System.out.println("u.name = "+u.getName());
				System.out.println("u.group = "+u.getGroup());
				System.out.println("group.set = "+u.getGroup().getUsers().size());
				System.out.println("-------------------");
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test//hql中不使用延迟加载
	public void hql_select_join_fetch(){
		//配置文件里面还是延迟加载,
		//但是只是在这次查询的时候不想用延迟加载了
		//这时候可以使用hql语句中的连接查询 join fetch
		//相当于以前的get/load查询时候文件中配置的fetch="join"
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			String hql = "from User u join fetch u.group";
			Query query = session.createQuery(hql);
			//查询u 查询g
			//建立他们之间的关联关系
			//类似于:u.setGroup(g);
			//最后再把u放到集合里面
			List<User> list = query.list();
			
			for(User u:list){
				System.out.println("u.id = "+u.getId());
				System.out.println("u.name = "+u.getName());
				System.out.println("u.group = "+u.getGroup());
				System.out.println("group.set = "+u.getGroup().getUsers().size());
				System.out.println("-------------------");
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hql_select_join(){
		
		//join 和 join fetch的区别
		//join也能连接查询,但是它不会帮我们把查询出来的对象建立起关联关系.
		//join fetch不仅能连接查询,还能帮我们把查询出来的对象建立起关联关系.
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql = "from User u join u.group";
			Query query = session.createQuery(hql);
			//查询u 查询g
			//不会建立他们之间的关联关系
			//而是把查询的结果放到这样的数组里面
			//Object[]{u,g}
			//最后再把数组放到集合里面
			List<Object[]> list = query.list();
			
			for(Object[] obj:list){
				System.out.println(Arrays.toString(obj));
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hql_select_where(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
//			String hql = "from User u where u.id=:sid";
//			Query query = session.createQuery(hql);
//			query.setLong("sid", 1L);
			
			String hql = "from User u where u.name=?";
			Query query = session.createQuery(hql);
			//从左到右 第一个?的下标是0
			query.setString(0, "zs");
			
			//如果我们十分确定查询的结果就一条数据
			User u = (User)query.uniqueResult();
			
			System.out.println(u.getId());
			System.out.println(u.getName());
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hql_delete(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			//注意:不能级联删除
			String hql = "delete from User u where u.name='zs'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hql_update(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql = "update User u set u.name='tom' where u.name='lisi'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//分页查询
	public void hql_fenye(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql = "from User";
			Query query = session.createQuery(hql);
			
			//第一条数据下标0
			int a = 5;
			//一共查询几条
			int b = 5;
			query.setFirstResult(a);
			query.setMaxResults(b);
			
			List<User> list = query.list();
			
			for(User u:list){
				System.out.println(u.getId()+"  "+u.getName());
			}
			
			tran.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
