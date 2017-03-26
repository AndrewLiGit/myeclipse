package com.briup.chap10;

public class Storage {
	private static int a;
	
	public Storage() {
	}

	public Storage(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
	 synchronized public void withDraw(){
		System.out.println(Thread.currentThread().getName()+" È¡³ö "+a);
	}
	synchronized public void deposit(int x){
		a=x;
		notify();
		System.out.println(Thread.currentThread().getName()+" ´æÈë "+a);
	}
	
}
