package com.briup.jdbc;

import java.util.Calendar;

public class Test {
	
	public static void main(String[] args) {
		
		//java.util.Date
		//java.sql.Date
		
		//Ê±¼ä´Á  1970 1 1 0:0:0
		
		long time = System.currentTimeMillis();
		System.out.println(time);
		//1411701142177
		//1411701184710
		
		java.util.Date date1 = new java.util.Date();
		
		long l = date1.getTime();
		
		java.sql.Date date2 = new java.sql.Date(114,8,26);
		java.sql.Date date3 = new java.sql.Date(l);
		System.out.println(date2);
		System.out.println(date3);
		
		java.util.Calendar c = Calendar.getInstance();
		
		java.util.Date date4 = c.getTime();
		
		System.out.println(date4);
	}
}
