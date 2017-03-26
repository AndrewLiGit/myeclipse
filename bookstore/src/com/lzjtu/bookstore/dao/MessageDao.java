package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.Message;
import com.lzjtu.bookstore.model.Pagination;

public interface MessageDao {

	List<Message> findAllMessage(Pagination pagination);

	int getCount();

	void save(Message message);

	void delete(int id);
}
