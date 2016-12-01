package com.zhengjy.test.thread.ThreadPoolExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * ThreadPoolExecuter是可扩展的。
 *  在执行任务的线程中将调用beforeExecute和afterExecute等方法，在这些方法中可以添加日志、计时、监视或统计信息收集的功能。
 *  无论run是否正常返回都会调用afterExecute方法。
 *  terminated在线程池完成关闭操作时调用。
 * @author zhengjy
 *
 */
public class TimingThreadPool extends ThreadPoolExecutor{

	 public TimingThreadPool(int corePoolSize, int maximumPoolSize,long keepAliveTime, TimeUnit unit, BlockingQueue workQueue) {
			  super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	 }

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger log = Logger.getLogger(this.getClass().getName());
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();
	
	@Override
	protected void beforeExecute(Thread t,Runnable r){
		super.beforeExecute(t, r);
		System.out.println("beforeExecute "+r);
		startTime.set(System.currentTimeMillis());
	}
	@Override
	protected void afterExecute(Runnable r,Throwable t){
		long  endTime = System.currentTimeMillis();
		long taskTime = endTime - startTime.get();
		numTasks.incrementAndGet();
		totalTime.addAndGet(taskTime);
		System.out.println("afterExecute "+r);
		super.afterExecute(r, t);
	}
	@Override
	protected void terminated(){
		System.out.println("terminated avg time " + totalTime.get()  + " " + numTasks.get());
		super.terminated();
	}
}

















