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
		//���߳�  �û��߳�   �ػ��߳�
		System.out.println(currentThread().getName()
					+" running ");
		MyFirstThread mft = new MyFirstThread();
		//ͬһ���̲߳������������ �׳�illageThreadStateException
		mft.setPriority(10);
		mft.start();
		//mft.stop();
		//mft.start();
		//ֱ�ӵ���run�����൱�ڵ�����ͨ����û��threadЧ��
		//mft.run();
		MyFirstThread mft2 = new MyFirstThread();
		mft2.setPriority(1);
		mft2.start();
		try {
		//join():��ռ�߳�  ˭�ȴ�˭ȥ���������߳���ִ��join()����
			mft2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RunnableTest rt = new RunnableTest();
		Thread t = new Thread(rt);
		//setDaemon()�ػ��߳�  һ���̵߳Ľ�������ǰ�̵߳Ľ���
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