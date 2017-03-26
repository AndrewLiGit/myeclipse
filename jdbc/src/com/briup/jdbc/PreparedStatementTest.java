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
	//插入操作
	public void insert(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
			String sql = "insert into test(id,name,salary) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			//把sql语句中的第一个?号替换成Long类型的值7
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
	//插入操作
	public void insertMore(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
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
	//插入操作 批处理
	public void insertBatch(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
			String sql = "insert into test(id,name,salary) values(my_seq.nextval,?,?)";
			ps = conn.prepareStatement(sql);
			
			for(int i=0;i<100;i++){
				ps.setString(1, "lily"+i);
				ps.setDouble(2, 1000+i);
				//把当前替换了具体数据的sql语句加入到批处理中
				ps.addBatch();
				
				//每20条执行一次批处理
				if(i%20==0&&i!=0){
					//执行当前批处理中的sql语句
					ps.executeBatch();
				}
			}
			//最后在执行一次 因为最后一次可能不满20条
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
	//删除操作  集合中是要删除的数据的id值
	public void delete(List<Long> list){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
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
	//更新操作 
	public void update(long id,String newName){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
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
	//查找操作 
	public void select(){
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			//获得PreparedStatement类型对象 执行同构的sql语句
			//sql语句中的?号是占位符,将来要用具体的值去替换的
			String sql = "select * from test where id>?";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, 120L);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				long id = rs.getLong("id");
				String name = rs.getString("name");
				//3表示表中的第三列
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
	 * 创建新表:
	   create table test2(
			id number primary key,
			name varchar2(100),
			birthday date
	   );
	 */
	//插入操作  表中的列有日期类型的数据
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
