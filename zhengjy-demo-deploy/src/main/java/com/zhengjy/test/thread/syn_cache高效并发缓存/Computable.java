package com.zhengjy.test.thread.syn_cache高效并发缓存;

public interface Computable<A,V> {
	V compute(A arg) ;
}
