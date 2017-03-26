package com.briup.chap10.thread;

class Demo extends Thread{
	private String name;
	public Demo(String name) {
		//super(name);
		this.name=name;
	}
	public void run(){
		for(int x=0;x<10;x++){
			for(int y=-9999999;y<9999999;y--){}
			System.out.println(name+"...x="+x+"...name="+Thread.currentThread().getName());
		}
	}
}
public class ThreadDemo {
	public static void main(String args[]){
		Demo d1 = new Demo("Íú²Æ");
		Demo d2 = new Demo("Ð¡Ç¿");
		
		d1.start();
		d2.start();
		
		System.out.println("over..."+Thread.currentThread().getName());
		
	}
}
