package com.briup.estore.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	private Long id;
	private Date orderDate;
	private Double cost;
	
	private Customer customer;
	private Set<Line> lines = new HashSet<Line>();
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<Line> getLines() {
		return lines;
	}
	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}	
