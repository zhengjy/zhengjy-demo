package com.zhengjy.test.concurrent.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
	private BlockingQueue<String>  blockingQueue;
	
	
	public Producer(BlockingQueue<String> blockingQueue) {
		super();
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put("1");
			Thread.sleep(1000);
			blockingQueue.put("2");
			Thread.sleep(1000);
			blockingQueue.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
