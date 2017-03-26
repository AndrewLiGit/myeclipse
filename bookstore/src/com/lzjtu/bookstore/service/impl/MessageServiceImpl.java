package com.lzjtu.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzjtu.bookstore.dao.MessageDao;
import com.lzjtu.bookstore.model.Message;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.MessageService;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public List<Message> findAllMessage(Pagination pagination) {
		
		List<Message> list = messageDao.findAllMessage(pagination);
		
		return list;
	}

	@Override
	public void save(Message message) {
		
		messageDao.save(message);
	}

	@Override
	public void delete(int id) {
		
		messageDao.delete(id);
	}

}
