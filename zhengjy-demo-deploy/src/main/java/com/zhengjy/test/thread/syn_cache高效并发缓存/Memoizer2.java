package com.zhengjy.test.thread.syn_cache高效并发缓存;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用ConcurrentHashMap代替HashMap。
 * 	当两个线程同时调用compute时存在一个漏洞，肯呢过会导致计算得到相同的值。
 * 	如果某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在执行，那么很可能会重复这个计算。
 * 
 */
public class Memoizer2<A,V> implements Computable<A, V> {
	
	private final Map<A,V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> c;
	
	public Memoizer2(Computable<A, V> c){
		this.c = c;
	}
	
	@Override
	public synchronized V compute(A arg){
		V result=cache.get(arg);
		if(result==null){
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}

}
