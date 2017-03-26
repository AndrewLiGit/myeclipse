package com.briup.web.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.briup.bean.Product;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IProductService;

public class ApplicationListener implements ServletContextListener{

	private IProductService service = ServiceFactory.getProductService();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("-----ApplicationListener-----");
		ServletContext sc = arg0.getServletContext();
		Map<Integer, Product> map = new HashMap<Integer, Product>();
		try {
			map = service.listAllProducts();
			Set<Integer> set = map.keySet();
			for(Integer i:set){
				Product product = map.get(i);
				//System.out.println(product.getCategory().getName());
			}
			sc.setAttribute("products", map);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("没有商品！");
			e.printStackTrace();
		}
	}

}
