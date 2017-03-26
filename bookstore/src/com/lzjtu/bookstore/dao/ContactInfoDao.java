package com.lzjtu.bookstore.dao;

import com.lzjtu.bookstore.model.ContactInfo;

public interface ContactInfoDao {

	public void save(ContactInfo contactInfo);

	public ContactInfo getContactInfoByName(String userName);

}
