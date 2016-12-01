package com.zhengjy.test.concurrent.ReentrantReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁升级写锁一直挂住：
 * Author ：zhengjy <br/>
 * Create Time：2016年10月11日 上午8:18:58 <br/>
 */
public class ReentrantReadWriteLock3 {
	public static void main(String[] args) {
		ReentrantReadWriteLock reentrant = new ReentrantReadWriteLock();
		Lock rlock = reentrant.readLock();
		Lock wlock = reentrant.writeLock();
		
		System.out.println("get rlock lock");
		rlock.lock();
		System.out.println("get wlock lock");
		wlock.lock();
		System.out.println("get rlock unlock");
		rlock.unlock();
		System.out.println("get wlock unlock");
		wlock.unlock();
	}
}
