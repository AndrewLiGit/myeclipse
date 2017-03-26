package com.briup.chap10.thread;

public class Gril implements Runnable{
	private Account account;
	private String name;
	
	public Gril(Account account,String name) {
		this.account = account;
		Thread t = new Thread(this);
		t.setName(name);
		t.start();
	}
	@Override
	public void run() {
		while (true) {
			double am = Math.random()*10000;
			account.withDraw(am);
			try {
				Thread.sleep((long)am);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
	}

}
