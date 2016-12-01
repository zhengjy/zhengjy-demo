package com.zhengjy.test.concurrent.callable;

import java.util.concurrent.*;

/**
 * ExecutorCompletionservice批量提交任务，可以按任务放入的顺序，先入先出
 * Author ：zhengjy <br/>
 * Create Time：2016年10月14日 下午4:55:10 <br/>
 */
public class Callables2 {
	public static void main(String[] args) throws Exception, ExecutionException {
		ExecutorService es =  Executors.newFixedThreadPool(5);
		ExecutorCompletionService<String> completion = new ExecutorCompletionService<String>(es);
		for(int i=0;i<5;i++){
			completion.submit(new Task(i));
		}
		
		for(int i=0;i<5;i++){
			System.out.println(completion.take().get());
		}
	}
	
}

class Task implements Callable<String>{
	private int i;
	public  Task(int i){
		this.i = i;
	}
	
	@Override
	public String call() throws Exception {
		return Thread.currentThread().getName() +"--"+i;
	}
	
}
