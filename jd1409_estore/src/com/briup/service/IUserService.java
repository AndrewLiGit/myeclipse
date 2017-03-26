package com.briup.service;

import com.briup.bean.User;
import com.briup.common.exception.ServiceException;

public interface IUserService {
	//更新用户信息
	void updateUserinfo(User user) throws ServiceException;
	
	//注册一个新用户
	void registerUser(User user) throws ServiceException;
	
	//登录
	User login(String userid,String password) throws ServiceException;
}
