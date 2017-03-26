package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Contactinfo;
import com.briup.bean.User;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IUserService;

public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IUserService service = ServiceFactory.getUserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		if(password.equals(password2)){
			String userid = req.getParameter("userid");
			String country = req.getParameter("country");
			String province = req.getParameter("province");
			String city = req.getParameter("city");
			String street1 = req.getParameter("street1");
			String street2 = req.getParameter("street2");
			String zip = req.getParameter("zip");
			String homephone = req.getParameter("homephone");
			String officephone = req.getParameter("officephone");
			String cellphone = req.getParameter("cellphone");
			String email = req.getParameter("email");
		
			Contactinfo contactinfo = new Contactinfo();
			contactinfo.setCountry(country);
			contactinfo.setProvince(province);
			contactinfo.setCity(city);
			contactinfo.setStreet1(street1);
			contactinfo.setStreet2(street2);
			contactinfo.setZip(zip);
			contactinfo.setHomephone(homephone);
			contactinfo.setOfficephone(officephone);
			contactinfo.setCellphone(cellphone);
			contactinfo.setEmail(email);
			
			User user = new User();
			user.setUserid(userid);
			user.setPassword(password);
			
			user.setContactinfo(contactinfo);
			contactinfo.setUser(user);
			
			try {
				service.registerUser(user);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				req.setAttribute("msg", e.getMessage());
				req.getRequestDispatcher("register.jsp").forward(req, resp);
				e.printStackTrace();
			}
			
		}else {
			
			req.setAttribute("msg", "密码不一致，请重新输入");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			
		}
	}

}
