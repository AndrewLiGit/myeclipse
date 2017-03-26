package com.briup.chap10.thread;

public class Boy extends Thread{

	private Account account;
	private String name;
	public Boy(Account account,String name) {
		this.account = account;
		setName(name);
	}
	@Override
	public void run() {
		while (true) {
			double am = Math.random()*10000;
			account.deposit(am);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

