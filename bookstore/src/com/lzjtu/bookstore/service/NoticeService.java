package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;

public interface NoticeService {

	public List<Notice> findNotice();

	public Notice getNoticeById(int id);

	public List<Notice> list(Pagination pagination);

	public Notice getNewsById(int id);

	public void save(Notice notice);

	public void delete(int id);

}
