package com.briup.service.impl;

import java.util.Map;
import java.util.Set;

import com.briup.bean.Product;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.ServiceException;
import com.briup.common.transaction.HibernateTransaction;
import com.briup.common.util.DaoFactory;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IProductDao;
import com.briup.service.IProductService;

public class ProductServiceImpl implements IProductService{

	IProductDao dao = DaoFactory.getProductDao();
	@Override
	public Map listAllProducts() throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		Map<Integer, Product> products = null;
		try {
			products = dao.findAllProducts();
//			Set<Integer> set = products.keySet();
//			for(Integer i:set){
//				Product product = products.get(i);
//				System.out.println(product.getCategory().getName());
//			}
			if(products==null){
				throw new ServiceException("没有商品！");
			}
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return products;
	}

	@Override
	public Product getProductByProductid(Integer productid)
			throws ServiceException {
		// TODO Auto-generated method stub
		HibernateTransaction transaction = new HibernateTransaction();
		transaction.beginTransaction();
		Product product = null;
		try {
			product = dao.findProductById(productid);
			if(product==null){
				throw new ServiceException("该产品不存在！");
			}
			System.out.println(product.getCategory().getName()+"-----service category");
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return product;
	}

}
