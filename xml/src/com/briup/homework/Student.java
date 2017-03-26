package com.briup.homework;

public class Student {
	private long id;
	private String name;
	private int age;
	private String city;
	
	public Student(){}
	public Student(long id, String name, int age, String city) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", city=" + city + "]";
	}
	
	public String toString(int i) {
		if(i==1)
			return name;
		else if(i==2)
			return age+"";
		return city;
	}

}
