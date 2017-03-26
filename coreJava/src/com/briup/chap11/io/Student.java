package com.briup.chap11.io;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = -3939938985211547864L;
		
		private int id;
		private String name;
		private int age;
		private boolean gender;
		
		public Student(int id, String name, int age, boolean gender) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
		}
		
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
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public boolean isGender() {
			return gender;
		}
		public void setGender(boolean gender) {
			this.gender = gender;
		}
		
}
