package com.zhengjy.test.concurrent.Atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong线程安全的， 原子性，支持无阻塞无锁定
 * 
 * 10个线程，每个线程运行了10000次，理论上应该有100000次的增加，使用了普通long是非线程安全的，而使用Atomic是线程安全的。
 * @author zhengjy
 *
 */
public class AtomicLongTest {
	public static void main(String[] args) {
		 final int loopcount = 10000;
		 int threadNum =10;
		
		 final NotSafe ns = new NotSafe();
		 final Safe s = new Safe();
		 final CountDownLatch latch = new CountDownLatch(threadNum);
		 for(int i=0;i<threadNum;i++){
			 final int index = i;
			 new Thread(
					 new Runnable() {
						 @Override
						 public void run() {
							 for(int i=0;i<loopcount;i++){
								 ns.inc();
								 s.inc();
							 }
							 System.out.println("finished："+index);
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
		 System.out.println("NotSafe  get() "+ns.get());
		 System.out.println("Safe  get() "+s.get());
		 System.out.println("end");
	}
}

class NotSafe{
	private int count;
	public void inc(){
		count++;
	}
	public int get(){
		return count;
	}
}

class Safe{
	private AtomicLong atomic = new AtomicLong(0);
	public void inc(){
		atomic.incrementAndGet();
	}
	public int get(){
		return (int) atomic.get();
	}
	
}