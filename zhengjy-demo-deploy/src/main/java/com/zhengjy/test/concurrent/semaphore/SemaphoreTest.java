package com.zhengjy.test.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量：可以控制某个资源被访问的个数，通过acquire()获得一个许可,如果没有就等待而release()释放许可。比如在Windows下可以设置共享文件的最大客户端的访问个数。
 * 
 * Semaphore实现的功能就类似于厕所有5个坑，假如有10个人上厕所，同时只有5个人占坑，当占坑的5个人任何一个人离开，剩余5个人任意一个补上。
 * Author ：zhengjy <br/>
 * Create Time：2016年10月12日 上午8:23:35 <br/>
 */
public class  SemaphoreTest {
	public  static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		//只能有5个线程同时访问
		final Semaphore semaphore = new Semaphore(5);
		for(int i=0;i<50;i++){
			final int NO = i;
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						//获得访问许可
						semaphore.acquire();
						System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        //访问完释放
                        semaphore.release();
                        //availablePermits()指的是当前信号灯库剩余多少可以被使用
                        System.out.println("-----------------" + semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			};
			executor.execute(run);
		}
		executor.shutdown();
	}
	
	
	public void deposit(int amount) {
		synchronized (this) {
		}
		}
}
