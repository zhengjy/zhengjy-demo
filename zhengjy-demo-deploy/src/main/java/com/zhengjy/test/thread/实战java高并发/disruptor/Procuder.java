package com.zhengjy.test.thread.实战java高并发.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * RingBuffer（环形队列）
 * 环形队列，只需要对外提供一个当前位置cursor，利用这个指针即可用进入入队也可以进行出队。
 * Disruptor的性能要比BkickingQueue至少高一个数量级以上。
 * Created by zhengjy on 2017/7/2.
 */
public class Procuder  {
    //环形缓冲区
    private RingBuffer<PCData> ringBuffer;
    public Procuder(RingBuffer<PCData> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    /**
     * 将传入的byteBuffer中的数据提取出来，并装载到环形缓冲器中。
     * @param bb
     */
    public void pushData(ByteBuffer bb){
        //得到下一个可用的序列号
        long sequence = ringBuffer.next();
        try{
            //通过序列号，取得下一个空闲可以的PCData，
            PCData event = ringBuffer.get(sequence);
            //将PCData的数据设为期望值,这个值最终会传递给消费者
            event.set(bb.getLong(0));
        }finally {
            //只要发布后的数据才会真正被消费者看见
            ringBuffer.publish(sequence);
        }
    }
}
