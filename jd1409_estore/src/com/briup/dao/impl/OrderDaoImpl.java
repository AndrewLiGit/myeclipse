package com.briup.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.bean.Order;
import com.briup.bean.Orderline;
import com.briup.bean.Payway;
import com.briup.common.exception.DataAccessException;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IOrderDao;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public void saveOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.save(order);
	}

	@Override
	public void deleteOrder(Integer orderid) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Order order = findOrderByOrderid(orderid);
		if(order == null){
			throw new DataAccessException("要删除的订单不存在！");
		}
		session.delete(order);
	}

	@Override
	public Order findOrderByOrderid(Integer orderid) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Order where orderid = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, orderid);
		List<Order> orders = query.list();
		Order order = null;
		if(orders.size()>0){
			order = orders.get(0);
		}
		return order;
	}

	@Override
	public List findOrderByUserid(String userid) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Order where userid = ?";
		Query query = session.createQuery(hql);
		query.setString(0, userid);
		List<Orderline> orderlines = query.list();
		//System.out.println(orderlines.size());
		return orderlines;
	}

	@Override
	public Map findAllPayway() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Payway";
		Query query = session.createQuery(hql);
		List<Payway> payways = query.list();
		Map<Integer, Payway> map = new HashMap<Integer, Payway>();
		if(payways.size()>0){
			for(Payway payway:payways){
				map.put(payway.getPaywayid(), payway);
			}
		}
		return map;
	}

	@Override
	public List findOrderByPageNumber(Integer page, Integer userid)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
