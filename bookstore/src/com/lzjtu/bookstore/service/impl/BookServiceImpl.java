package com.lzjtu.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzjtu.bookstore.dao.BigCategoryDao;
import com.lzjtu.bookstore.dao.BookDao;
import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.BookService;
import com.lzjtu.bookstore.util.PropertyUtil;
import com.lzjtu.bookstore.util.StringUtil;

public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BigCategoryDao bigCategoryDao;
	
//	@Autowired
//	private SmallCategoryDao smallCategoryDao;
	
	public void setBigCategoryDao(BigCategoryDao bigCategoryDao) {
		this.bigCategoryDao = bigCategoryDao;
	}

//	public void setSmallCategoryDao(SmallCategoryDao smallCategoryDao) {
//		this.smallCategoryDao = smallCategoryDao;
//	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> findHot() {

		String hot = PropertyUtil.getProperty("index.hotBookAmount");
		
		int hotAmount = Integer.parseInt(hot);
		
		List<Book> hotList = bookDao.findHot(hotAmount);
		
		return hotList;
	}

	@Override
	public List<Book> findSpecial() {
		
		String specialPrice = PropertyUtil.getProperty("index.specialPriceBookAmount");
		
		int specialPriceAmount = Integer.parseInt(specialPrice);
		
		List<Book> specialPriceList = bookDao.findSpecialPrice(specialPriceAmount);
		
		return specialPriceList;
	}

	@Override
	public List<Book> findByKeyword(Pagination pagination, String keyword) {
		List<Book> bookByKeyword = new ArrayList<Book>();

        keyword = StringUtil.keywordChange(keyword);
        bookByKeyword = bookDao.findByKeyword(pagination, keyword);
        return bookByKeyword;
	}

	@Override
	public List<Book> findByCategory(Pagination pagination, String category) {
		
		int bigCategoryId = bigCategoryDao.getByName(category);
		
//		List<Integer> smallCategoryId = smallCategoryDao.findByName(category);
//		
//		int[] smallCategoryIdArray = new int[smallCategoryId.size()];
//		
//		for(int i = 0; i < smallCategoryId.size(); i ++) {
//			smallCategoryIdArray[i] = smallCategoryId.get(i);
//		}

		List<Book> bookByCategory = bookDao.findByCategory(pagination, bigCategoryId);
		
        return bookByCategory;
	}

	@Override
	public Book getBookById(int id) {
		
		Book book = bookDao.gtBookById(id);
		return book;
	}

	@Override
	public List<Book> list(Pagination pagination) {
		
		List<Book> list = bookDao.list(pagination);
		return list;
	}

	@Override
	public void deleteById(int id) {
		bookDao.deleteById(id);
	}

	@Override
	public void save(Book book) {
		
		if(book.getId() > 0) {
			bookDao.update(book);
		} else {
			bookDao.create(book);
		}
	}

}
