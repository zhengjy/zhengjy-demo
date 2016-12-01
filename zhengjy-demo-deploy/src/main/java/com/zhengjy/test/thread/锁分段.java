package com.zhengjy.test.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 锁分段：
 * HashTable都是竞争同一把锁，假如容器里有多把锁，每一把锁用于锁容器其中的一部分数据，那么当多线程访问不同数据段的数据时，线程间就不会存在锁竞争，
	从而可以有效的提高并发访问率，这就是ConcurrentHashMap使用的锁分段技术，首先将数据一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问
	其中一段数据时，那么其他段的数据也能被其他线程访问。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月28日 下午3:52:11 <br/>
 */
public class 锁分段 {
    public static void main(String[] args) {
    	Lock<String,String> lock = new Lock<String,String>();
    	
    	
    	
	}
}

class Lock<K,V> {
	private final int LOCK_COUNT = 16;
    private final Map<K,V> map;
    private final Object[] locks;

    public Lock() {
        this.map = new HashMap<K,V>();
        locks = new Object[LOCK_COUNT];
        for (int i=0;i<LOCK_COUNT;i++){
            locks[i] = new Object();
        }
    }
    
    private int keyHashCode(K k){
        return Math.abs(k.hashCode() % LOCK_COUNT);
    }
    
    public V get(K k){
        int keyHashCode = keyHashCode(k);
        synchronized (locks[keyHashCode % LOCK_COUNT]){
            return map.get(k);
        }
    }
    
}
