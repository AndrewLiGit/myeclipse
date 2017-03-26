package com.briup.chap10;

public class PrinterTest {
	public static void main(String args[]){
		Storage storage= new Storage(0);
		new Counter(storage).start();
		new Printer(storage);
	}
}
