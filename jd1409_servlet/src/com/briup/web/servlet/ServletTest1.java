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
		
		//req 请求   通过req可以接收页面发送过来的数据
		//res 响应   通过res可以向客户端浏览器写回去一些数据或者写回本次访问的结果怎样
		
		System.out.println("ServletTest1");
		
		//先告诉浏览器这次响应带回去的是什么类型的内容
		res.setContentType("text/html;charset=utf-8");
		
		//获得指向客户端浏览器的输出流(谁访问就写给谁)
		PrintWriter out = res.getWriter();
		
		out.println("hello world jd1409");
		
		out.flush();
		out.close();
		
	}
	
	
	
	
}
