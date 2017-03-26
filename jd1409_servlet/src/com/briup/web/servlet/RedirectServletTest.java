package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//��doGet������ �ñ��η����ض���һ��ҳ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		//page��ʾ����Ҫ��ת����ҳ��
		//ע��:�ض����ʱ��һ��Ҫ��·����ʼ��/ȥ��
		String page = "";
		if("tom".equals(name)){
			page = "forwardA.html";
		}else{
			page = "forwardB.html";
		}
		
		//�ͻ����ض���pageҳ��
		response.sendRedirect(page);
		
		
	}
	
	//��doPost������ �ñ��η����ض�������һ��servlet��
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
