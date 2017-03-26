package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeServletTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public LifeServletTest2() {
		System.out.println("����LifeServletTest2�еĹ�����");
	}
	
	@Override
	public void init() throws ServletException {
		//ע��:tomcat����һ��ֱ�ӵ��õ����вε�init����
		//ֻ�����вε�init�����ֵ������޲ε�init����,Ҳ����������д������޲�init����
		System.out.println("����LifeServletTest2��init()����");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("����LifeServletTest2�е�destroy()����");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ע��:tomcat����һ��ֱ�ӵ��õ���service����,
		//ֻ������service��������һ���ĵ���,�����õ���doGet����
		System.out.println("����LifeServletTest2�е�doGet����");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
