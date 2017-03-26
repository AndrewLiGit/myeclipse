package com.lzjtu.bookstore.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.ContactInfoDao;
import com.lzjtu.bookstore.model.ContactInfo;

public class ContactInfoDaoImpl extends SqlSessionDaoSupport implements ContactInfoDao{

	private static final String CLASS_NAME = ContactInfo.class.getName();
	
	@Override
	public void save(ContactInfo contactInfo) {
		
		getSqlSession().insert(CLASS_NAME + ".save", contactInfo);
		
	}

	@Override
	public ContactInfo getContactInfoByName(String userName) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getContactInfoByName", userName);
	}

}
