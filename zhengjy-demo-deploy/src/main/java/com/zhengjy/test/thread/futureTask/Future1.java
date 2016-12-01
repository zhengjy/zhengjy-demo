package com.zhengjy.test.thread.futureTask;

import java.util.concurrent.*;


public class Future1 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task t = new Task();
		Future<Integer> result = executor.submit(t);
		executor.shutdown();
		try {
			//主函数执行方法
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程在执行任务");
		
		try {
			System.out.println("task运行结果"+result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
		
	}
}

/**
 * 异步处理方法
 
 */
class Task implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("子进程在进行计算");
		Thread.sleep(10000);
		int sum =0;
		for(int i=0;i<100;i++){
			sum +=i;
		}
		return sum;
	}


	
	
}