package com.briup.web.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent e) {
		
		System.out.println("request��������");
	}

	@Override
	public void requestInitialized(ServletRequestEvent e) {
		//�¼�����e���԰����ǻ�øոմ�����request����
		ServletRequest request = e.getServletRequest();
		//request.setAttribute("test","hello world");
		System.out.println("request���󱻴���");
	}

}
