package com.zhengjy.rpc.core;

import com.zhengjy.rpc.model.MessageRequest;
import com.zhengjy.rpc.model.MessageResponse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Rcp消息回调
 * Created by Jiyang.Zheng on 2019/1/11 14:54.
 */
public class MessageCallBack {
    private MessageRequest request;
    private MessageResponse rsponse;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }



    public void over(MessageResponse rsponse) {
        try {
            lock.lock();
            finish.signal();
            this.rsponse = rsponse;
        }finally {
            lock.unlock();
        }

    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            //设定一下超时时间，rpc服务器太久没有相应的话，就默认返回空吧。
            finish.await(10*1000, TimeUnit.MILLISECONDS);
            if (rsponse != null){
                return this.rsponse.getResultDesc();
            }else {
                return null;
            }
        }finally {
            lock.unlock();
        }

    }
}
