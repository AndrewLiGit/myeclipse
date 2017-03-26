package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//request.getSession().getServletContext();
		ServletContext application = 
				request.getSession().getServletContext();
		
		Integer num = (Integer) application.getAttribute("num");
		
		if(num==null){
			num = 1;
			application.setAttribute("num",num);
		}
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
			out.println("<body>");
				out.println("<h1>application: num="+num+"</h1>");
			out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
		
		num++;
		application.setAttribute("num", num);
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
