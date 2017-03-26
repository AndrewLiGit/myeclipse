package com.lzjtu.bookstore.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.BookOrderDao;
import com.lzjtu.bookstore.model.BookOrder;

public class BookOrderDaoImpl extends SqlSessionDaoSupport implements BookOrderDao {

	private static final String CLASS_NAME = BookOrder.class.getName();
	
	@Override
	public void save(BookOrder bookOrder) {
		
		getSqlSession().insert(CLASS_NAME + ".save", bookOrder);
	}

	@Override
	public List<BookOrder> getBookOrder(int orderId) {
		
		return getSqlSession().selectList(CLASS_NAME + ".getBookOrder", orderId);
	}

}
