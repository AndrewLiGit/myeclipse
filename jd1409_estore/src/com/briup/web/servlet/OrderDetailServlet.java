package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Order;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IOrderService;

public class OrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IOrderService service = ServiceFactory.getOrderService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer orderid = Integer.valueOf(request.getParameter("orderid"));
		Order order = null;
		try {
			order = service.listOrderByOrderid(orderid);
			request.setAttribute("order", order);
			request.getRequestDispatcher("user/orderinfo.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
