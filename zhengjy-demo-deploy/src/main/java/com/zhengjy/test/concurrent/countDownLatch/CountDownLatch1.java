package com.zhengjy.test.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch闭锁：可以用来在一个线程中等待多个线程完成任务的类。
 * 使用场景：某个主线程接到一个任务，起了N个子线程去完成，但是主线程需要等待这N个线程都完成
 * 了任务以后才开始执行某个操作。
 * <br>
 * @author zhengjy
 *
 */
public class CountDownLatch1 {
	public static void main(String[] args) {
		int count =10;
		final CountDownLatch latch = new CountDownLatch(count);
		for(int i=0;i<count;i++){
			final int  index =1;
			new Thread(
					new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							 System.out.println("thread " + index + " has finished..."); 
							latch.countDown();
						}
					}
			
			).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
		
	}
	
}
