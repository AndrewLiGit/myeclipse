package com.briup.estore.service;

import com.briup.estore.bean.Customer;

public interface ICustomerService {
	Customer login(String username,String password);
	
	void register(Customer customer);
}
