package com.zhengjy.test.concurrent.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjy on 2018/3/20 14:56
 */
public class ReentrantLockTest2 {



    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
            }
        }).start();

        reentrantLock.unlock();
    }
}
