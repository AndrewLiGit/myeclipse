package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Pagination;

public interface NewsDao {

	public List<News> findNews();

	public News getNewsById(int id);

	public List<News> list(Pagination pagination);

	public int getCount();

	public void update(News news);

	public void create(News news);

	public void delete(int id);
}
