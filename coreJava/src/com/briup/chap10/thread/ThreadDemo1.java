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
					System.out.println(name+"�Ѿ��ܵ���"+x+"��");
			}
			if (x==100) {
				System.out.println(name+"�Ѿ������յ㡣����");
			}
		}
	}
}

public class  ThreadDemo1{
	public static void main(String[] args) {	
		Demo1 tuzi = new Demo1("����");
		Demo1 wugui = new Demo1("�ڹ�");
		Thread t1 = new Thread(tuzi);
		Thread t2 = new Thread(wugui);
		t1.start();
		t2.start();
	}
}
