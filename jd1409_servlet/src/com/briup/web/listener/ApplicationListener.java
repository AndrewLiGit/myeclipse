package com.briup.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("application对象被销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		//事件对象e可以帮我们获得刚刚创建的application对象
		ServletContext application = e.getServletContext();
		System.out.println("application对象被创建");
	}

}
