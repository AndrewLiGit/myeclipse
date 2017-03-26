package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Contactinfo;
import com.briup.bean.User;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IUserService;

public class ModifyUserinfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IUserService service = ServiceFactory.getUserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password =request.getParameter("password");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street1 = request.getParameter("street1");
		String street2 = request.getParameter("street2");
		String zip = request.getParameter("zip");
		String homephone = request.getParameter("homephone");
		String officephone = request.getParameter("officephone");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		user.setUserid(userid);
		user.setPassword(password);
		
		Contactinfo contactinfo = user.getContactinfo();
		
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
		
		user.setContactinfo(contactinfo);
		
		session.setAttribute("user", user);
		
		try {
			service.updateUserinfo(user);
			request.setAttribute("msg", "修改成功！");
			request.getRequestDispatcher("user/userinfo.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("user/userinfo.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
