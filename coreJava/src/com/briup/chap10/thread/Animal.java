package com.briup.chap10.thread;
class Demo2{
	public static final Demo2 dd = new Demo2();
	}
public class Animal extends Thread{
	int v = 0;
	int s = 0;
	public void run() {
		while(true) {
			v =(int)(Math.random()*10);
			s += v;
			System.out.println(currentThread().getName()
					+" running v is:"+v+"  s is:"+s);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//yield();
			synchronized (Demo2.dd) {
				if(s >50) {
					System.out.println("Winner is:"+currentThread().getName());
					System.exit(0);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Animal dog = new Animal();
		Animal pig = new Animal();
		dog.setName("»¨Ã¨");
		pig.setName("°×Ã¨");
		dog.start();
		pig.start();
	}
}
