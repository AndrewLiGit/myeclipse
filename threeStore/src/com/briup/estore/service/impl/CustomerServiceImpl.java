package com.briup.estore.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.HibernateSessionFactory;
import com.briup.estore.dao.CustomerDao;
import com.briup.estore.service.ICustomerService;
/**
 * 业务逻辑层
 *   处理业务逻辑
 *   事务的处理
 *   
 * */
public class CustomerServiceImpl implements ICustomerService {
	
	private CustomerDao customerDao = new CustomerDao();
	
	@Override
	public Customer login(String username, String password) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		//业务逻辑处理
		Customer customer 
			= customerDao.findCustomerByName(username);
		if(customer!=null){
			if(customer.getPassword().equals(password)){
				
			}else{
				System.out.println("密码不正确");
			}
		}else{
			System.out.println("用户名不存在");
		}
		
		tran.commit();
		return customer;
	}
	/**
	 * 不允许同名用户名
	 * 校验数据
	 * 
	 * */
	@Override
	public void register(Customer customer) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		//业务逻辑处理
		/*
		 * 1，判断该用户名在数据库中存在否
		 * 
		 * */
		Customer isCus = 
				customerDao.findCustomerByName(customer.getUsername());
		if(isCus == null){
			customerDao.saveOrUpdateCustomer(customer);
		}else{
			System.out.println("该用户名已经存在。。。");
		}
		tran.commit();
		
	}

}
