package com.briup.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.ShopCart;

public class ClearCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShopCart cart = (ShopCart) session.getAttribute("cart");
		try {
			cart.removeAllProducts();
			request.setAttribute("msg", "购物车已清空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "购物车清空失败！");
			
			e.printStackTrace();
		}
	}

}
