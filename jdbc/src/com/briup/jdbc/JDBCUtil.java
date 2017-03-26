package com.briup.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		
		try {
			//注意:读取的文件的内容的格式要是k=v这种固定的格式
			Properties p = new Properties();
			p.load(new FileInputStream("src/com/briup/jdbc/jdbc.properties"));
			
			// p.getProperty("key") 返回文件中key对应的value值
			driver = p.getProperty("dirver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public static Connection getConneciton()throws Exception{
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
	
	public static Statement getStatement()throws Exception{
		
		Connection conn = getConneciton();
		Statement stmt = conn.createStatement();
		return stmt;
	}
	
	public static PreparedStatement getPreparedStatement(String sql)throws Exception{
		
		Connection conn = getConneciton();
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		return ps;
	}	
	
	public static void close(ResultSet rs,Statement stmt,Connection conn)throws Exception{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	public static void close(Statement stmt,Connection conn)throws Exception{
		close(null,stmt,conn);
	}
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println(getConneciton());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
