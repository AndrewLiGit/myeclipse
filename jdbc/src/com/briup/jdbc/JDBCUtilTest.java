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
	
	//数据库连接对象
	private Connection conn;
	//执行sql语句的对象
	private Statement stmt;
	private PreparedStatement ps;
	//接收查询数据的结果集
	private ResultSet rs;
	
	//插入操作  默认事务是自动提交
	public void insert(){
		
		try {
			//第一步 注册驱动
			Class.forName(driver);
			//第二步 获得数据库的连接对象
			conn = DriverManager.getConnection(url, username, password);
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			//第四步 执行sql语句
			String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
			//默认情况下,sql语句执行完事务就自动提交了
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//第五步 关闭资源
			try {
				//注意关闭的顺序,最后创建的对象最先关闭
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//插入操作  默认事务是自动提交
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
	
	//插入操作
	public void insert3(){
		
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
