package com.lzjtu.bookstore.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.PayWayDao;
import com.lzjtu.bookstore.model.PayWay;

public class PayWayDaoImpl extends SqlSessionDaoSupport implements PayWayDao {

	private static final String CLASS_NAME = PayWay.class.getName();
	
	@Override
	public List<PayWay> findAllPayWay() {
		
		return getSqlSession().selectList(CLASS_NAME + ".findAllPayWay");
	}

}
