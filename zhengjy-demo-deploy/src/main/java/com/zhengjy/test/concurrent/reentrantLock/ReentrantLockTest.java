package com.zhengjy.test.concurrent.reentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 通过
 * lock()获取锁，拿不到就一直等待
 * trylock()，拿到就返回true,不然返回false
 * reentrantLock.tryLock(timeout, unit)时间限制的拿锁
 * unlock()释放锁
 * 
 *好处：
 *	1)更好的提升性能
 *	2)提供同一个Lock对象上不同的condition的信号通知
 *	3)还提供了lockinterruptibly这样支持响应中断的加锁过程，当你试图去加锁，但是当前锁被其他线程拿住,
 *然后你这个线程可以被中断
 * 
 * @author zhengjy
 *
 */
public class ReentrantLockTest {
	public static void main(String[] args) {
		final int loop = 10000;
		int threadNum = 10;
		final SafeReentrantLock reentrantLock= new SafeReentrantLock();
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for(int i=0;i<threadNum;i++){
			final int index =i;
			new Thread(
					new Runnable() {
						@Override
						public void run() {
							for(int i=0;i<loop;i++){
								reentrantLock.inc();
							}
							System.out.println("finished "+index);
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
		System.out.println("finished end");
		System.out.println("SafeReentrantLock："+reentrantLock.get());
	}
}

class SafeReentrantLock{
	long count=0;
	ReentrantLock reentrantLock = new ReentrantLock();
	
	public void inc(){
		reentrantLock.lock();
		
		try{
			count++;
		}finally{
			reentrantLock.unlock();
		}
	}
	
	public long get(){
		return count;
	}
	
}