package com.briup.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.bean.Product;
import com.briup.common.exception.DataAccessException;
import com.briup.common.util.HibernateSessionFactory;
import com.briup.dao.IProductDao;

public class ProductDaoImpl implements IProductDao{

	@Override
	public Map findAllProducts() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		Map<Integer, Product> map = new HashMap<Integer, Product>();
		for(Product p:list){
			System.out.println(p.getCategory().getName());
			map.put(p.getProductid(), p);
		}
		return map;
	}

	@Override
	public Product findProductById(Integer productid)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Product where productid = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, productid);
		List<Product> products = query.list();
		Product product = null;
		if(products.size()>0){
			product = products.get(0);
		}
		return product;
	}

}
