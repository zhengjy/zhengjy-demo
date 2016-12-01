package com.zhengjy.test.thread.futureTask;

import java.util.concurrent.*;
import java.util.concurrent.FutureTask;

public class FutureTask2 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task t = new Task();
		FutureTask<Integer> ft = new FutureTask<Integer>(t);
		executor.submit(ft);
		executor.shutdown();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行完毕");
		
		try {
			System.out.println("task运行结果"+ft.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
	}
}

class Task3 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程正在计算");
		Thread.sleep(1000);
		int sum=0;
		for (int i = 0; i < 100; i++) {
			sum +=i;
		}
		
		return sum;
	}
	
}
