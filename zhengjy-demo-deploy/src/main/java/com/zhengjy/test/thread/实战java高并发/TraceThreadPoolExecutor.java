package com.zhengjy.test.thread.实战java高并发;

import java.util.concurrent.*;

/**
 * 扩展ThradPoolExecutor
 * Created by zhengjy on 2017/7/1.
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                   TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task,clinetTrace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,clinetTrace(),Thread.currentThread().getName()));
    }

    private Exception clinetTrace(){
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task,final Exception clientStack,String clientThreadName){
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                }catch (Exception e){
                    clientStack.printStackTrace();
                    throw e;
                }
            }
        };
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0L,TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i=0; i<5; i++){
            pools.execute(new DivTack(100,i));
        }
    }

    static class DivTack implements Runnable{
        int a,b;
        public DivTack(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            double re = a/b;
            System.out.println(re);
        }
    }
}
