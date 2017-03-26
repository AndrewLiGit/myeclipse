package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Pagination;

public interface NewsService {

	public List<News> findNews();

	public News getNewsById(int id);

	public List<News> list(Pagination pagination);

	public void save(News news);

	public void delete(int id);

}
