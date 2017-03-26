package com.briup.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		System.out.println("session������");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		//�¼�����e���԰����ǻ�øոմ�����session����
		HttpSession session = e.getSession();
		System.out.println("session������");
	}

}
