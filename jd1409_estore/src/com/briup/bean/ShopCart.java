package com.briup.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShopCart implements IShopCart {
	
	private Map<Integer,Orderline> orderlines = new HashMap<Integer,Orderline>();
	
	public void addProduct(Product product) throws Exception{
		System.out.println("进入ShopCart的add()方法！");
		int number = 1;
		Integer id = product.getProductid();
		if(orderlines.containsKey(id)) {
			Orderline orderline = (Orderline) orderlines.get(id);
			number = orderline.getAmount()+1;
			orderline.setAmount(new Integer(number));
		}else {
			Orderline orderline = new Orderline();
			orderline.setAmount(new Integer(number));
			orderline.setProduct(product);
			orderlines.put(id, orderline);
		}
	}

	public void removeProduct(Integer productid) throws Exception{
		System.out.println("进入ShopCart的removeProduct()方法！");
		orderlines.remove(productid);
	}

	public void removeAllProducts() throws Exception{
		System.out.println("进入ShopCart的removeAllProducts()方法！");
		orderlines.clear();
	}
	
	public void updateProduct(Integer productid, Integer number) throws Exception{
		System.out.println("进入ShopCart的updateProduct()方法！");
		Orderline orderline = orderlines.get(productid);
		orderline.setAmount(number);
	}

	public BigDecimal getTotalPrice() throws Exception{
		System.out.println("进入ShopCart的getTotalPrice()方法！");
		BigDecimal totalPrice = new BigDecimal(0);
		Iterator<Orderline> iter = getOrderlines();
		while(iter.hasNext()) {
			Orderline o = (Orderline) iter.next();
			Product p = o.getProduct();
			BigDecimal sum = new BigDecimal(p.getBaseprice()*o.getAmount());
			totalPrice = totalPrice.add(sum);
		}
		return totalPrice;
	}

	public Iterator<Orderline> getOrderlines() throws Exception{
		System.out.println("进入ShopCart的getOrderlines()方法！");
		return orderlines.values().iterator();
	}
}
