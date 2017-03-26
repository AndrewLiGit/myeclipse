package com.lzjtu.bookstore.model;

public class SmallCategory {

	private int id;
	private String name;
	private int bigTypeId;
	private String remarks;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBigTypeId() {
		return bigTypeId;
	}
	
	public void setBigTypeId(int bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
