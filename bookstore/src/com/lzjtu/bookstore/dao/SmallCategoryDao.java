package com.lzjtu.bookstore.dao;

import java.util.List;

public interface SmallCategoryDao {

	List<Integer> findByName(String category);

}
