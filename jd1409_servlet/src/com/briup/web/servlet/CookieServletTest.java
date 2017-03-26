package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		session.setAttribute("flag","true");
		
		Cookie c1 = new Cookie("name","tom");
		c1.setMaxAge(60*60*24*365*20);
		
		Cookie c2 = new Cookie("age","20");
		c2.setMaxAge(10);
		
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		String url = response.encodeURL("ShowCookieServletTest");
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
			out.println("<body>");
				out.println("<a href='"+url+"'>œ‘ æcookie</a>");
			out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
