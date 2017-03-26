package com.briup.chap10.thread;

public class TicketTest implements Runnable {
	private TicketOffice office;
	private String name;
	
	public TicketTest(TicketOffice office,String name) {
		this.office = office;
		this.name = name;
	}
	@Override
	public void run() {
		while (true) {
			synchronized (office) {
				if(office.getNum() > 0) {
					System.out.println(name+" ticket number is:"+office.getNum());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					office.sales();
					System.out.println("after "+name+" sales ticket number is:"+office.getNum());
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		TicketOffice office = new TicketOffice(20);
		TicketTest test1 = new TicketTest(office, "�Ϻ���վ");
		TicketTest test2 = new TicketTest(office, "�Ͼ���վ");
		TicketTest test3 = new TicketTest(office, "̫ԭ��վ");
		
		Thread t1 = new Thread(test1);
		Thread t2 = new Thread(test2);
		Thread t3 = new Thread(test3);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
