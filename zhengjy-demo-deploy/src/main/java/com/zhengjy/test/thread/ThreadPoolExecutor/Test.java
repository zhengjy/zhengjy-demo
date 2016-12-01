package com.zhengjy.test.thread.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public class Test {
	public static void main(String[] args) {
		TimingThreadPool pool = new TimingThreadPool(2, 2, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque(10));
		for(int i=0;i<3;i++){
			pool.execute(new MyThread());
		}
		pool.shutdown();
	}
	
	static class MyThread extends Thread{
		@Override
		public void run() {
			System.out.println("MyThread "+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			super.run();
		}
	}
}
