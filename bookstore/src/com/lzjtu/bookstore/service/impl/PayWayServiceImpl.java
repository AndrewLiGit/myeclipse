package com.lzjtu.bookstore.service.impl;

import java.util.List;

import com.lzjtu.bookstore.dao.PayWayDao;
import com.lzjtu.bookstore.model.PayWay;
import com.lzjtu.bookstore.service.PayWayService;

public class PayWayServiceImpl implements PayWayService {

	private PayWayDao payWayDao;
	
	public void setPayWayDao(PayWayDao payWayDao) {
		this.payWayDao = payWayDao;
	}

	@Override
	public List<PayWay> findAllPayWay() {
		
		List<PayWay> list = payWayDao.findAllPayWay();
		
		return list;
	}

}
