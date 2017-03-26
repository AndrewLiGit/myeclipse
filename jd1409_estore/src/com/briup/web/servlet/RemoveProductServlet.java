package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.ShopCart;

public class RemoveProductServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

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
		HttpSession session = req.getSession();
		ShopCart cart = (ShopCart) session.getAttribute("cart");
		Integer productid = Integer.valueOf(req.getParameter("productid"));
		try {
			cart.removeProduct(productid);
			req.setAttribute("msg", "删除成功！");
			req.getRequestDispatcher("shopcart.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.setAttribute("msg", "删除失败！");
			req.getRequestDispatcher("shopcart.jsp").forward(req, resp);
			e.printStackTrace();
		}
		
	}

}
