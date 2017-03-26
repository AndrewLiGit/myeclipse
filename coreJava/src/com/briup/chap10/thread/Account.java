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
				//wait()���̷߳���ȴ��ز����ͷ���
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
		//notify()֪ͨ�ȴ����е�һ���߳̿��Ա��������أ�
		//���߳�һ��������Ϳ���ִ����Ӧ�Ĳ���
		//notify();
		notifyAll();
		System.out.println(Thread.currentThread().getName()+
				"\tearn is:"+amount+"\tbalance is:"+balance);
	 
	}
}