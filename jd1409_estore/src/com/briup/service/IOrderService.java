package com.briup.service;

import java.util.List;
import java.util.Map;

import com.briup.bean.Order;
import com.briup.bean.User;
import com.briup.common.exception.ServiceException;

public interface IOrderService {
	//列出用户订单
	List listOrdersOfUser(User user) throws ServiceException;
	
	//删除订单
	void removeOrder(Integer orderid) throws ServiceException;
	
	//保存一个订单
	void saveOrder(Order order) throws ServiceException;
	
	//列出所有的付款方式
	Map listAllPayways() throws ServiceException;
	
	//通过订单id获取一个订单
	Order listOrderByOrderid(Integer orderid) throws ServiceException;
}
