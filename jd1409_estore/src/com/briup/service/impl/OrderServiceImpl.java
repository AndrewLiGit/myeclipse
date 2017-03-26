package com.briup.service.impl;

import java.util.List;
import java.util.Map;

import javassist.compiler.ast.NewExpr;

import com.briup.bean.Order;
import com.briup.bean.Payway;
import com.briup.bean.User;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.ServiceException;
import com.briup.common.transaction.HibernateTransaction;
import com.briup.common.util.DaoFactory;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IOrderDao;
import com.briup.service.IOrderService;

public class OrderServiceImpl implements IOrderService{

	private IOrderDao dao = DaoFactory.getOrderDao();
	@Override
	public List listOrdersOfUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		List<Order> orders = null;
		try {
			orders = dao.findOrderByUserid(user.getUserid());
			if(orders==null){
				throw new ServiceException("当前没有订单！");
			}
		transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return orders;
	}

	@Override
	public void removeOrder(Integer orderid) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		try {
			dao.deleteOrder(orderid);
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			throw new ServiceException("删除失败！");
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void saveOrder(Order order) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		try {
			dao.saveOrder(order);
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			throw new ServiceException("提交订单失败！");
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}

	@Override
	public Map listAllPayways() throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		Map<Integer,Payway> payways = null;
		try {
			payways = dao.findAllPayway();
			if(payways == null){
				throw new ServiceException("支付方式加载失败！");
			}
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return payways;
	}

	@Override
	public Order listOrderByOrderid(Integer orderid) throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		Order order = null;
		try {
			order = dao.findOrderByOrderid(orderid);
			if(order == null){
				throw new ServiceException("加载失败！");
			}
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return order;
	}

}
