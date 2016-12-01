package com.zhengjy.test.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Executor1 {
	public static void main(String[] args) {
		Executor e = Executors.newCachedThreadPool();
		final Callable<String> c=new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "xxxxx";
			}
		};
		e.execute(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(c.call());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}));
		
	}
	
	
	
	
}
