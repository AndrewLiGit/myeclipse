package com.briup.chap11.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PiepedStreamDemo {
	
	public static void main(String[] args) {
	
		//管道流的构建
		PipedInputStream pis = new PipedInputStream();
		try {
			PipedOutputStream pos =new PipedOutputStream(pis);
			PiepedStreamDemo psd = new PiepedStreamDemo();
			OutThread ot = psd.new OutThread(pos);
			ot.start();
			InThread it1 = psd.new InThread(pis);
			InThread it2 = psd.new InThread(pis);
			it1.start();
			it2.start();
			//it1.join(); join()使用在管道中会抛出Broken错误
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public class OutThread extends Thread {
		private PipedOutputStream pos;
		
		public OutThread(PipedOutputStream pos) {
			this.pos = pos;
		}
		public void run() {
			for(int i=0;i<10;i++) {
				try {
					pos.write(i);
					System.out.println(getName()
							+" write(i):"+i);
					pos.flush();
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public class InThread extends Thread {
		private PipedInputStream pis;
		
		public InThread(PipedInputStream pis) {
			this.pis = pis;
		}
		public void run() {
			for(int i=0;i<5;i++) {
				//synchronized (pis) {
					try {
						int num = pis.read();
						System.out.println(getName()
								+" print i:"+num);
						
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				//}
			}
		}
	}
}
