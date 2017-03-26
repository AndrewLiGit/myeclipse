package com.briup.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateComponent {
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
	
	
	@Test
	public void save(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Person p = new Person();
			p.setName("tom");
			p.setAge(20);
			
			Address address = new Address("China","kunshang");
			p.setAddress(address);
			
			session.save(p);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void query(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Person p = 
				(Person)session.get(Person.class,1L);
			
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(p.getAge());
			System.out.println(p.getAddress().getCountry());
			System.out.println(p.getAddress().getCity());
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
