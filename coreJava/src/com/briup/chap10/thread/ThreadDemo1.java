package com.briup.chap10.thread;

class Demo1 implements Runnable{
	private String name;
	
	public Demo1(){}
	public Demo1(String name){
		this.name=name;
	}
	
	synchronized public void run(){ 
		for(int x=1; x<=100; x++){
			if(x%10==0&&x!=100) {
					System.out.println(name+"已经跑到了"+x+"米");
			}
			if (x==100) {
				System.out.println(name+"已经到达终点。。。");
			}
		}
	}
}

public class  ThreadDemo1{
	public static void main(String[] args) {	
		Demo1 tuzi = new Demo1("兔子");
		Demo1 wugui = new Demo1("乌龟");
		Thread t1 = new Thread(tuzi);
		Thread t2 = new Thread(wugui);
		t1.start();
		t2.start();
	}
}
