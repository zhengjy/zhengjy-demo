package com.zhengjy.test.concurrent.blockingQueue.t2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
		Producer producer = new Producer(queue);
		Consumer c = new Consumer(queue);
		new Thread(producer).start();
		new Thread(c).start();
		Thread.sleep(4000);
	}
}
