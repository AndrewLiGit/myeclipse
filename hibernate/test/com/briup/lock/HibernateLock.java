package com.briup.lock;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateLock {
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
			Husband h = new Husband();
			h.setName("tom");
			Wife w = new Wife();
			w.setName("lily");
			
			h.setWife(w);
			
			session.save(h);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ pessimistic  �������ݿ��е�for update��ʵ�ֵ�
	@Test
	public void lock_pessimistic(){
		try {
			Session session = sf.openSession();
			Session session2 = sf.openSession();
			
			//��һ��session��������,��ѯ���ݲ�����
			Transaction tran = session.beginTransaction();
			Husband h = (Husband)session.get(Husband.class,1L,LockMode.UPGRADE);
			
			//�ڶ���session��������,����ѯ��ͬ��һ������.
			Transaction tran2 = session2.beginTransaction();
			Husband h2 = (Husband)session2.get(Husband.class, 1L);
			//�ڶ������������޸����ݲ��Ҹ���
			h2.setName("zhangsan11111");
			session2.update(h2);
			
			//�ڶ��������ύ ע��:���ʱ���һ������û�н���
			System.out.println("hello");
			tran2.commit();
			System.out.println("world");
			session2.close();
			
			//��һ���������
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ֹ��� optimistic
	@Test
	public void lock_optimistic(){
		try {
			
			Session session = sf.openSession();
			Session session2 = sf.openSession();
			
			//��һ�������� ������� ������ʱ���version=0  Ȼ���������ݸ��� 
			Transaction tran = session.beginTransaction();
			Wife w = (Wife)session.get(Wife.class,1L);
			w.setName("tom5");
			session.update(w);
			
			//�ڶ��������� ������� ��ʱ��versionҲ����0   ע����ʱ���һ������û���ύ
			Transaction tran2 = session2.beginTransaction();
			Wife w2 = (Wife)session2.get(Wife.class,1L);
			w2.setName("tom6");
			session2.update(w2);
			
			//��һ�������ύ �ύ�������е� version�Զ���1���1
			tran.commit();
			session.close();
			
			//�ڶ��������ύ �����е��õ���version=0 ���ݿ������version=1
			tran2.commit();
			session2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
