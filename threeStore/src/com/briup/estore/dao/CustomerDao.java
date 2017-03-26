package com.briup.estore.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.HibernateSessionFactory;

/**
 * 
 * 只能存在增删改查方法，不允许有业务逻辑的处理
 * 
 * */
public class CustomerDao {
	public void saveOrUpdateCustomer(Customer customer){
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(customer);
	}
	
	public Customer findCustomerByName(String username){
		Session session = HibernateSessionFactory.getSession();
		Query query = 
				session.createQuery("from Customer where username=?");
		query.setString(0, username);
		return (Customer) query.uniqueResult();
	}
}
