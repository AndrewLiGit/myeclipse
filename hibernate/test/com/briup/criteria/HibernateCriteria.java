package com.briup.criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HibernateCriteria {
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
			
			Husband h = new Husband("wangwu4",29,1600);
			
			Wife w = new Wife();
			w.setName("lily");
			
			//��������ʵ�������֮��Ĺ�ϵ
			h.setWife(w);
			
			session.save(h);
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//��ѯ����������
	public void criteria1(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			//session������:
			//1.ִ����ɾ�Ĳ����
			//2.�ṩ���湦��
			//3.�����߼���ѯ�ӿڵ�ʵ�������
			
			//Criteria�ǽӿ�
			Criteria criteria = session.createCriteria(Husband.class);
			
			//��ʱ����Husband������Ӧ�ı����������ݲ�ѯ����
			//��Ϊ�����滹û����Ӳ�ѯ��ɸѡ����
			List<Husband> list = criteria.list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test//��Ӳ�ѯ����
	public void criteria2(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Husband.class);
			
			//Criteria�ӿ�  Criterion�ӿ�  Restrictions��
			//Criterion �� Criteria �Ĳ�ѯ����
			//CriterionҲ��һ���ӿ�,����Ҫʹ������ӿڵ�ʵ�����������ʾ��ѯ������
			//Criteria�еķ���add(��ѯ����)
			//Criteria �ṩ�� add(Criterion criterion) ��������Ӳ�ѯ����
			//Restrictions ��������Դ���Criterion�ӿ����͵Ķ���,Ҳ���Ǵ�����ѯ��������
			//Restrictions�����кܶྲ̬����,��Щ�����ķ���ֵ��������Ҫ�Ĳ�ѯ��������(Criterion�ӿڵ�ʵ�������)
			
			//Restrictions.gt("id", 2L)�����ķ���ֵ����Criterion�ӿڵ�ʵ�������(��ѯ��������)
//			select * from husband where id>2;
//			criteria.add(Restrictions.gt("id", 2L));
			
//			select * from husband where salary<=4000
//			criteria.add(Restrictions.le("salary", 4000d));
			
//			select * from husband where name='wangwu3'
//			criteria.add(Restrictions.eq("name", "wangwu3"));
			
//			select * from husband where name like 'wang%'
//			criteria.add(Restrictions.like("name", "wang%"));
			
//			select * from husband where id in(1,2,3,4);
//			criteria.add(Restrictions.in("id", new Long[]{1L,2L,3L,4L}));
			
//			select * from husband where salary between 4000 and 9000;
//			ע��:����Ҳ������һ������ ������������������֮��
//			criteria.add(Restrictions.between("salary", 4000d, 9000d));
			
//			select * from husband where salary is null;
//			criteria.add(Restrictions.isNull("salary"));
			
//			select * from husband where name is not null;
			criteria.add(Restrictions.isNotNull("name"));
			
			List<Husband> list = criteria.list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
//				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//������Ӳ�ѯ����
	public void criteria3(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Husband.class);
			
			//����������� Ĭ������and�ķ�ʽ��������
			//select * from husband where id>2 and id<7;
//			criteria.add(Restrictions.gt("id",2L));
//			criteria.add(Restrictions.lt("id", 7L));
			
			
			//�����д���ȼ��������д��
			//ͬʱ����Ҳ��������д  ���������������
			criteria.add(Restrictions.gt("id", 2L)).add(Restrictions.le("id",4L));
			
			List<Husband> list = criteria.list();
			
			//Ҳ�������������������ִ�в�ѯд��һ��
//			List<Husband> list = criteria.add(Restrictions.gt("id", 2L)).
//					add(Restrictions.le("id",4L)).list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
//				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test//���or������
	public void criteria4(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Husband.class);
			
			//Restrictions.or(����1,����2);  �����ķ���ֵ����Criterion���Ͷ���
			//select * from husband where (id>2 or name='zhangsan') and salary=3000;
//			criteria.add(
//							Restrictions.or(Restrictions.gt("id",2L), Restrictions.eq("name","zhangsan"))
//					    ).
//					 add(Restrictions.eq("salary",3000d));
			
			
			
			//Restrictions.disjunction()����Ը��ܶ��or����������
			//select * from husband where id>3 and id<7 and (name='zs' or name like 'zs%' or salary<1000) and id=9;
			criteria.add(Restrictions.gt("id",3L)).
					 add(Restrictions.lt("id",7L)).
					 add(
							Restrictions.disjunction().
							add(Restrictions.eq("name","zs")).
							add(Restrictions.like("name","zs%")).
							add(Restrictions.lt("salary", 1000d))
						).
					 add(Restrictions.eq("id", 9L));
			
			
			List<Husband> list = criteria.list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
//				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//���Ӳ�ѯ
	public void criteria5(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Husband.class);
			
			//�ڲ�ѯHusband�Ļ����������Ӳ�ѯ��wifeҲ�����
			//�����wifeָ����Husband�����ֽ���wife������
			criteria.createCriteria("wife");
			
			List<Husband> list = criteria.list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	@Test//����
	public void criteria6(){
		try {
			
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Husband.class);
			
//			criteria.addOrder(Order.asc("id"));
			criteria.addOrder(Order.desc("salary"));
			
			List<Husband> list = criteria.list();
			
			for(Husband h:list){
				System.out.println(h.getId());
				System.out.println(h.getName());
				System.out.println(h.getAge());
				System.out.println(h.getSalary());
//				System.out.println(h.getWife().getName());
				System.out.println("------------------");
			}
			
			tran.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
