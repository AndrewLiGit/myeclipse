package com.lzjtu.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lzjtu.bookstore.dao.ContactInfoDao;
import com.lzjtu.bookstore.dao.UserDao;
import com.lzjtu.bookstore.exception.ParameterException;
import com.lzjtu.bookstore.exception.ServiceException;
import com.lzjtu.bookstore.model.ContactInfo;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.User;
import com.lzjtu.bookstore.service.UserService;
import com.lzjtu.bookstore.util.StringUtil;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private ContactInfoDao contactInfoDao;

	public ContactInfoDao getContactInfoDao() {
		return contactInfoDao;
	}

	public void setContactInfoDao(ContactInfoDao contactInfoDao) {
		this.contactInfoDao = contactInfoDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String userName, String password) throws ParameterException, ServiceException {

		ParameterException parameterException = new ParameterException();

        if (StringUtil.isEmpty(userName)) {
            parameterException.addErrorField("userName", "用户名不能为空。");
        }

        if (StringUtil.isEmpty(password)) {
            parameterException.addErrorField("password", "密码不能为空。");
        }

        if (parameterException.isErrorField()) {
            throw parameterException;
        }
		User user = userDao.getUserByUserName(userName);

        if (user == null) {
            throw new ServiceException(1000, "用户名不存在。");
        }
        if (!password.equals(user.getPassword())) {
            throw new ServiceException(1001, "密码不正确。");
        }
		
		return user;
	}

	@Override
	public int save(User user, ContactInfo contactInfo) {
		
		int amount = userDao.save(user);
		
		contactInfoDao.save(contactInfo);
		
		return amount;
	}

	@Override
	public ContactInfo getContactInfoByName(String userName) {
		
		ContactInfo contactInfo = contactInfoDao.getContactInfoByName(userName);
		
		return contactInfo;
	}

	@Override
	public List<User> list(Pagination pagination) {
		
		List<User> list = new ArrayList<User>();
		list = userDao.list(pagination);
		return list;
	}

	@Override
	public User getUser(String userName) {
		User user = userDao.getUserByUserName(userName);
		return user;
	}

	@Override
	public int getCountByName(String userName) {
		
		return userDao.getCountByName(userName);
	}
	
}
