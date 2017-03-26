package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.User;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IUserService;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private IUserService service = ServiceFactory.getUserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		User user = null;
		try {
			user = service.login(userid, password);
			if(user.getPassword().equals(password)){
				session.setAttribute("user", user);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else{
				req.setAttribute("msg", "用户名或密码错误！");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

}
