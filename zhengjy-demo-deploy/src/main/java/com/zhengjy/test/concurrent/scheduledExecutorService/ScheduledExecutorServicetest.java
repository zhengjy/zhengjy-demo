package com.zhengjy.test.concurrent.scheduledExecutorService;

import java.util.concurrent.*;

public class ScheduledExecutorServicetest {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService se = Executors.newScheduledThreadPool(3);
		for(int i=0;i<100;i++){
			Task t =new Task("x"+i);
			Thread.sleep(1000);
			ScheduledFuture str =se.schedule(t, 1000, TimeUnit.MILLISECONDS);
			System.out.println(str.get());
			
		}
		se.shutdown();
	}
}

class Task implements Callable{
	private String str;
	
	public Task(String str){
		this.str = str;
	}
	@Override
	public String call() throws Exception {
		return str;
	}
	
}