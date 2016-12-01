package com.zhengjy.test.concurrent.callable;

import java.util.concurrent.*;

/**
 * 线程：饥饿死锁
 * Author ：zhengjy <br/>
 * Create Time：2016年9月14日 上午10:35:31 <br/>
 */
public class ThreadDeadlock {
	/**返回一个线程池（这个线程池只有一个线程），这个线程池可以在线程死后（或发生异常时）重写启动一个线程来替代原来的线程继续执行下去*/
	//	ExecutorService  executor = Executors.newSingleThreadExecutor();
	/**创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收线程，则新建线程*/
	ExecutorService executor = Executors.newCachedThreadPool();
	/**创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。*/
//	Executor executor = Executors.newFixedThreadPool(10);
	/**创建一个定长线程池，支持定时及周期性任务执行*/
//	Executor executor = Executors.newScheduledThreadPool(10);
	
	
	/**
	 * 
	 * Author ：zhengjy <br/>
	 * Create Time：2016年9月14日 上午10:31:45 <br/>
	 * 产生死锁的分析：
	 * 	RenderPageTask任务中有两个子任务分别是“页头”和“页尾”。当提交RenderPageTask任务时，实际上是向线程池中添加了三个任务，但是
	 *  由于线程池是单一线程，同时只会执行一个任务，2个子任务就会阻塞在线程池中。而RanderPageTask任务由于得不得返回，也会一直堵塞，
	 *  不会释放线程资源让子线程执行。这样就会导致了线程的饥饿死锁。
	 * 
	 */
	class RenderPageTask implements Callable<String>{
		@Override
		public String call() throws Exception {
			Future<String> header,footer;
			header = executor.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					System.out.println("加载页脚");
					Thread.sleep(3*1000);
					return "页头";
				}
			});
			
			footer = executor.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					System.out.println("加载页脚");  
                    Thread.sleep(3*1000);  
                    return "页脚";  
				}
			});
			System.out.println("渲染页面主体");  
			 return header.get() +"-内容-"+ footer.get();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadDeadlock td = new ThreadDeadlock();
		Future<String> str = td.executor.submit(td.new RenderPageTask());
		String ret = str.get();
		System.out.println(ret);
		
	}
}
