package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		//之前写一个动态页面的方式 页面里面可以写变量
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
			out.println("<body>");
				out.print("<h1>你好:"+username+",登录成功</h1>");
			out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();*/
		
		//现在使用jsp作为动态页面 直接跳转到这个jsp页面就可以了
		request.setAttribute("username", username);
		
		request.getRequestDispatcher("/success.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
