package com.briup.bean;

import java.util.HashSet;
import java.util.Set;
//种类  分三类：计算机   英语  自然科学 三类
public class Category implements java.io.Serializable{
	
	//Fields
	
	private Integer categoryid;
	private String name;
	private String description;
	private Set products = new HashSet();
	
	//Constructors
	
	/** default constructor*/
	public Category() {
	}
	
	/**  minimal constructor */
	public Category(String name) {
		this.name = name;
	}
	
	
	/** full constructor*/
	public Category(String name,String description,Set products) {
		this.name = name;
		this.description = description;
		this.products = products;
	}

	//Property accessors
	
	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
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

	public Set getProducts() {
		return products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}
}
