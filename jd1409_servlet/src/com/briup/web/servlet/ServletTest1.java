package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletTest1 implements Servlet{

	@Override
	public void destroy() {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		//req ����   ͨ��req���Խ���ҳ�淢�͹���������
		//res ��Ӧ   ͨ��res������ͻ��������д��ȥһЩ���ݻ���д�ر��η��ʵĽ������
		
		System.out.println("ServletTest1");
		
		//�ȸ�������������Ӧ����ȥ����ʲô���͵�����
		res.setContentType("text/html;charset=utf-8");
		
		//���ָ��ͻ���������������(˭���ʾ�д��˭)
		PrintWriter out = res.getWriter();
		
		out.println("hello world jd1409");
		
		out.flush();
		out.close();
		
	}
	
	
	
	
}
