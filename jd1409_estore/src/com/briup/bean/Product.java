package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

public class Product implements java.io.Serializable {

	//Fields
	
	private Integer productid;
	private String name;
	private String description;
	private Double baseprice;
	private Category category;
	private String writer;
	private String publish;
	private long pages;
	private String images;
	private Set orderline = new HashSet(0);
	
	//Construcotrs
	
	/** default constructor*/
	public Product() {
	}
	
	/** minimal constructor*/
	public Product(String name,double baseprice) {
		this.name = name;
		this.baseprice = baseprice;
	}
	
	/** full constructor*/
	public Product(String name,String description,Double baseprice,Category category,
			    String writer,String publish,long pages,String images,Set orderline) {
		this.name = name;
		this.description = description;
		this.baseprice = baseprice;
		this.category = category;
		this.writer = writer;
		this.publish = publish;
		this.pages = pages;
		this.images = images;
		this.orderline = orderline;
	}

	//Property accessors
	
	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(Double baseprice) {
		this.baseprice = baseprice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Set getOrderline() {
		return orderline;
	}

	public void setOrderline(Set orderline) {
		this.orderline = orderline;
	}
}
