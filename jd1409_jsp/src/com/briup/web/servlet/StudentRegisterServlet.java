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
		//1.接收数据
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//2.封装对象 如果需要的话
		Student student = new Student();
		student.setName(username);
		student.setAge(age);
		
		String msg = "";
		String page = "";
		try {
			//3.调用service方法进行业务逻辑处理
			service.register(student);
			msg = "注册成功";
			page = "/success.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "注册失败:"+e.getMessage();
			page = "/error.jsp";
		}
		
		//4.根据业务逻辑处理的结果进行跳转 并且把需要的数据带到页面
		request.setAttribute("msg",msg);
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
