package com.briup.estore.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	/**
	 * 配置文件的目录
	 * */
	private static String configFile ="/hibernate.cfg.xml";
	/**
	 * 用来保证该线程中只能由一个session
	 * */
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	/**
	 * 用来实例化一个配置对象
	 * */
	private static Configuration config = new Configuration();
	
	private static SessionFactory sessionFactory;
	
	static {
		//读取配置文件
		config.configure(configFile);
		//创建sessionFactory
		sessionFactory = config.buildSessionFactory();
	}
	//获取session
	public static Session getSession() {
		Session session = threadLocal.get();
		if(session == null || !session.isOpen()){
			session =(sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}
	//关闭session
	public static void close(){
		Session session = threadLocal.get();
		threadLocal.set(null);
		if(session!=null){
			session.close();
		}
	}
}
