package com.zhengjy.test.concurrent.callable;

import java.util.concurrent.*;

public class CallableTest {
	public static void main(String[] args) throws Exception, ExecutionException {
		Callable<String> call2= new Callable<String>() {

			@Override
			public String call() throws Exception {
				return null;
			}
		};
		
		
		final ExecutorService exec = Executors.newFixedThreadPool(1);  
        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
    				return null;
            }
        };  
        try {
            Future<String> future = exec.submit(call);  
            String obj = future.get(1000 *3, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒  
        } catch (TimeoutException ex) {
        }finally {
        	// 关闭线程池  
        	exec.shutdown();  
		}
		
	}
}
