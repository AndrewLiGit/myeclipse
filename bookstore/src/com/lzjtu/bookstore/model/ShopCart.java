package com.lzjtu.bookstore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShopCart implements IShopCart {

	private Map<Integer,BookOrder> bookOrders = new HashMap<Integer,BookOrder>();
	
	public void addBook(Book book) throws Exception{
		System.out.println("进入ShopCart的add()方法！");
		System.out.println(book.getPrice()+ "??????????????????");
		int number = 1;
		Integer id = book.getId();
		if(bookOrders.containsKey(id)) {
			BookOrder bookOrder = (BookOrder) bookOrders.get(id);
			number = bookOrder.getNumber()+1;
			bookOrder.setNumber(new Integer(number));
		}else {
			BookOrder bookOrder = new BookOrder();
			bookOrder.setNumber(new Integer(number));
			bookOrder.setBook(book);
			bookOrders.put(id, bookOrder);
		}
	}

	public void removeBook(Integer bookid) throws Exception{
		System.out.println("进入ShopCart的removeProduct()方法！");
		bookOrders.remove(bookid);
	}

	public void removeAllBooks() throws Exception{
		System.out.println("进入ShopCart的removeAllProducts()方法！");
		bookOrders.clear();
	}
	
	public void updateBook(Integer bookid, Integer number) throws Exception{
		System.out.println("进入ShopCart的updateProduct()方法！");
		BookOrder bookOrder = bookOrders.get(bookid);
		bookOrder.setNumber((int)number);
	}

	public BigDecimal getTotalPrice() throws Exception{
		System.out.println("进入ShopCart的getTotalPrice()方法！");
		BigDecimal totalPrice = new BigDecimal(0);
		Iterator<BookOrder> iter = getBookOrders();
		while(iter.hasNext()) {
			BookOrder bookOrder = (BookOrder) iter.next();
			BigDecimal price = new BigDecimal(0);
			if(bookOrder.getBook().getIsSpecialPrice() == 0) {
				price = new BigDecimal(Float.toString(bookOrder.getBook().getSpecialPrice()));
			} else {
				price = new BigDecimal(Float.toString(bookOrder.getBook().getPrice()));
			}
			BigDecimal sum = price.multiply(new BigDecimal(Float.toString(bookOrder.getNumber())));
			totalPrice = totalPrice.add(sum);
			
		}
		return totalPrice;
	}

	public Iterator<BookOrder> getBookOrders() throws Exception{
		System.out.println("进入ShopCart的getOrderlines()方法！");
		return bookOrders.values().iterator();
	}

}
