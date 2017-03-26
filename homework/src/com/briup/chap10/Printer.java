package com.briup.chap10;

public class Printer implements Runnable {
	private Storage storage;

	public Printer(Storage storage) {
		// super();
		this.storage = storage;
		Thread p = new Thread(this);
		p.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
				storage.withDraw();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}

}
