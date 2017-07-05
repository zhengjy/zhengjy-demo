package com.zhengjy.test.thread.实战java高并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengjy on 2017/7/1.
 */
public class ExtThreadPool {
    public static class MyTask implements Runnable{
        public String name;
        public MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("正在执行：Thread ID"+Thread.currentThread().getId()+
            ",Task Name=" +this.name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("r="+r+",t="+t.getName()+"，准备执行："+ ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成："+((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for(int i=0;i<5;i++){
            MyTask task = new MyTask("task-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
        //取得可用的CPU数量
        System.out.println("CPU数量="+Runtime.getRuntime().availableProcessors());
    }
}
