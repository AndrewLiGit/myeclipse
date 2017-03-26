package com.briup.estore.common;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		session.createQuery("from Product").list();
		
		
	}
}
