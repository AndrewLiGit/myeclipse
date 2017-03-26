package com.briup.service.impl;

import com.briup.bean.User;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.ServiceException;
import com.briup.common.transaction.HibernateTransaction;
import com.briup.common.util.DaoFactory;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IUserDao;
import com.briup.service.IUserService;

public class UserServiceImpl implements IUserService{
	
	private IUserDao dao = DaoFactory.getUserDao();

	@Override
	public void updateUserinfo(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		try {
			User user2 = dao.findUserByUserid(user.getUserid());
			if(user2!=null){
				throw new ServiceException("该用户名已经存在！");
			}
			dao.saveUser(user);
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public User login(String userid, String password) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		User user = null;
		try {
			user = dao.findUserByUserid(userid);
			if(user==null){
				throw new ServiceException("该用户名不存在！");
			}
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return user;
	}

}
