package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.briup.jdbc.JDBCUtil;

public class JDBCUtilTest {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "test";
	private String password = "test";
	
	//���ݿ����Ӷ���
	private Connection conn;
	//ִ��sql���Ķ���
	private Statement stmt;
	private PreparedStatement ps;
	//���ղ�ѯ���ݵĽ����
	private ResultSet rs;
	
	//�������  Ĭ���������Զ��ύ
	public void insert(){
		
		try {
			//��һ�� ע������
			Class.forName(driver);
			//�ڶ��� ������ݿ�����Ӷ���
			conn = DriverManager.getConnection(url, username, password);
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			//���Ĳ� ִ��sql���
			String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
			//Ĭ�������,sql���ִ����������Զ��ύ��
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر���Դ
			try {
				//ע��رյ�˳��,��󴴽��Ķ������ȹر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//�������  Ĭ���������Զ��ύ
	public void insert2(){
		
		try {
			stmt = JDBCUtil.getStatement();
			String sql = "insert into test(id,name,salary) values(152,'tom',1000)";
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				JDBCUtil.close(stmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	
	//�������
	public void insert3(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "insert into test(id,name,salary) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			//��sql����еĵ�һ��?���滻��Long���͵�ֵ7
			ps.setLong(1, 7L);
			ps.setString(2, "lily");
			ps.setDouble(3, 1000d);
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insert4(){
		
		try {
			String sql = "insert into test(id,name,salary) values(?,?,?)";
			ps = JDBCUtil.getPreparedStatement(sql);
			
			ps.setLong(1, 153L);
			ps.setString(2, "lily");
			ps.setDouble(3, 1000d);
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				JDBCUtil.close(ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		JDBCUtilTest t = new JDBCUtilTest();
//		t.insert2();
		t.insert4();
	}
	
}
