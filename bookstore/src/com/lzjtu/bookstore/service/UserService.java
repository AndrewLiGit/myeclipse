package com.lzjtu.bookstore.service;

import java.util.List;

import com.lzjtu.bookstore.exception.ParameterException;
import com.lzjtu.bookstore.exception.ServiceException;
import com.lzjtu.bookstore.model.ContactInfo;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.User;

public interface UserService {

	public User login(String userName, String password) throws ParameterException, ServiceException;

	public int save(User user, ContactInfo contactInfo);

	public ContactInfo getContactInfoByName(String userName);

	public List<User> list(Pagination pagination);

	public User getUser(String userName);

	public int getCountByName(String userName);


}
