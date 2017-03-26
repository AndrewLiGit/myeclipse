package com.briup.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.bean.User;
import com.briup.common.exception.DataAccessException;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IUserDao;

public class UserDaoImpl implements IUserDao{

	@Override
	public void saveUser(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.save(user);
	}

	@Override
	public void updateUser(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.update(user);
	}

	@Override
	public User findUserByUserid(String userid) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User where userid = ?"; 
		Query query = session.createQuery(hql);
		query.setString(0, userid);
		List<User> users = query.list();
		if(users.size()>0){
			User user = users.get(0);
			return user;
		}
		return null;
	}
	
	
	

}
