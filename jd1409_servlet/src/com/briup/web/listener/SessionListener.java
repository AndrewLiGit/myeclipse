package com.briup.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		System.out.println("session被创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		//事件对象e可以帮我们获得刚刚创建的session对象
		HttpSession session = e.getSession();
		System.out.println("session被销毁");
	}

}
