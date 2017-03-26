package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;

public interface NoticeDao {

	public List<Notice> findNotice();

	public Notice getNoticeById(int id);

	public List<Notice> list(Pagination pagination);

	public void update(Notice notice);

	public void create(Notice notice);

	public void delete(int id);

	public int getCount();
}
