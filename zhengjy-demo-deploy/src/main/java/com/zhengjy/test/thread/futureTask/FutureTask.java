package com.zhengjy.test.thread.futureTask;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTask {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			Callable<String> str = new Task2();
			MyFutureTask mf = new MyFutureTask(str);
			executor.submit(mf);
			
		}
	}
}

class MyFutureTask extends java.util.concurrent.FutureTask<String>{
	public MyFutureTask(Callable<String> callable){
		super(callable);
	}
	
	@Override
	protected void done(){
		try {
			System.out.println(this);
			System.out.println(get() +"线程执行完毕");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
}


class Task2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		Random random =  new Random();
		TimeUnit.SECONDS.sleep(random.nextInt(12));
		return Thread.currentThread().getName();
	}
	
}