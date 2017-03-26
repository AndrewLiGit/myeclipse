package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

public class Payway implements java.io.Serializable{

	//Fields 
	
	private Integer paywayid;
	private String paystyle;
	private Set orders = new HashSet(0);
	
	//Constructors
	
	/** default constructor */
	public Payway() {
	}
	
	/** full constructor */
	public Payway(String paystyle,Set orders) {
		this.paystyle = paystyle;
		this.orders = orders;
	}

	// property accessors
	
	public Integer getPaywayid() {
		return paywayid;
	}

	public void setPaywayid(Integer paywayid) {
		this.paywayid = paywayid;
	}

	public String getPaystyle() {
		return paystyle;
	}

	public void setPaystyle(String paystyle) {
		this.paystyle = paystyle;
	}

	public Set getOrders() {
		return orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}
}
