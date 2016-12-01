package com.zhengjy.test.thread.lock;
/**
 * 顺序死锁：
 * LeftRightDeadLock存在死锁风险。
 * left,right这两个方法分别获取left锁和right锁。如果一个线程调用left,另一个线程调用right，这两个线程是交错执行，他们会发生死锁.
 * 
 * A线程： 锁住 left,尝试锁住right
 * B线程： 锁住 right,尝试锁住left <br/>
 * 
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年9月24日 上午10:02:27 <br/>
 */
public class LeftRightDeadLock {
	private final static Object LEFT = new Object();
	private final static Object RIGHT = new Object();
	
	private void left(){
		synchronized(LEFT){
			synchronized(RIGHT){
				System.out.println("");
			}
		}
	}
	
	
	private void right(){
		synchronized(RIGHT){
			synchronized(LEFT){
				
			}
		}
	}
}
