package com.briup.chap10;

public class Counter extends Thread{
	private Storage storage;

	public Counter(Storage storage) {
		//super();
		this.storage = storage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int x=(int)(Math.random()*10);
			storage.deposit(x);
			try {
				 sleep(500);
			} catch (InterruptedException e) {
				// TODO uto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
