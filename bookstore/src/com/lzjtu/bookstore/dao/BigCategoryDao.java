package com.lzjtu.bookstore.dao;

import java.util.List;

import com.lzjtu.bookstore.model.BigCategory;

public interface BigCategoryDao {

	int getByName(String category);

	List<BigCategory> list();

}
