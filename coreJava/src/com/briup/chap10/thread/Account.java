package com.briup.chap10.thread;

public class Account {
	private double balance;
	
	public Account(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	synchronized public void withDraw(double amount) {
		if(amount > balance) {
			try {
				//wait()将线程放入等待池并且释放锁
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance -= amount;
		System.out.println(Thread.currentThread().getName()+
				"\tcustome is:"+amount+"\tbalance is:"+balance);
	}
		 
	 synchronized public void deposit(double amount) {
		balance += amount;
		//notify()通知等待池中的一个线程可以被调入锁池，
		//该线程一旦获得锁就可以执行相应的操作
		//notify();
		notifyAll();
		System.out.println(Thread.currentThread().getName()+
				"\tearn is:"+amount+"\tbalance is:"+balance);
	 
	}
}