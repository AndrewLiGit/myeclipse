package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.Order;
import com.lzjtu.bookstore.model.Pagination;

public interface OrderDao {

	void save(Order order);

	int getOrderId(int userId);

	List<Order> list(Pagination pagination);
	
	public int getCount();

	Order getByOrderId(int orderId);

	List<Order> listByUserId(Pagination pagination, int userId);
	
	public int getCountByUserId(int userId);

}
