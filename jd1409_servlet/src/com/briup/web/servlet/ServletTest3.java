package com.briup.web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletTest3");
		
		response.setContentType("image/png");
		
		ServletOutputStream out = response.getOutputStream();
		
		//可以写一个相对于当前类所在位置的相对路径
		InputStream in = this.getClass().getResourceAsStream("1.png");
		
		byte[] buf = new byte[100];
		
		int len = in.read(buf);
		
		while(len!=-1){
			out.write(buf,0,len);
			len = in.read(buf);
		}
		
		out.flush();
		out.close();
		in.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
