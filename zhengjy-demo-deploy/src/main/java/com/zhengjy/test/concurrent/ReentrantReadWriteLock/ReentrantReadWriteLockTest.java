package com.zhengjy.test.concurrent.ReentrantReadWriteLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock可重入的读写锁，允许多个读线程活动ReadLock，但只允许一个写线程活动WriteLock。
 * ReentrantReadWriteLock是一个读写锁，它提供了一个读锁和一个写锁，读锁用于只读操作，而写锁用于写入操作，读操作可以并行执行，而写操作则是互斥的。
 * 读锁和写锁的分离在一些写少读多的应用中可以带来性能上的提升。例如：一个HashMap在构造之后很少修改，却经常进行查询操作，这样查询操作就可以并发进行从而
 * 提高性能。
 * 读锁和写锁之间满足如下的约束：
 * 	1)当任一线程持有读锁或写锁时，其他线程不能获得写锁。
 * 	2)当任一线程持有写锁时，其他线程不能获取读锁，
 * 	3)多个线程可以同时持有读锁。
 * <br/>
 * Author ：zhengjy <br/>
 * Create Time：2016年10月10日 下午5:07:48 <br/>
 */
public class ReentrantReadWriteLockTest {
	public static void main(String[] args) {
		ReentrantReadWriteLock reentrant = new ReentrantReadWriteLock();
		final Lock rlock = reentrant.readLock();
		final Lock wlock = reentrant.writeLock();
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date())) +" now to get rlock");
						rlock.lock();
						
						try {
							Thread.currentThread().sleep(10000);  
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()))+" now to unlock rlock");
						rlock.unlock();
						
						latch.countDown();
					}
				}
		).start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()))+" now to get wlock");
				wlock.lock();
				
				System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()))+" now to unlock wlock ");
				wlock.unlock();
				
				latch.countDown();
			}
		}).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date())) + " finished");  
	}
}
