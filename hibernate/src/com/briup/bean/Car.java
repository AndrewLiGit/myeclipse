package com.briup.bean;

//POJO类  javabean  实体类
public class Car {
	private long id;
	private String name;
	private double price;
	
	
	//一定要有一个无参构造器
	public Car() {
		
	}
	public Car(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Car(String name, double price) {
		this.name = name;
		this.price = price;
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "id="+id+" name="+name+" price="+price;
	}
	
}
