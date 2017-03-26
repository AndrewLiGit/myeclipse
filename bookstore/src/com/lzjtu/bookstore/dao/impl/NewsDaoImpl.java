package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.NewsDao;
import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Pagination;

public class NewsDaoImpl extends SqlSessionDaoSupport implements NewsDao {

	private static final String CLASS_NAME = News.class.getName();
	
	@Override
	public List<News> findNews() {
		
		return getSqlSession().selectList(CLASS_NAME + ".findNews");
	}

	@Override
	public News getNewsById(int id) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getNewsById", id);
	}

	@Override
	public List<News> list(Pagination pagination) {
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
	public void update(News news) {
		
		getSqlSession().update(CLASS_NAME + ".update", news);
	}

	@Override
	public void create(News news) {
		
		getSqlSession().insert(CLASS_NAME + ".create", news);
	}

	@Override
	public void delete(int id) {
		
		getSqlSession().delete(CLASS_NAME + ".delete", id);
	}

}
