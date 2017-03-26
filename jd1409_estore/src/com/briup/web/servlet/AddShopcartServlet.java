package com.briup.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Product;
import com.briup.bean.ShopCart;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IProductService;

public class AddShopcartServlet extends HttpServlet{

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
		
		String productid = req.getParameter("productid");
		ServletContext sc = req.getSession().getServletContext();
		System.out.println("((((");
		Map<Integer, Product> products = (Map<Integer, Product>) sc.getAttribute("products");
		System.out.println("8888");
		Product product = products.get(Integer.valueOf(productid));
		System.out.println(product);
		HttpSession session = req.getSession();
		ShopCart cart = (ShopCart) session.getAttribute("cart");
		System.out.println(cart);
		try {
			cart.addProduct(product);
			System.out.println("---");
			session.setAttribute("cart", cart);
			System.out.println("+++++++++++");
			session.setAttribute("msg", "添加成功！");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.setAttribute("msg", "添加失败，请重新添加！");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

}
