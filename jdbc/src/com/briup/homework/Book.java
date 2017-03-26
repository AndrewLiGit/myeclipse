package com.briup.homework;

import java.sql.Date;
import java.text.SimpleDateFormat;
/*
  create  table book(
  		id number constraint book_id_pk primary key,
  		name varchar2(20) not null,
  		price number,
  		publishDate Date
  );
 */
public class Book {
	private long id;
	private String name;
	private double price;
	//发布日期
	private Date publishDate;
	
	public Book(){}
	public Book(long id, String name, double price, Date publishDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.publishDate = publishDate;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	public String getPublishDate() {
//		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yy");
//		String time = s.format(publishDate);
//		
//		return "to_date('"+time+"','dd-mm-yy')";
//	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price
				+ ", publishDate=" + publishDate + "]";
	}
	
}
