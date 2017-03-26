package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementTest {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "test";
	private String password = "test";
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static void main(String[] args) {
		
		PreparedStatementTest pst = new PreparedStatementTest();
//		pst.insert();
//		pst.insertMore();
		pst.insertBatch();
		
/*		List<Long> list = new ArrayList<Long>();
		list.add(100L);
		list.add(101L);
		list.add(104L);
		list.add(106L);
		list.add(116L);
		list.add(126L);
		pst.delete(list);*/
		
//		pst.update(151, "tom123");
//		pst.select();
		
		//pst.insertDate();
	}
	/*
	   create table test(
			id number primary key,
			name varchar2(100),
			salary number
	   );
	 */
	//�������
	public void insert(){
		
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
	//�������
	public void insertMore(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "insert into test(id,name,salary) values(my_seq.nextval,?,?)";
			ps = conn.prepareStatement(sql);
			
			for(int i=10;i<=30;i++){
				ps.setString(1, "lily"+i);
				ps.setDouble(2, 1000+i);
				ps.execute();
			}						
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
	//������� ������
	public void insertBatch(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "insert into test(id,name,salary) values(my_seq.nextval,?,?)";
			ps = conn.prepareStatement(sql);
			
			for(int i=0;i<100;i++){
				ps.setString(1, "lily"+i);
				ps.setDouble(2, 1000+i);
				//�ѵ�ǰ�滻�˾������ݵ�sql�����뵽��������
				ps.addBatch();
				
				//ÿ20��ִ��һ��������
				if(i%20==0&&i!=0){
					//ִ�е�ǰ�������е�sql���
					ps.executeBatch();
				}
			}
			//�����ִ��һ�� ��Ϊ���һ�ο��ܲ���20��
			ps.executeBatch();
			
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
	//ɾ������  ��������Ҫɾ�������ݵ�idֵ
	public void delete(List<Long> list){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "delete from test where id=?";
			ps = conn.prepareStatement(sql);
			
			for(Long id:list){
				ps.setLong(1, id);
				ps.execute();
			}
			
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
	//���²��� 
	public void update(long id,String newName){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "update test set name=? where id=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, newName);
			ps.setLong(2, id);
			
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
	//���Ҳ��� 
	public void select(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//���PreparedStatement���Ͷ��� ִ��ͬ����sql���
			//sql����е�?����ռλ��,����Ҫ�þ����ֵȥ�滻��
			String sql = "select * from test where id>?";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, 120L);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				long id = rs.getLong("id");
				String name = rs.getString("name");
				//3��ʾ���еĵ�����
				double salary = rs.getDouble(3);
				
				System.out.println(id+" "+name+" "+salary);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * �����±�:
	   create table test2(
			id number primary key,
			name varchar2(100),
			birthday date
	   );
	 */
	//�������  ���е������������͵�����
	public void insertDate(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "insert into test2(id,name,birthday) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, 2L);
			ps.setString(2, "tom2");
//			java.util.Date date = new java.util.Date();
//			
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			ps.setDate(3, date);
			
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
}
