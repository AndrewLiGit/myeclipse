package com.briup.common.util;

import java.io.IOException;
import java.util.Properties;

import com.briup.service.IOrderService;
import com.briup.service.IProductService;
import com.briup.service.IUserService;

public class ServiceFactory {

	public static IProductService getProductService() {
		IProductService productService = null;
		Properties props = getProperties();
		String className = props.getProperty("ProductServiceImpl");
		try {
			productService = (IProductService) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return productService;
	}

	public static IUserService getUserService() {
		IUserService userService = null;
		Properties props = getProperties();
		String className = props.getProperty("UserServiceImpl");
		try {
			userService = (IUserService) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userService;
	}
	
	public static IOrderService getOrderService() {
		IOrderService orderService = null;
		Properties props = getProperties();
		String className = props.getProperty("OrderServiceImpl");
		try {
			orderService = (IOrderService) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderService;
	}
	
	public static Properties getProperties() {
		Properties props = new Properties();
//		System.out.println(props);
		try {
//			props.load(ServiceFactory.class.getResourceAsStream("../../ApplicationResources.properties"));
			props.load(ServiceFactory.class.getClassLoader().getResourceAsStream("com/briup/ApplicationResources.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static void main(String[] args) {
//		System.out.println(getProductService());
		System.out.println(getProductService());
	}
}
