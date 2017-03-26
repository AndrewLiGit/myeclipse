package com.briup.chap10.thread;

public class AccountTest {
	public static void main(String[] args) {
		Account account = new Account(0);
		new Boy(account, "tom").start();
		new Boy(account, "jack").start();
		new Gril(account,"lily");
	}
}
