package com.zhengjy.test.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁重入
 * Created by zhengjy on 2017/6/30.
 */
public class ReentrantLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCondition rkc = new ReentrantLockCondition();
        Thread t1 = new Thread(rkc);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        //通知线程t1继续执行
        condition.signal();
        System.out.println("----");
        Thread.sleep(5000);

        lock.unlock();
    }
}
