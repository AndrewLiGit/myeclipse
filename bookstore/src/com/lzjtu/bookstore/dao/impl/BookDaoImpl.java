package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.BookDao;
import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.Pagination;

public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao{

	private static final String CLASS_NAME = Book.class.getName();
	
	@Override
	public List<Book> findHot(int hotAmount) {
		
		return getSqlSession().selectList(CLASS_NAME + ".findHot", hotAmount);
	}

	@Override
	public List<Book> findSpecialPrice(int specialPriceAmount) {
		
		return getSqlSession().selectList(CLASS_NAME + ".findSpecialPrice", specialPriceAmount);
	}

	@Override
	public List<Book> findByKeyword(Pagination pagination, String keyword) {
		pagination.setTotalCount(this.getCountByKeyword(keyword));
        if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
        params.put("keyWord", "%" + keyword + "%");
        return getSqlSession().selectList(CLASS_NAME + ".findByKeyword", params);
	}

	@Override
    public int getCountByKeyword(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + ".getCountByKeyword", "%" + keyword + "%");
    }

	@Override
	public List<Book> findByCategory(Pagination pagination, int bigCategoryId) {
		pagination.setTotalCount(this.getCountByCategory(bigCategoryId));
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
		params.put("bigCategoryId", bigCategoryId);
//		params.put("smallCategoryId", smallCategoryId);
		
		return getSqlSession().selectList(CLASS_NAME + ".findByCategory", params);
	}
	
	@Override
    public int getCountByCategory(@Param(value="CategoryId") Integer CategoryId) {
        return getSqlSession().selectOne(CLASS_NAME + ".getCountByCategory", CategoryId);
    }

	@Override
	public Book gtBookById(int id) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".gtBookById", id);
	}

	@Override
	public List<Book> list(Pagination pagination) {
		
		pagination.setTotalCount(this.getCount());
		if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
		
		return getSqlSession().selectList(CLASS_NAME + ".list", params);
	}

	@Override
	public int getCount() {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCount");
	}

	@Override
	public void deleteById(int id) {
		
		getSqlSession().update(CLASS_NAME + ".deleteById", id);
	}

	@Override
	public void update(Book book) {
		
		getSqlSession().update(CLASS_NAME + ".update", book);
	}

	@Override
	public void create(Book book) {
		
		getSqlSession().insert(CLASS_NAME + ".create", book);
	}

	
}
