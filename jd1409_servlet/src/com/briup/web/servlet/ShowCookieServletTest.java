package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCookieServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获得浏览器带过来的cookie
		Cookie[] cookies = request.getCookies();
		
		HttpSession session = request.getSession();
		String flag = (String) session.getAttribute("flag");
		
		System.out.println("flag = "+flag);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
			out.println("<body>");
				out.println("<h1>本次访问服务器端接收到的cookie:</h1>");
				
				if(cookies!=null){
					for(Cookie c:cookies){
						String name = c.getName();
						String value = c.getValue();
						out.println("<h3>"+name+" : "+value+"</h3>");
					}
				}
				
			out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
