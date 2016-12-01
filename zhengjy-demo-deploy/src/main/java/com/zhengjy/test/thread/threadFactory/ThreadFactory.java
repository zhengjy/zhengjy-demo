package com.zhengjy.test.thread.threadFactory;

public interface ThreadFactory {
	Thread  newThread(Runnable r);
}
