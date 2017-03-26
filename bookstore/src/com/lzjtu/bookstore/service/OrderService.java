package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.Order;
import com.lzjtu.bookstore.model.Pagination;

public interface OrderService {

	int save(Order order, int userId);

	List<Order> list(Pagination pagination);

	Order getById(int orderId);

	List<Order> listByUserId(Pagination pagination, int userId);

}
