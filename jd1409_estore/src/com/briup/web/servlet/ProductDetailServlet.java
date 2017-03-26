package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Product;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IProductService;

public class ProductDetailServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private IProductService service = ServiceFactory.getProductService();

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
		Product product = null;
		HttpSession session = req.getSession();
		try {
			product = service.getProductByProductid(Integer.valueOf(productid));
			System.out.println(product);
			System.out.println(product.getPages());
			System.out.println(product.getDescription());
			System.out.println(product.getCategory().getName());
//			System.out.println("111111111111111111111111111111111111");
			session.setAttribute("product", product);
			req.getRequestDispatcher("productdetail.jsp").forward(req, resp);
//			System.out.println("00000");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

	
}
