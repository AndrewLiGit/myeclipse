package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeServletTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public LifeServletTest2() {
		System.out.println("调用LifeServletTest2中的构造器");
	}
	
	@Override
	public void init() throws ServletException {
		//注意:tomcat在这一步直接调用的是有参的init方法
		//只不过有参的init里面又调用了无参的init方法,也就是我们重写的这个无参init方法
		System.out.println("调用LifeServletTest2中init()方法");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("调用LifeServletTest2中的destroy()方法");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//注意:tomcat在这一步直接调用的是service方法,
		//只不过是service方法中又一层层的调用,最后调用到了doGet方法
		System.out.println("调用LifeServletTest2中的doGet方法");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
