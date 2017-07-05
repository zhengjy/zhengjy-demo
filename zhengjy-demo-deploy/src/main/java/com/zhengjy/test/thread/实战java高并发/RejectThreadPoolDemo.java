package com.zhengjy.test.thread.实战java高并发;

import java.util.concurrent.*;

/**
 * 线程池-拒绝策略
 * Created by zhengjy on 2017/7/1.
 */
public class RejectThreadPoolDemo {
    public  static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thread ID:"+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * MyTask执行需要花费100毫秒，等待队列只要10个，会导致大量的任务被直接丢弃
         * @param args
         * @throws InterruptedException
         */
        public static void main(String[] args) throws InterruptedException {
            MyTask task = new MyTask();
            ExecutorService es = new ThreadPoolExecutor(5, 5, 0L,
                    TimeUnit.MICROSECONDS,
                    new LinkedBlockingDeque<Runnable>(10),//拥有10个容量的等待队列
                    Executors.defaultThreadFactory(),
                    //自定义拒绝策略
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            System.out.println(r.toString()+" is discard");
                        }
                    });
            for (int i=0;i<Integer.MAX_VALUE;i++){
                es.submit(task);
                Thread.sleep(10);
            }
            es.shutdown();
        }
    }
}
