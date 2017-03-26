package com.briup.dao;

import java.util.Map;

import com.briup.bean.Product;
import com.briup.common.exception.DataAccessException;

public interface IProductDao {
	//查询所有产品
	Map findAllProducts() throws DataAccessException;
	
	//通过产品id查找一个产品
	Product findProductById(Integer productid) throws DataAccessException;
}
