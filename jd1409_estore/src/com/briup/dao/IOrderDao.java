package com.briup.dao;

import java.util.List;
import java.util.Map;

import com.briup.bean.Order;
import com.briup.common.exception.DataAccessException;

public interface IOrderDao {
	//保存一个订单
	void saveOrder(Order order) throws DataAccessException;
	
	//删除一个订单
	void deleteOrder(Integer orderid) throws DataAccessException;
	
	//通过订单id查找一个订单
	Order findOrderByOrderid(Integer orderid) throws DataAccessException;
	
	//查找一个用户的所有订单
	List findOrderByUserid(String userid) throws DataAccessException;
	
	//查找所有的付款方式
	Map findAllPayway() throws DataAccessException;
	
	//查找用户第一页的订单
	List findOrderByPageNumber(Integer page,Integer userid) throws DataAccessException;
}
