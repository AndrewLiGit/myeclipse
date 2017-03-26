package com.briup.common.util;

import java.io.IOException;
import java.util.Properties;

import com.briup.dao.IOrderDao;
import com.briup.dao.IProductDao;
import com.briup.dao.IUserDao;

public class DaoFactory {
	public static IProductDao getProductDao() {
		IProductDao productDao = null;
		Properties props = getProperties();
		String className = props.getProperty("ProductDaoImpl");
		try {
			productDao = (IProductDao) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return productDao;
	}
	
	public static IUserDao getUserDao() {
		IUserDao userDao = null;
		Properties props = getProperties();
		String className = props.getProperty("UserDaoImpl");
		try {
			userDao = (IUserDao) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userDao;
	}
	
	public static IOrderDao getOrderDao() {
		Properties props = getProperties();
		IOrderDao orderDao = null;
		String className = props.getProperty("OrderDaoImpl");
		try {
			orderDao = (IOrderDao) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderDao;
	}
	
	public static Properties getProperties() {
		Properties props = new Properties();
		try {
			props.load(DaoFactory.class.getResourceAsStream("../../ApplicationResources.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
