package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.OrderDao;
import com.lzjtu.bookstore.model.Order;
import com.lzjtu.bookstore.model.Pagination;

public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao{

	private static final String CLASS_NAME = Order.class.getName();
	
	@Override
	public void save(Order order) {
		
		getSqlSession().insert(CLASS_NAME + ".save", order);
	}

	@Override
	public int getOrderId(int userId) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getOrderId", userId);
	}

	@Override
	public List<Order> list(Pagination pagination) {
		pagination.setTotalCount(this.getCount());
		if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
		
		return getSqlSession().selectList(CLASS_NAME + ".list", params);
	}

	@Override
	public int getCount() {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCount");
	}

	@Override
	public Order getByOrderId(int orderId) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getByOrderId", orderId);
	}

	@Override
	public List<Order> listByUserId(Pagination pagination, int userId) {
		pagination.setTotalCount(this.getCount());
		if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
        params.put("userId", userId);
		
		return getSqlSession().selectList(CLASS_NAME + ".listByUserId", params);
	}

	@Override
	public int getCountByUserId(int userId) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCountByUserId", userId);
	}

}
