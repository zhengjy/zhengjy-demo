package com.zhengjy.test.thread.实战java高并发.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();
        //设置缓冲区大小为1024
        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<PCData>(
                factory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                //BlockingWaitStrategy：这是默认的策略。
                new BlockingWaitStrategy());
        //处理数据的消费者,设置了4个消费者实例,系统会将为每个消费者实例映射到一个线程中，也就是这里提供4个消费者线程
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        //启动并初始化disruptor
        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Procuder procuder = new Procuder(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        //由一个生产者不断象缓冲区存入数据
        for (long L=0;true;L++){
            bb.putLong(0,L);
            System.out.println("add data "+L);
            procuder.pushData(bb);
            Thread.sleep(100);

        }
    }


}
