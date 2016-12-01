package com.zhengjy.test.thread;

import java.util.concurrent.atomic.AtomicLong;

public class MyThread extends Thread{
	public static void main(String[] args) {
		MyThread m1 = new MyThread();
		MyThread m2 = new MyThread();
		MyThread m3 = new MyThread();
		m1.start();
		m2.start();
		m3.start();
		
	}
}

/**
 * 
 * 项目名称：java性能优化
 * 类名：com.zhengjy.test.thread.MyThread.java
 * 类描述：
 * 创建人 ：zhengjy
 * 创建时间：2016年9月1日 上午8:36:41
 * 
 * 多个线程在没有同步的情况下同时对一个计算器执行递减操作时发生的情况。
 * 如果计算器的初始值是10，那么在某些情况下，每个线程读取的都是10，接着递减操作，并且计算的值都设为9，显然，这并不是我们希望看到的情况，如果以一次递减操作丢失了，命中计算器的值
 * 将会偏差1.
 */

class MyThread2 extends Thread{
	private static int ticket=10;
	private final AtomicLong a = new AtomicLong(10);
	public  void run(){
		for(int i=0;i<20;i++){
			if(ticket>0){
				System.out.println(Thread.currentThread().getName()+"卖票：ticket"+this.ticket--);
			}
		}
	}
}