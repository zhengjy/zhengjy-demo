package com.zhengjy.test.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 	有界缓存：可以使用两个Condition，分别未notFull和notEmpty，用于表示“非满”和“非空”两个条件谓词。当缓存为空时，take将阻塞并等待notEmpty，此时put向notEmpty发送信号，
 * 可以接触任何在take中阻塞的线程。
 * Author ：zhengjy <br/>
 * Create Time：2016年10月26日 下午3:06:13 <br/>
 */
public class ConditionBoundedBuffer<T> {
	protected Lock  lock  = new ReentrantLock();
	//条件谓词：notFull(count <items.length)
	Condition notFull = lock.newCondition();
	//条件谓词: notEmpty(count>0)
	Condition notEmpty =lock.newCondition();
	
	private final T[] items = (T[]) new Object[1024];
	private int tail,head,count;
	
	//阻塞并直到：notFull
	public void put(T x) throws InterruptedException{
		lock.lock();
		try{
			while(count == items.length){
				notFull.await();
			}
			items[tail]=x;
			if(++tail == items.length){
				tail =0;
			}
			++count;
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	
	//阻塞并直到：notEmpty
	public T take() throws InterruptedException{
		lock.lock();
		try{
			while(count ==0){
				notEmpty.await();
			}
			T x = items[head];
			if(++head ==items.length){
				head =0;
			}
			--count;
			notFull.signal();
			return x;
		}finally{
			lock.unlock();
		}
	}
}











