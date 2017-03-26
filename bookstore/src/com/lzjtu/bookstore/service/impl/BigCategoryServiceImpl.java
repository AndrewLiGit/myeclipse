package com.lzjtu.bookstore.service.impl;

import java.util.List;

import com.lzjtu.bookstore.dao.BigCategoryDao;
import com.lzjtu.bookstore.model.BigCategory;
import com.lzjtu.bookstore.service.BigCategoryService;

public class BigCategoryServiceImpl implements BigCategoryService {
	
	private BigCategoryDao bigCategoryDao;

	public void setBigCategoryDao(BigCategoryDao bigCategoryDao) {
		this.bigCategoryDao = bigCategoryDao;
	}


	@Override
	public List<BigCategory> list() {
		
		List<BigCategory> list = bigCategoryDao.list();
		return list;
	}

}
