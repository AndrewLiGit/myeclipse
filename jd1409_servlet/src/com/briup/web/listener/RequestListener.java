package com.briup.web.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent e) {
		
		System.out.println("request对象被销毁");
	}

	@Override
	public void requestInitialized(ServletRequestEvent e) {
		//事件对象e可以帮我们获得刚刚创建的request对象
		ServletRequest request = e.getServletRequest();
		//request.setAttribute("test","hello world");
		System.out.println("request对象被创建");
	}

}
