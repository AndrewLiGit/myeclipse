package com.briup.service;

import java.util.Map;

import com.briup.bean.Product;
import com.briup.common.exception.ServiceException;

public interface IProductService {
	//列出所有产品
	Map listAllProducts() throws ServiceException;
	
	//通过产品id得到一个产品
	Product getProductByProductid(Integer productid) throws ServiceException;
}


