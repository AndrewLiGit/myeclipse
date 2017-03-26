package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletTest2 extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("ServletTest2");
		
		res.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
			out.print("<head>");
				out.println("<title>");
					out.print("test");
				out.println("</title>");
			out.print("</head>");
			
			out.print("<body>");
				out.println("<h1>");
					out.println("<font color='red'>hello world jd1409</font>");
				out.println("</h1>");
				out.println("<input type='button' value='test' onclick='javascript:alert(\"hello\")'>");
			out.print("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
		
	}
	
}
