package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import oracle.jdbc.OracleDriver;

public class ConnectTest2 {

	public static void main(String[] args) {
		//连接数据库须要用到的信息
		//driver：连接数据库所用到的驱动类的包名加类名
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//url:连接数据库的地址信息
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		//username:连接数据库的用户名
		String username = "test";
		//password:连接数据库的密码
		String password = "test";
		
		try {
			//这种方式注册驱动可以不使用上面定义的string driver
			//第一步  注册驱动（驱动是一个类）
			Driver dri = new OracleDriver();
			DriverManager.registerDriver(dri);
			//第二步 获得数据库连接
			Connection conn = DriverManager.getConnection(url,username,password);
			
			System.out.println(conn);
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
