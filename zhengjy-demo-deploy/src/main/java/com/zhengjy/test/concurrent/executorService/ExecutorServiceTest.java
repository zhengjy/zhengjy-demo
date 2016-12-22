package com.zhengjy.test.concurrent.executorService;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

//http://blog.csdn.net/defonds/article/details/44021605/
public class ExecutorServiceTest {
	ExecutorService executor =Executors.newSingleThreadExecutor();
	
	//execute(Runnable) 方法要求一个Runnable对象，然后对它进行异步执行。
	@Test
	public void test(){
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("synchronized task");
			}
		});
		executor.shutdown();
	}
	
	//submit(Runnable) 方法也要求一个Runnable实现类，但是他返回一个Fature对象，
	//这个fature	对象可以用来检测Runnbale是否已经执行完毕。
	@Test
	public void test2() throws Exception, ExecutionException{
		Future future =  executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("synchronized task");
			}
		});
		
		System.out.println(future.get());
		executor.shutdown();
	}
	
	/*
	 * submit(Callable) 方法类似于 submit(Runnable) 方法，除了它所要求的参数类型之外。Callable 实例除了它的 call() 方法能够返回一个结果之外和一个 Runnable 很相像。Runnable.run() 不能够返回一个结果。
		Callable 的结果可以通过 submit(Callable) 方法返回的 Future 对象进行获取。
	 */
	@Test
	public void test3(){
		Future<String> futrue = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("synchronized task");
				return "xxxx";
			}
		});
		try {
			
			System.out.println(futrue.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * invokeAll() 方法将调用你在集合中传给 ExecutorService 的所有 Callable 对象。invokeAll() 返回一系列的 Future 对象，通过它们你可以获取每个 Callable 的执行结果。
记住，一个任务可能会由于一个异常而结束，因此它可能没有 "成功"。无法通过一个 Future 对象来告知我们是两种结束中的哪一种。
	 */
	@Test
	public void test4() throws Exception{
		Set<Callable<String>> callables = new HashSet<Callable<String>>();  
		  
		callables.add(new Callable<String>() {  
		    public String call() throws Exception {  
		        return "Task 1";  
		    }  
		});  
		callables.add(new Callable<String>() {  
		    public String call() throws Exception {  
		        return "Task 2";  
		    }  
		});  
		callables.add(new Callable<String>() {   
		    public String call() throws Exception {  
		        return "Task 3";  
		    }  
		});  
		  
		List<Future<String>> futures = executor.invokeAll(callables);  
//		executor.invokeAll(tasks, timeout, unit);
		for(Future<String> future : futures){  
		    System.out.println("future.get = " + future.get());  
		}  
		executor.shutdown();  
	}
}
