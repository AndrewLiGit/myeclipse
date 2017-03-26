package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTest1 {

	public static void main(String[] args) {
		//�������ݿ���Ҫ�õ�����Ϣ
		//driver���������ݿ����õ���������İ���������
		String driver = "oracle.jdbc.driver.OracleDriver";
		//url:�������ݿ�ĵ�ַ��Ϣ
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		//username:�������ݿ���û���
		String username = "test";
		//password:�������ݿ������
		String password = "test";
				
		try {
			//��һ��  ע��������������һ���ࣩ
			Class.forName(driver);
			//System.out.println(Class.forName(driver));
			//�ڶ��� ������ݿ�����
			Connection conn = DriverManager.getConnection(url,username,password);
			
			System.out.println(conn);
			
			if(conn!=null) conn.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
