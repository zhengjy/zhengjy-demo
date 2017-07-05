package com.zhengjy.test.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

	public static void main(String[] args) {
		final ReentrantLock lock = new ReentrantLock(true);
		lock.lock();
		lock.lock();



		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				lock.unlock();
			}
		}).start();

	}



}
