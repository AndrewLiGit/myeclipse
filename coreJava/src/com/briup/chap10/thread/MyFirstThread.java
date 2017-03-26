package com.briup.chap10.thread;

public class MyFirstThread extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(currentThread().getName()
					+" running "+i+" isAlive:"+currentThread().isAlive()
					+" interrupted:"+currentThread().isInterrupted()
					+" proiority:"+currentThread().getPriority());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//主线程  用户线程   守护线程
		System.out.println(currentThread().getName()
					+" running ");
		MyFirstThread mft = new MyFirstThread();
		//同一个线程不允许启动多次 抛出illageThreadStateException
		mft.setPriority(10);
		mft.start();
		//mft.stop();
		//mft.start();
		//直接调用run方法相当于调用普通方法没有thread效果
		//mft.run();
		MyFirstThread mft2 = new MyFirstThread();
		mft2.setPriority(1);
		mft2.start();
		try {
		//join():抢占线程  谁等待谁去调用其他线程来执行join()方法
			mft2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RunnableTest rt = new RunnableTest();
		Thread t = new Thread(rt);
		//setDaemon()守护线程  一个线程的结束引起当前线程的结束
		t.setDaemon(true);
		t.start();
		System.out.println("main over");
	}
}
class RunnableTest implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread()
					.getName()+" running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}