package com.lzjtu.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzjtu.bookstore.dao.NoticeDao;
import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	@Override
	public List<Notice> findNotice() {

		List<Notice> list = noticeDao.findNotice();
		return list;
	}

	@Override
	public Notice getNoticeById(int id) {

		Notice notice = noticeDao.getNoticeById(id);
		
		return notice;
	}

	@Override
	public List<Notice> list(Pagination pagination) {
		
		List<Notice> list = noticeDao.list(pagination);
		return list;
	}

	@Override
	public Notice getNewsById(int id) {
		
		Notice notice = noticeDao.getNoticeById(id);
		
		return notice;
	}

	@Override
	public void save(Notice notice) {
		
		if(notice.getId() > 0) {
			noticeDao.update(notice);
		} else {
			noticeDao.create(notice);
		}
	}

	@Override
	public void delete(int id) {
		
		noticeDao.delete(id);
	}
	
}
