package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.Pagination;

public interface BookService {

	public List<Book> findHot();

	public List<Book> findSpecial();

	public List<Book> findByKeyword(Pagination pagination, String keyword);

	public List<Book> findByCategory(Pagination pagination, String category);

	public Book getBookById(int id);

	public List<Book> list(Pagination pagination);

	public void deleteById(int id);

	public void save(Book book);

}
