package com.zhengjy.test.thread.executor.t2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor extends Thread{
	
	int index;
	public MyExecutor(int i){
		this.index=i;
	}
	
	@Override
	public void run() {
		System.out.println("["+this.index+"] start....");
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.println("["+this.index+"] end.");
	}
	
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(int i=0;i<10;i++){
			executor.execute(new MyExecutor(i));
		}
		executor.shutdown();
	}

}
