package com.lan.thread01;

public class MyThread extends Thread{

	private int count = 5;
//	@Override
//	public void run() {
//		count -- ;
//		System.out.println(this.currentThread().getName() + " count = "+count);
//	}
//	t2 count = 3
//	t4 count = 1
//	t3 count = 2
//	t1 count = 3
//	t5 count = 0
	
	@Override
	public synchronized void run() {
		count -- ;
		System.out.println(this.currentThread().getName() + " count = "+count);
	}
	/*	    t1 count = 4
			t4 count = 3
			t3 count = 2
			t2 count = 1
			t5 count = 0*/
	
	public static void main(String[] args) {
		
		/**
		 * 分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排队是按照CPU分配的先后顺序而定的）
		 * 		一个线程想要执行synchronized修饰的方法里的代码：
		 * 		1 尝试获得锁
		 * 		2如果拿到锁，执行synchronized代码体内容：拿不到锁，这个线程就会不断地尝试获得这把锁，直到拿到为止，
		 * 		而且是多个线程同时去竞争这把锁。（也就是会有锁竞争问题）
		 */
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread, "t1");
		Thread t2 = new Thread(myThread, "t2");
		Thread t3 = new Thread(myThread, "t3");
		Thread t4 = new Thread(myThread, "t4");
		Thread t5 = new Thread(myThread, "t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
