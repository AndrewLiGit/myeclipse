package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if("tom".equals(username)&&"123".equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("login_flag", "1");
			String page = "/user/success.html";
			request.getRequestDispatcher(page).forward(request, response);
		}else{
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
				out.println("<body>");
					out.println("<h3>用户名或者密码错误,请重新登录</h3>");
					out.println("<a href='/jd1409_servlet/login.html'>前往登录</a>");
				out.println("</body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
