package com.zhengjy.rpc.core;


import java.util.concurrent.*;

/**
 * rcp线程池封装
 * Created by Jiyang.Zheng on 2019/1/11 13:32.
 */
public class RpcThreadPool {

    public static Executor getExecutor(int threads,int queues){
        String name ="RpcThreadPool";
        return new ThreadPoolExecutor(threads,threads,0,TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>()
                        : (queues < 0 ? new LinkedBlockingQueue<>()
                        : new LinkedBlockingQueue<>()),
                new NamedThreadFactory(name, true), new AbortPolicyWithReport(name));
    }
}
