package com.zhengjy.test.concurrent.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者生产了余库存，消费者才能调用。不然会阻塞。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午6:28:02 <br/>
 */
public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String>  blockingQueue = new ArrayBlockingQueue<String>(1024);
		
		Producer producer = new Producer(blockingQueue);
		
		Consumer consumer= new Consumer(blockingQueue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		Thread.sleep(4000);
	}
}
