package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.User;

public interface UserDao {

	public User getUserByUserName(String userName);

	public int save(User user);

	public List<User> list(Pagination pagination);
	
	public int getCount();

	public int getCountByName(String userName);

}
