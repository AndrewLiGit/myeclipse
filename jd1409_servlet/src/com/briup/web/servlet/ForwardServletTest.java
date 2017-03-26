package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//在doGet方法中 让本次访问跳转到一个页面中
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//page表示将来要跳转到的页面
		String page = "";
		if("tom".equals(name)){
			page = "/forwardA.html";
		}else{
			page = "/forwardB.html";
		}
		
		//获得一个指向page页面的跳转对象
		RequestDispatcher rd = 
				request.getRequestDispatcher(page);
		
		//完成跳转
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//my_servlet表示将来要跳转到的页面
		String my_servlet = "";
		if("tom".equals(name)){
			my_servlet = "/ForwardMyServletA";
		}else{
			my_servlet = "/ForwardMyServletB";
		}
		
		//获得一个指向page页面的跳转对象
		RequestDispatcher rd = 
				request.getRequestDispatcher(my_servlet);
		
		//完成跳转
		rd.forward(request, response);
		
		
	}

}
