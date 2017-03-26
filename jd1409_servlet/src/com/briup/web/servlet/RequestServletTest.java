package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//把数据保存到request对象中
		//request对象里面封装了一个Map集合
		//Map<String,Object>
		request.setAttribute("age", 20);
		
		//从request中拿到的对象都是Object类型的
		//需要的话可以强制转换
		int age = (Integer) request.getAttribute("age");
		System.out.println("age = "+age);
		
		//注意:区别getParameter和getAttribute俩个方法
		//getParameter是获得页面传过来的参数的
		//getAttribute是获得之前调用setAttribute方法放到request中的数据的
		String name = request.getParameter("name");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
