package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//在doGet方法中 让本次访问重定向到一个页面中
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//page表示将来要跳转到的页面
		//注意:重定向的时候一定要把路径开始的/去掉
		String page = "";
		if("tom".equals(name)){
			page = "forwardA.html";
		}else{
			page = "forwardB.html";
		}
		
		//客户端重定向到page页面
		response.sendRedirect(page);
		
		
	}
	
	//在doPost方法中 让本次访问重定向到另外一个servlet中
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
