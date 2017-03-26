package com.briup.orm.o2o;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.Loader;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.briup.orm.o2o.bi.Husband;
import com.briup.orm.o2o.bi.Wife;

//һ��һ˫���������
public class HIbernateO2O_bi {
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
	
	
	@Test//�Զ�����
	public void autoCreateTable(){
		
		try {
			SchemaExport se = new SchemaExport(cfg);
			se.create(false, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//��������
	public void save(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = new Husband();
			h.setName("tom");
			
			Wife w = new Wife();
			w.setName("lily");
			
			//�ڴ��н�����������֮��Ĺ�ϵh-->w
			h.setWife(w);
			
			session.save(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//�������� ʧ�ܵ�����
	public void save2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setName("tom");
			
			Wife w = new Wife();
			w.setName("lily");
			
			//�ڴ��н�����������֮��Ĺ�ϵh-->w
//			h.setWife(w);
//			session.save(h);
			
			//��������ʧ�� ��Ϊ������Ҫ���Ƕ���    w--������-->h
			//���Ƕ���Ĺ�ϵ���ó���   h--������-->w
			w.setHusband(h);
			session.save(w);
			
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	@Test//������ѯ
	public void query(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//������ѯ   �����Ƿ�ʹ���ӳټ���
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
	
	@Test//��ѯwife����
	public void query_wife(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			System.out.println("hello");
			Wife w = (Wife)session.get(Wife.class, 1L);
			System.out.println("world");
			
			//�����wife.hbm.xml�ļ���ӳ��Husband������ɾ��,��ô�Ͳ��ܼ�����ѯ��
			//����������֮�䵽���ǵ����������˫�������Ҫ����Ҫ��ӳ���ļ�������
			System.out.println(w.getHusband().getName());
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//����ɾ��
	public void delete(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband)session.get(Husband.class, 1L);
			
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	
}
