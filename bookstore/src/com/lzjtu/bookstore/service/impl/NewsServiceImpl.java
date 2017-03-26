package com.lzjtu.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzjtu.bookstore.dao.NewsDao;
import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.NewsService;

public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsDao newsDao;

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	@Override
	public List<News> findNews() {
		
		List<News> list = newsDao.findNews();
		return list;
	}

	@Override
	public News getNewsById(int id) {
		
		News news = newsDao.getNewsById(id);
		
		return news;
	}

	@Override
	public List<News> list(Pagination pagination) {
		List<News> list = newsDao.list(pagination);
		return list;
	}

	@Override
	public void save(News news) {
		
		if(news.getId() > 0) {
			newsDao.update(news);
		} else {
			newsDao.create(news);
		}
	}

	@Override
	public void delete(int id) {
		
		newsDao.delete(id);
	}
	
}
