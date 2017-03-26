package com.lzjtu.bookstore.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import com.lzjtu.bookstore.dao.BigCategoryDao;
import com.lzjtu.bookstore.model.BigCategory;

public class BigCategoryDaoImpl extends SqlSessionDaoSupport implements BigCategoryDao {

	private static final String CLASS_NAME = BigCategory.class.getName();
	
	@Override
	public int getByName(String category) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getByName", category);
	}

	@Override
	public List<BigCategory> list() {
		
		return getSqlSession().selectList(CLASS_NAME + ".list");
	}

}
