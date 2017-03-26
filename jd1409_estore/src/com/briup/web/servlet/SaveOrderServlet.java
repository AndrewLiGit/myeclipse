package com.briup.web.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Order;
import com.briup.bean.Orderline;
import com.briup.bean.Orderstatus;
import com.briup.bean.Payway;
import com.briup.bean.ShopCart;
import com.briup.bean.User;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IOrderService;

public class SaveOrderServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private IOrderService service = ServiceFactory.getOrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ShopCart cart = (ShopCart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		Map<Integer, Payway> payways = (Map<Integer, Payway>) session.getAttribute("payways");
		Order order = new Order();
		try {
			Iterator<Orderline> orderlines = cart.getOrderlines();
			while (orderlines.hasNext()) {
				Orderline orderline = orderlines.next();
				orderline.setOrder(order);
				order.getOrderlines().add(orderline);
			}
			order.setCost(cart.getTotalPrice().doubleValue());
			order.setFinished(1);
			order.setCardno(" ");
			order.setName("      ");
			Integer paywayid = new Integer(request.getParameter("payway"));
			System.out.println(paywayid+"===========saveOrder============");
			Payway payway = payways.get(paywayid);
			order.setPayway(payway);
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setStatusid(1);
			orderstatus.setName("pending");
			order.setOrderstatus(orderstatus);
			order.setUser(user);
			
			service.saveOrder(order);
			cart.removeAllProducts();
			/*List<Order> orders = service.listOrdersOfUser(user);
			session.setAttribute("orders", orders);*/
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("user/confirmOrder.jsp");
			e.printStackTrace();
		}
	}

}
