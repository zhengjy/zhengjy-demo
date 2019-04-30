package com.zhengjy.rpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 * Created by Jiyang.Zheng on 2019/1/11 13:12.
 */
public class NamedThreadFactory  implements ThreadFactory {

    private static final AtomicInteger threadNnmber = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    private final String prefix;

    private final boolean daemoThread;

    private final ThreadGroup threadGroup;

    public NamedThreadFactory(){
        this("rpcserver-threadpool-"+ threadNnmber.getAndIncrement());
    }

    public NamedThreadFactory(String prefix){
        this(prefix,false);
    }

    public NamedThreadFactory(String prefix,boolean daemo){
        this.prefix = prefix +"-thread-";
        this.daemoThread = daemo;
        SecurityManager s  = System.getSecurityManager();
        this.threadGroup = s== null ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }



    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup,r,name,0);
        ret.setDaemon(this.daemoThread);
        return ret;
    }

    public ThreadGroup getThreadGroup(){
        return threadGroup;
    }


}
