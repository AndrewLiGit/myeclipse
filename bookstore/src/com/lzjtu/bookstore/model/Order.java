package com.lzjtu.bookstore.model;

import java.util.Date;

public class Order {

	private int id;
	private float cost;
	private String orderNo;
	private int orderStatus;
	private int userId;
	private int payStyleId;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getPayStyleId() {
		return payStyleId;
	}
	
	public void setPayStyleId(int payStyleId) {
		this.payStyleId = payStyleId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
