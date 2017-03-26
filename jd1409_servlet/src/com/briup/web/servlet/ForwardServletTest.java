package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//��doGet������ �ñ��η�����ת��һ��ҳ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//page��ʾ����Ҫ��ת����ҳ��
		String page = "";
		if("tom".equals(name)){
			page = "/forwardA.html";
		}else{
			page = "/forwardB.html";
		}
		
		//���һ��ָ��pageҳ�����ת����
		RequestDispatcher rd = 
				request.getRequestDispatcher(page);
		
		//�����ת
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//my_servlet��ʾ����Ҫ��ת����ҳ��
		String my_servlet = "";
		if("tom".equals(name)){
			my_servlet = "/ForwardMyServletA";
		}else{
			my_servlet = "/ForwardMyServletB";
		}
		
		//���һ��ָ��pageҳ�����ת����
		RequestDispatcher rd = 
				request.getRequestDispatcher(my_servlet);
		
		//�����ת
		rd.forward(request, response);
		
		
	}

}
