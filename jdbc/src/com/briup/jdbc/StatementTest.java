package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "test";
	private String password = "test";
	
	//�������ݿ�Ķ���
	private Connection conn;
	//ִ��sql���Ķ���
	private Statement stmt;
	//��ѯ�Ľ����
	private ResultSet rs;
	
	public static void main(String[] args) {
		
		StatementTest st = new StatementTest();
		//st.insert("zhangsan3",3000);
		//st.delete(5);
		//st.update(1,"lisi");
		st.select();
		
		//st.insert4();		
	}	
	/*
	create table test(
		id number primary key,
		name varchar2(100),
		salary number
	); 
	 */
	//�������  �Զ��ύ����ķ�ʽ
	public void insert(){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
			//sql���ִ�����Զ��ύ����
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	//�������  �ֶ��ύ����ķ�ʽ
	public void insert2(){
			try {
				//��һ�� ע������
				Class.forName(driver);
				
				//�ڶ��� ����������ݿ�Ķ���
				conn = DriverManager.getConnection(url, username, password);
				//�����Զ��ύΪfalse�͵��ֶ��ύ    Ĭ����true
				conn.setAutoCommit(false);
				
				//������ ���Statement���� ����:ִ��sql���
				stmt = conn.createStatement();
				
				//���Ĳ� ʹ��Statement����ִ��sql���
				String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
				stmt.execute(sql);
				//�ֶ��ύ����
				conn.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				//�������ִ�е�ʱ�����쳣,��������ع�����
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}finally{
				//���岽 �ر��������ݿ�ĸ�����Դ
				try {
					//�ȴ����Ķ������ر�
					if(stmt!=null)stmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		}	

	//�������  sql�����ƴ�Ӳ���
	public void insert(int id,String name,double salary){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "insert into test(id,name,salary) values("+id+",'"+name+"',"+salary+")";
			System.out.println(sql);
			//sql���ִ�����Զ��ύ����
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//�������  idֵʹ����Ҫ���� 
	//create sequence my_seq;
	public void insert(String name,double salary){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "insert into test(id,name,salary) values(my_seq.nextval,'"+name+"',"+salary+")";
			System.out.println(sql);
			//sql���ִ�����Զ��ύ����
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}	
	
	//ɾ������
	public void delete(int id){
		
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "delete from test where id="+id;
			stmt.execute(sql);
			//stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}				
	}
	
	//���²���
	public void update(int id,String newName){
		
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "update test set name='"+newName+"' where id="+id;
			stmt.execute(sql);
			//stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}			
	}	
	
	//��ѯ
	public void select(){
		
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql��� ����ǲ�ѯ���,��Ҫ���ս����
			String sql = "select * from test";
			rs = stmt.executeQuery(sql);
			//��������� �õ�����
			//rs.next()����true����false
			//true��ʾ������л�������
			//false��ʾ�������û��������
			while(rs.next()){
				//���rs�л������� �Ϳ����õ���һ�������е�ÿһ�е�ֵ
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salary");
				
				System.out.println(id+" "+name+" "+salary);				
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}				
	/*
	 * �½�һֱ��
	create table test2(
		id number primary key,
		name varchar2(100),
		birthday date
	); 
	 */
	
	//�������  ��������
	public void insert4(){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			//���sql����ȷ��
//			String sql = "insert into test2(id,name,birthday) values(1,'tom',sysdate)";
			//���sql�Ǵ����
//			String sql = "insert into test2(id,name,birthday) values(3,'tom','01-05-99')";
			String sql = "insert into test2(id,name,birthday) values(2,'tom',to_date('01-05-99','dd-mm-yy'))";
			//sql���ִ�����Զ��ύ����
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}		
}
