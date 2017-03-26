package com.briup.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PMServletTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("name = "+name);
		System.out.println("age = "+age);
		
		String[] str = request.getParameterValues("like");
		
		System.out.println(Arrays.toString(str));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("name = "+name);
		System.out.println("age = "+age);

		String[] str = request.getParameterValues("like");
		
		System.out.println(Arrays.toString(str));
	}

}
