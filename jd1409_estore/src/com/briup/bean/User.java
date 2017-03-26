package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

public class User implements java.io.Serializable{

	// Fields

	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String password;
	@SuppressWarnings("rawtypes")
	private Set<?> orders = new HashSet(0);
	private Contactinfo contactinfo;
	
	// Constructors
	
	/** default constructor */
	public User() {
	}
	
	/** full constructor */
	public User(String userid,String password,Set<?> orders,Contactinfo contactinfo) {
		this.userid = userid;
		this.password = password;
		this.orders = orders;
		this.contactinfo = contactinfo;
	}

	// Property accessors
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<?> getOrders() {
		return orders;
	}
	public void setOrders(Set<?> orders) {
		this.orders = orders;
	}
	public Contactinfo getContactinfo() {
		return contactinfo;
	}
	public void setContactinfo(Contactinfo contactinfo) {
		this.contactinfo = contactinfo;
	}
}
