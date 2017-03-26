package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.BookOrder;

public interface BookOrderService {

	void save(BookOrder bookOrder);

	List<BookOrder> getBookOrder(int orderId);

}
