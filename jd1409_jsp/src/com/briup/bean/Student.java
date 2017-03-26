package com.briup.bean;

public class Student {
	private long id;
	private String name;
	private int age;
	private Address address;
	
	public Student() {
	}
	public Student(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getScore(){
		
		return 100;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
