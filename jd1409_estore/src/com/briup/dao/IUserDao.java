package com.briup.dao;

import com.briup.bean.User;
import com.briup.common.exception.DataAccessException;

public interface IUserDao {
	//保存一个用户
	void saveUser(User user) throws DataAccessException;
	
	//更新一个用户
	void updateUser(User user) throws DataAccessException;
	
	//通过用户id查找一个用户
	User findUserByUserid(String userid) throws DataAccessException;

}
