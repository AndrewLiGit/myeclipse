package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.Pagination;

public interface BookDao {

	List<Book> findHot(int hotAmount);

	List<Book> findSpecialPrice(int specialPriceAmount);

	List<Book> findByKeyword(Pagination pagination, String keyword);

	int getCountByKeyword(String keyword);

	List<Book> findByCategory(Pagination pagination, int bigCategoryId);

	Book gtBookById(int id);

	int getCountByCategory(Integer bigCategoryId);

	List<Book> list(Pagination pagination);
	
	public int getCount();

	void deleteById(int id);

	void update(Book book);

	void create(Book book);

}
