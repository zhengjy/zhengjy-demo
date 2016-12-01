package com.zhengjy.test.thread.syn_cache高效并发缓存;

import java.util.Map;
import java.util.concurrent.*;

/**只有一个缺陷，仍然存在两个线程计算出相同值的漏洞。
 * 	由于compute方法中的if代码块仍然是非原子的“先检查在执行”操作，因此两个线程仍有可能在同一时间内调用compute来计算相同的值，
 * 计即二者都没有在缓存中找到期望的值，因此都开始计算。
 * <br/>
 * Author ：zhengjy <br/>
 * Create Time：2016年10月10日 上午8:44:38 <br/>
 */
public class Memoizer3<A,V> implements Computable<A, V> {
private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> c;
	
	public Memoizer3(Computable<A, V> c){
		this.c = c;
	}
	
	@Override
	public V compute(final A arg) {
		Future<V> future = cache.get(arg);
		if(future==null){
			Callable<V> call = new Callable<V>() {
				public V call(){
					return c.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(call);
			future = ft;
			cache.put(arg, ft);
			ft.run();
		}
		
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			return null;
		}
	}
	
}
