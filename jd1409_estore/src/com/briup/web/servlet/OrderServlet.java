package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Order;
import com.briup.bean.User;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IOrderService;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IOrderService service = ServiceFactory.getOrderService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		List<Order> orders = null;
		if(user==null){
			request.setAttribute("msg", "亲，您还没登陆，请先登录！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			try {
				orders = service.listOrdersOfUser(user);
				request.setAttribute("orders", orders);
				request.getRequestDispatcher("/user/order.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/user/order.jsp").forward(request, response);
				e.printStackTrace();
			}
			
			
		}
	}

}
