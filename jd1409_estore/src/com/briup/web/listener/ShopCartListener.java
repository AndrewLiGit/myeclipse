package com.briup.web.listener;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.briup.bean.Payway;
import com.briup.bean.ShopCart;
import com.briup.common.exception.ServiceException;
import com.briup.common.util.ServiceFactory;
import com.briup.service.IOrderService;

public class ShopCartListener implements HttpSessionListener{

	private IOrderService service = ServiceFactory.getOrderService();
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("--- shopcart Listener ---");
		HttpSession session = arg0.getSession();
		ShopCart cart = new ShopCart();
		session.setAttribute("cart", cart);
		Map<Integer, Payway> payways = null;
		try {
			payways = service.listAllPayways();
			session.setAttribute("payways", payways);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("加载失败！");
			e.printStackTrace();
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		session.removeAttribute("cart");
	}

}
