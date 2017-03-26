package com.briup.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("application��������");
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		//�¼�����e���԰����ǻ�øոմ�����application����
		ServletContext application = e.getServletContext();
		System.out.println("application���󱻴���");
	}

}
