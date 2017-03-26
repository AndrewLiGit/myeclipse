package com.lzjtu.bookstore.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.SmallCategoryDao;
import com.lzjtu.bookstore.model.SmallCategory;

public class SmallCategoryDaoImpl extends SqlSessionDaoSupport implements SmallCategoryDao {

	private static final String CLASS_NAME = SmallCategory.class.getName();
	
	@Override
	public List<Integer> findByName(String category) {
		
		return getSqlSession().selectList(CLASS_NAME + ".findByName", category);
	}

}
