package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		System.out.println("JSESSIONID : "+session.getId());
		
		Integer num = (Integer) session.getAttribute("num");
		
		if(num==null){
			num = 1;
			session.setAttribute("num",num);
		}
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
			out.println("<body>");
				out.println("<h1>session: num="+num+"</h1>");
			out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
		
		num++;
		session.setAttribute("num", num);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
