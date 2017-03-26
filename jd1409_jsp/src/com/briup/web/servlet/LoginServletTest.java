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
		
		//֮ǰдһ����̬ҳ��ķ�ʽ ҳ���������д����
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
			out.println("<body>");
				out.print("<h1>���:"+username+",��¼�ɹ�</h1>");
			out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();*/
		
		//����ʹ��jsp��Ϊ��̬ҳ�� ֱ����ת�����jspҳ��Ϳ�����
		request.setAttribute("username", username);
		
		request.getRequestDispatcher("/success.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
