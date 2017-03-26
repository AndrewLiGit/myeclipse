package com.briup.orm.o2m;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.briup.orm.o2m.bi.Group;
import com.briup.orm.o2m.bi.User;

//一对多双向关联测试
public class HIbernateO2M_bi {
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
			
			
			Group g = new Group();
			g.setName("g1");
			
			User u1 = new User();
			u1.setName("zs");
			
			User u2 = new User();
			u2.setName("lisi");
			
			User u3 = new User();
			u3.setName("wangwu");
			
			//内存中建立起对象之间的关联
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
	
	@Test//级联查询
	public void query(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			System.out.println("hello");
			Group g = 
					(Group)session.get(Group.class,1L);
			System.out.println("world");	
			for(User u:g.getUsers()){
				System.out.println(u.getId()+"  "+u.getName());
			}
			
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
			
			Group g = 
					(Group)session.get(Group.class,1L);
				
			session.delete(g);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test//级联删除
	public void delete2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			User u = 
					(User)session.get(User.class,1L);
			session.delete(u);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//级联删除
	public void delete3(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			User u = 
					(User)session.get(User.class,1L);
			u.setGroup(null);

			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//级联删除
	public void delete4(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			User u = 
					(User)session.get(User.class,2L);
			u.setGroup(null);
			session.delete(u);
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//级联删除
	public void delete5(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Group g = (Group) session.get(Group.class, 1L);
			/*
			 * Group配置文件里的Set标签里inverse=“true”时需：
			 * for(User u:g.getUsers()){
					u.setGroup(null);
				}
			 *
			 * 	Group配置文件里的Set标签里inverse=“false”时就不需要了
			 */
//			for(User u:g.getUsers()){
//				u.setGroup(null);
//			}
			
			g.setUsers(null);
			session.delete(g);
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
