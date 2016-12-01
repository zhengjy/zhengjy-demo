package com.zhengjy.test.thread.syn_cache高效并发缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用HashMap来保存之前计算的结果。
 * HashMap不是线程安全的，因此要确保链各个线程不会同时访问HashMap，Memoizer1对整个compute方法进行同步。这种方法确保线程安全性，但会带来一个明显的可伸缩性问题：
 * 	每次只有一个线程能够执行compute。如果另一个线程正在计算结果，那么其他调用compute的线程肯呢过被阻塞很长时间。
 * 
 * 创建人 ：zhengjy<br/>
 * 创建时间：2016年9月8日 上午8:13:26<br/>
 
 */
public class Memoizer1<A,V> implements Computable<A, V> {
	
	private final Map<A,V> cache = new HashMap<A, V>();
	
	private final Computable<A, V> c;
	
	public Memoizer1(Computable<A, V> c){
		this.c = c;
	}
	
	/**
	 * 
	 */
	public synchronized V compute(A arg){
		V result=cache.get(arg);
		if(result==null){
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
