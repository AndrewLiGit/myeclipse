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

//һ��һ�����������
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
//			session.save(w);
			//�ڴ��н�����������֮��Ĺ�ϵh--->w
			h.setWife(w);
			
			session.save(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//��������  һ��һ��ϵ ���������Ψһ��
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
			
			/*
			 * load�������ӳټ��غ�ӳ���ļ������õ��ӳټ��ص�����
			 * 
			 * ��Husband��WifeΪ��˵��:
			 * 
			 * session.load(Husband.class, 1L);
			 * load�������ӳ�:����load������ʱ�򲻲�ѯHusband,
			 * ʹ�õ�Husband��������Ե�ʱ��Ż�������ȥ��ѯ
			 * ����ӳٿ��Ƶ��ǵ���load������ʱ��᲻��������select����ѯHusband����
			 * 
			 * ӳ���ļ������õ��ӳټ���:
			 * ��ѯHusband�����ʱ��,�������Husband������Wife�����ѯ����
			 * �ȵ�ʹ�õ����Wife��������Ե�ʱ��Ż������Ĳ�ѯ����
			 * ���ӳٿ��Ƶ��ǲ�ѯHusband��ʱ��᲻��������select����WifeҲ��Ӧ�Ĳ�ѯ����
			 * 
			 */
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//���ü�����ѯ��ʱ��ʹ��select����join���Ӳ�ѯ
	public void fetch_select_join(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//������ѯ   join���Ӳ�ѯ��Ӱ�쵽�ӳټ��ص����� ��Ϊjoin���Ӳ�ѯ��ֻ��һ��select���Ӳ�ѯ����䣬û�а취���ӳټ���
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
	
	
	
	@Test//����ɾ��
	public void delete(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband)session.get(Husband.class, 1L);
			
			//������ѯ����֮�����h�Ͷ���w���ڴ����Ѿ����ڹ�ϵ(1:1),��Ϊ���ļ�������ӳ��
			//���Կ��Խ��м���ɾ��(ͬʱҲ��������������ɾ����)
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//ɾ�� ����hû�й�����w
	public void delete2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setId(1L);
			
			
			//����h�Ͷ���w���ڴ�û�й��� ����û�м���ɾ��
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//ɾ�� ����h������w �����Ϲ�״̬�Ķ���
	public void delete3(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			
			Husband h = new Husband();
			h.setId(1L);
			
			Wife w = new Wife();
			w.setId(1L);
			
			h.setWife(w);
			
			//hibernate��Ҳ�ǿ����������м���ɾ����
			session.delete(h);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//ɾ�� 
	public void delete4(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband) session.get(Husband.class, 1L);
			
			//��session�Ļ����н��h��wֱ�ӵĹ����ϵ
			h.setWife(null);
			session.delete(h);
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test//��������
	public void update(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Husband h = (Husband)session.get(Husband.class, 1L);
			
			//wife������ʱ��Ҳ�ǳ־û�״̬
			//��Ϊ��ѯHusband�Ķ����ʱ�����˼�����ѯ��wife����Ҳ��ѯ����
			//wife�����Ըı��˻ᱣ�����ݿ��ͬ��
			h.getWife().setName("lily");
			
			session.update(h);
			
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
			
			//��ѯwife����  ���ἶ���Ĳ�ѯ����Ӧ���ɷ� ��ֻ�ǵ��������ӳ��
			//ֻ��ͨ��һ����������ѯ������һ��,��֮�򲻿���,���Ǹĳ�˫�����������
			Wife w = (Wife)session.get(Wife.class, 1L);
			
			System.out.println(w.getName());
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
