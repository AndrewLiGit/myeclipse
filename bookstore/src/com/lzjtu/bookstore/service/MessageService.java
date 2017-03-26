package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.model.Message;
import com.lzjtu.bookstore.model.Pagination;

public interface MessageService {

	List<Message> findAllMessage(Pagination pagination);

	void save(Message message);

	void delete(int id);

}
