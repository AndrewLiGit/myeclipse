package com.briup.estore.bean;

import java.util.HashSet;
import java.util.Set;

public class Product {
	private Long id;
	private String productName;
	private Double price;
	
	private Set<Line> lines = new HashSet<Line>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<Line> getLines() {
		return lines;
	}
	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
}
