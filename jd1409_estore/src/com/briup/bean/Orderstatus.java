package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

public class Orderstatus implements java.io.Serializable {
	
	// Fields
	
	private Integer statusid;
	private String name;
	private String description;
	private Set orders = new HashSet(0);
	
	// Constructors
	
	/** default constructor */
	public Orderstatus() {
	}
	
	
	/**  full constructor  */
	public Orderstatus(String name,String description,Set orders) {
		this.name = name;
		this.description = description;
		this.orders = orders;
	}
	
	// property accessors
	
	public Integer getStatusid() {
		return statusid;
	}


	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
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


	public Set getOrders() {
		return orders;
	}


	public void setOrders(Set orders) {
		this.orders = orders;
	}
}
