package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTest3 {

	public static void main(String[] args) {
		//连接数据库须要用到的信息
		//driver：连接数据库所用到的驱动类的包名加类名
		String driver = "oracle.jdbc.driver.OracleDriver";
		//url:连接数据库的地址信息
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		//username:连接数据库的用户名
		String username = "test";
		//password:连接数据库的密码
		String password = "test";
		
		try {
			//第一步  注册驱动（驱动是一个类）
			//传JVM参数  就是把driver类的名字传给JVM JVM会帮我们 
			//在运行时给JVM传参数 -Djdbc.drivers=oracle.jdbc.driver.OracleDriver
			//通过代码给JVM传参数
			System.setProperty("jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
			
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
