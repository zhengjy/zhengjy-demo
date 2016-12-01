package com.zhengjy.test.concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 接收多个线程提交
 * Author：zhengjy
 */
public class Callables {
	public static <V> void main(String[] args) {
		List<Callable<V>> callable  = new ArrayList<Callable<V>>();
		callable.add(new Callable<V>() {
			@Override
			public V call() throws Exception {
				return null;
			}
		});
		
		callable.add(new Callable<V>() {
			@Override
			public V call() throws Exception {
				return null;
			}
		});
		Executor executor = Executors.newCachedThreadPool();
		submitTask(executor,callable);
		
		
	}
	
	/**
	 * 在线程池运行任务并阻塞直到所有线程都完成，Callables的顺序和返回值的顺序一样
	 * @param executor
	 * @param callables
	 * @return
	 */
	public static <V> List<V> submitTask(Executor executor,List<Callable<V>> callables){
		
		final CountDownLatch latch = new CountDownLatch(callables.size());//多少个线程
		final ConcurrentMap<Integer, V> concurrent = new ConcurrentHashMap<Integer, V>();
		int i = 0;
		for(final Callable<V> r:callables){
			final int j =i;
			i++;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						V v = r.call();
						concurrent.put(j, v);
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						//线程数-1
						latch.countDown();
					}
				}
			});
			/*try {
				executor.invokeAll(callables);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		try {
			//等待所有线程完成，未完成一直阻塞，直到计时器的值为0
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<V> ret = new ArrayList<>(i);
		for(int k=0;k<i;k++){
			ret.add(concurrent.get(k));
		}
		return ret;
	}
}
