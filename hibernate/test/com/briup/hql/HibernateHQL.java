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
	
	@Test//hql��䲻��ִ��insert���
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
		//hql��hibernate�ṩ��һ�ֲ�ѯ���
		//��Ҫʹ��org.hibernate.Query�ӿڵ�ʵ�������ȥִ��hql
		//ͨ��session���Query�ӿڵ�ʵ�������
		//hql����ע����������е�����
		//hql����в����ܳ��ֱ�����ֻ����е�����
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			/*
			 * �൱��ִ��sql���:select * from t_group
			 */
			String hql = "from Group";
			Query query = session.createQuery(hql);
			//��ѯ����ŵ�list�в�����
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
			//��ʱ��list����ŵ���Object���͵�����
			//��������ŵ������ǲ�ѯ����ÿһ����ֵ
			//ÿһ������id  name
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
	
	@Test//hql�����ʹ����Ĺ�����
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
	
	@Test//hql�����ʹ����Ĺ�����
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
	
	@Test//hql�е��ӳټ���
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
	
	
	@Test//hql�в�ʹ���ӳټ���
	public void hql_select_join_fetch(){
		//�����ļ����滹���ӳټ���,
		//����ֻ������β�ѯ��ʱ�������ӳټ�����
		//��ʱ�����ʹ��hql����е����Ӳ�ѯ join fetch
		//�൱����ǰ��get/load��ѯʱ���ļ������õ�fetch="join"
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			String hql = "from User u join fetch u.group";
			Query query = session.createQuery(hql);
			//��ѯu ��ѯg
			//��������֮��Ĺ�����ϵ
			//������:u.setGroup(g);
			//����ٰ�u�ŵ���������
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
		
		//join �� join fetch������
		//joinҲ�����Ӳ�ѯ,��������������ǰѲ�ѯ�����Ķ������������ϵ.
		//join fetch���������Ӳ�ѯ,���ܰ����ǰѲ�ѯ�����Ķ������������ϵ.
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql = "from User u join u.group";
			Query query = session.createQuery(hql);
			//��ѯu ��ѯg
			//���Ὠ������֮��Ĺ�����ϵ
			//���ǰѲ�ѯ�Ľ���ŵ���������������
			//Object[]{u,g}
			//����ٰ�����ŵ���������
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
			//������ ��һ��?���±���0
			query.setString(0, "zs");
			
			//�������ʮ��ȷ����ѯ�Ľ����һ������
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
			//ע��:���ܼ���ɾ��
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
	
	@Test//��ҳ��ѯ
	public void hql_fenye(){
		try {
			Session session = sf.openSession();
			Transaction tran = session.beginTransaction();
			
			String hql = "from User";
			Query query = session.createQuery(hql);
			
			//��һ�������±�0
			int a = 5;
			//һ����ѯ����
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
