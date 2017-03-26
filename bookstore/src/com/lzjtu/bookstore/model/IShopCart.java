package com.lzjtu.bookstore.model;

import java.math.BigDecimal;
import java.util.Iterator;

public interface IShopCart {

    void addBook(Book book) throws Exception;
	
	void removeBook(Integer bookid) throws Exception;
	
	void removeAllBooks() throws Exception;
	
	void updateBook(Integer bookid,Integer number) throws Exception;
	
	BigDecimal getTotalPrice() throws Exception;
	
	Iterator<BookOrder> getBookOrders() throws Exception;
}
