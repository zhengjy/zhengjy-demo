package com.zhengjy.test.concurrent.ReentrantReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁降级：
 * 	在一个线程中读锁不能升级为写锁，但是写锁可以降级未读锁。如果你已经有一个读锁，再去试图获取写锁，将无法获得，一直堵住。但是如果你有一个写锁，再去试图获取读锁没问题；
 * Author ：zhengjy <br/>
 * Create Time：2016年10月11日 上午8:13:15 <br/>
 */
public class ReentrantReadWriteLock2 {
	public static void main(String[] args) {
		ReentrantReadWriteLock reentrant = new ReentrantReadWriteLock();
		Lock rlock = reentrant.readLock();
		Lock wlock = reentrant.writeLock();
		
		System.out.println(" get wlock");
		wlock.lock();
		
		System.out.println(" get rlock");
		rlock.lock();
		
		System.out.println(" get wlock unlock");
		wlock.unlock();
		
		System.out.println(" get rlock unlock");
		rlock.unlock();
	}
}
