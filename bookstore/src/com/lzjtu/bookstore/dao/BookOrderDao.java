package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.BookOrder;

public interface BookOrderDao {

	void save(BookOrder bookOrder);

	List<BookOrder> getBookOrder(int orderId);

}
