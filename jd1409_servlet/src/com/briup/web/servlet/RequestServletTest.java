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
		
		//�����ݱ��浽request������
		//request���������װ��һ��Map����
		//Map<String,Object>
		request.setAttribute("age", 20);
		
		//��request���õ��Ķ�����Object���͵�
		//��Ҫ�Ļ�����ǿ��ת��
		int age = (Integer) request.getAttribute("age");
		System.out.println("age = "+age);
		
		//ע��:����getParameter��getAttribute��������
		//getParameter�ǻ��ҳ�洫�����Ĳ�����
		//getAttribute�ǻ��֮ǰ����setAttribute�����ŵ�request�е����ݵ�
		String name = request.getParameter("name");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
