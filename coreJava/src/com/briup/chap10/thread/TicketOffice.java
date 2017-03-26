package com.briup.chap10.thread;

public class TicketOffice {
	private int num;
	
	public TicketOffice(int num) {
		this.num = num;
	}
	//synchronized
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	//synchronized
	public void sales() {
		if(num >0)
			--num;
	}
}

