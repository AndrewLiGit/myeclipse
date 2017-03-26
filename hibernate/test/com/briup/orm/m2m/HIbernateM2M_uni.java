package com.briup.orm.m2m;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.briup.orm.m2m.uni.Student;
import com.briup.orm.m2m.uni.Teacher;

//һ�Զ�˫���������
public class HIbernateM2M_uni {
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
			
			
			Teacher t = new Teacher();
			t.setName("zs");
			
			Student s1 = new Student();
			s1.setName("tom1");
			Student s2 = new Student();
			s2.setName("tom2");
			Student s3 = new Student();
			s3.setName("tom3");
			
			//�ڴ��н�������֮��Ĺ�����ϵ
			t.getStudents().add(s1);
			t.getStudents().add(s2);
			t.getStudents().add(s3);
			
			session.save(t);
			
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
			
			Teacher t = 
					(Teacher)session.get(Teacher.class,1L);
				
			for(Student s:t.getStudents()){
				System.out.println(s);
			}
			
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
			
			Teacher t = 
					(Teacher)session.get(Teacher.class,1L);
				
			session.delete(t);
			
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test//����ɾ��
	public void delete2(){
		
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Teacher t = 
					(Teacher)session.get(Teacher.class,1L);
			
			//�������֮��Ĺ�����ϵ
			t.getStudents().clear();
			session.delete(t);
			tran.commit();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
