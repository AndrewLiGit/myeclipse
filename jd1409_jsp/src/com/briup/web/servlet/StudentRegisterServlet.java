package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Student;
import com.briup.service.IStudentService;
import com.briup.service.StudentServiceImpl;

public class StudentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IStudentService service = new StudentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��������
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//2.��װ���� �����Ҫ�Ļ�
		Student student = new Student();
		student.setName(username);
		student.setAge(age);
		
		String msg = "";
		String page = "";
		try {
			//3.����service��������ҵ���߼�����
			service.register(student);
			msg = "ע��ɹ�";
			page = "/success.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "ע��ʧ��:"+e.getMessage();
			page = "/error.jsp";
		}
		
		//4.����ҵ���߼�����Ľ��������ת ���Ұ���Ҫ�����ݴ���ҳ��
		request.setAttribute("msg",msg);
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
