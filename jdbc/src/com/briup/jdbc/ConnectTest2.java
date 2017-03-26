package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import oracle.jdbc.OracleDriver;

public class ConnectTest2 {

	public static void main(String[] args) {
		//�������ݿ���Ҫ�õ�����Ϣ
		//driver���������ݿ����õ���������İ���������
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//url:�������ݿ�ĵ�ַ��Ϣ
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		//username:�������ݿ���û���
		String username = "test";
		//password:�������ݿ������
		String password = "test";
		
		try {
			//���ַ�ʽע���������Բ�ʹ�����涨���string driver
			//��һ��  ע��������������һ���ࣩ
			Driver dri = new OracleDriver();
			DriverManager.registerDriver(dri);
			//�ڶ��� ������ݿ�����
			Connection conn = DriverManager.getConnection(url,username,password);
			
			System.out.println(conn);
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
