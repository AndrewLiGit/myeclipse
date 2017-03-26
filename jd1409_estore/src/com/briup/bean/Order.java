package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

public class Order implements java.io.Serializable {

	//Fields
	
	private Integer orderid;
	private String name;
	private Integer finished;
	private Double cost;
	private String cardno;
	private User user;
	private Orderstatus orderstatus;
	private Payway payway;
	private Set orderlines = new HashSet(0);
	
	//constructors
	
	/** default constructor */
	public Order() {
	}
	
	/** full constructor */
	public Order(String name,Integer finished,Double cost,String cardno,User user,
			Orderstatus orderstatus,Payway payway,Set orderlines ) {
		this.name = name;
		this.finished = finished;
		this.cost = cost;
		this.cardno = cardno;
		this.user = user;
		this.orderstatus = orderstatus;
		this.payway = payway;
		this.orderlines = orderlines;
	}

	//property accessors
	
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Orderstatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Payway getPayway() {
		return payway;
	}

	public void setPayway(Payway payway) {
		this.payway = payway;
	}

	public Set getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(Set orderlines) {
		this.orderlines = orderlines;
	}
}
