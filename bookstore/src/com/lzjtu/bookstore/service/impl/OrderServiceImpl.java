package com.lzjtu.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzjtu.bookstore.dao.OrderDao;
import com.lzjtu.bookstore.model.Order;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public int save(Order order, int userId) {
		orderDao.save(order);
		int orderId = orderDao.getOrderId(userId);
		return orderId;
	}

	@Override
	public List<Order> list(Pagination pagination) {
		
		List<Order> list = new ArrayList<Order>();
		list = orderDao.list(pagination);
		return list;
	}

	@Override
	public Order getById(int orderId) {

		Order order = orderDao.getByOrderId(orderId);
		return order;
	}

	@Override
	public List<Order> listByUserId(Pagination pagination, int userId) {
		List<Order> list = new ArrayList<Order>();
		list = orderDao.listByUserId(pagination, userId);
		return list;
	}

	
}
