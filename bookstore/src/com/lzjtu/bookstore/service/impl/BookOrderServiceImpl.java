package com.lzjtu.bookstore.service.impl;

import java.util.List;

import com.lzjtu.bookstore.dao.BookOrderDao;
import com.lzjtu.bookstore.model.BookOrder;
import com.lzjtu.bookstore.service.BookOrderService;

public class BookOrderServiceImpl implements BookOrderService {

	private BookOrderDao bookOrderDao;

	public void setBookOrderDao(BookOrderDao bookOrderDao) {
		this.bookOrderDao = bookOrderDao;
	}

	@Override
	public void save(BookOrder bookOrder) {
		
		bookOrderDao.save(bookOrder);
	}

	@Override
	public List<BookOrder> getBookOrder(int orderId) {
		
		List<BookOrder> list = bookOrderDao.getBookOrder(orderId);
		return list;
	}

}
